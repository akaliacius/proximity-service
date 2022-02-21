package com.meter.grid;

import com.meter.data.LocationHolder;

import java.util.stream.Stream;

public record QuadGrid<X extends LocationHolder>(
        Grid<X> westNorth,
        Grid<X> eastNorth,
        Grid<X> eastSouth,
        Grid<X> westSouth) {

    public Stream<Grid<X>> stream(){
        return Stream.of(westNorth, eastNorth, eastSouth, westSouth);
    }
}
