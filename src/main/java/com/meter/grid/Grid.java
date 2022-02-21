package com.meter.grid;

import com.meter.data.LocationHolder;
import com.meter.geo.CoordinateType;
import com.meter.geo.Coordinates;
import com.meter.geo.Loc;

import java.util.*;
import java.util.function.BiFunction;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Grid<X extends LocationHolder> {

    private final int maxCapacity;
    private final Grid<X> parent;
    private final Coordinates coordinates;
    private QuadGrid<X> children;
    private List<X> locationHolders = new ArrayList<>();
    private final long level;

    public Grid(Optional<Grid<X>> parent, Coordinates coordinates, int maxCapacity) {
        if (parent.isEmpty()) {
            this.parent = null;
            level = 0;
        } else {
            this.parent = parent.get();
            level = parent.get().level + 1;
        }
        this.maxCapacity = maxCapacity;
        this.coordinates = coordinates;
    }

    public List<X> sortLocationHolders(Grid<X> grid, Loc yourLocation, int limit, BiFunction<Loc, Loc, Double> locationFunction) {
        grid.validate(yourLocation);
        if (grid.amIParent()) {
            Grid<X> next = grid.children.stream()
                    .filter(child -> child.canIAccept(yourLocation))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("this should have not happened"));
            return sortLocationHolders(next, yourLocation, limit, locationFunction);
        }
        List<X> parent = grid.parent.locationHolders;
        List<X> children = grid.parent
                .children
                .stream()
                .flatMap(child -> child.locationHolders.stream())
                .toList();

        List<X> accumulator = new ArrayList<>();
        accumulator.addAll(parent);
        accumulator.addAll(children);
        return sort(accumulator, yourLocation, limit, locationFunction);
    }

    List<X> sort(List<X> data, Loc location, int limit, BiFunction<Loc, Loc, Double> locationFunction){
        return data.stream()
                .map(locationHolder -> new AbstractMap.SimpleEntry<>(
                        locationHolder,
                        locationFunction.apply(locationHolder.getLocation(), location))
                )
                .sorted(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue))
                .map(AbstractMap.SimpleEntry::getKey)
                .limit(limit)
                .toList();
    }

    public boolean insert(X locationHolder) {
        validate(locationHolder.getLocation());
        if (isNull(children)) {
            if (locationHolders.size() < maxCapacity) {
                return locationHolders.add(locationHolder);
            } else {
                createChildren();
            }
        }
        return children.stream()
                .filter(child -> child.canIAccept(locationHolder.getLocation()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("no one can accept this"))
                .insert(locationHolder);
    }

    boolean canIAccept(Loc location) {
        return Coordinates.isLocationInRange(coordinates, location);
    }

    public void validate(Loc location) {
        if (!canIAccept(location)) {
            throw new IllegalArgumentException(location + " is not on world map. something is not right here");
        }
    }

    void createChildren() {
        children = new QuadGrid<>(
            new Grid<>(Optional.of(this), CoordinateType.NORTH_WEST.calculate(this.coordinates), this.maxCapacity),
            new Grid<>(Optional.of(this), CoordinateType.NORTH_EAST.calculate(this.coordinates), this.maxCapacity),
            new Grid<>(Optional.of(this), CoordinateType.SOUTH_EAST.calculate(this.coordinates), this.maxCapacity),
            new Grid<>(Optional.of(this), CoordinateType.SOUTH_WEST.calculate(this.coordinates), this.maxCapacity)
        );
    }

    public int size() {
        return locationHolders.size();
    }

    public long getLevel() {
        return level;
    }

    public boolean amIChild() {
        return nonNull(parent);
    }

    public boolean amIParent() {
        return nonNull(children);
    }

    public boolean amIBoss() {
        return isNull(parent);
    }
}
