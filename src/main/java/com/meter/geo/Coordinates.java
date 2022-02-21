package com.meter.geo;

public record Coordinates(Loc northWest, Loc northEast, Loc southEast, Loc southWest) {

    public static final Coordinates WORLD_MAP = new Coordinates(
            new Loc(90, -180),
            new Loc(90, 180),
            new Loc(-90, 180),
            new Loc(-90, -180)
    );

    public static boolean isLocationInRange(Coordinates coordinates, Loc loc){
        double fromLat = coordinates.southWest.lat();
        double toLat = coordinates.northWest.lat();
        double fromLon = coordinates.southWest.lon();
        double toLon = coordinates.southEast.lon();

        return inRange(fromLat, toLat, loc.lat()) && inRange(fromLon, toLon, loc.lon());
    }

    static boolean inRange(double from, double to, double number){
        return number >= from && number <= to;
    }
}
