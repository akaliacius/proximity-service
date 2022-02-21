package com.meter.geo;

public enum CoordinateType {

    NORTH_WEST {
        @Override
        public Coordinates calculate(Coordinates parent) {
            var latitudeSum = sum(parent.northWest().lat(), parent.northEast().lat());
            var longitudeSum = sum(parent.southWest().lon(), parent.southWest().lon());

            var northWest = parent.northWest();
            var northEast = new Loc(parent.northWest().lat(), parent.northWest().lon() + longitudeSum/2);
            var southEast = new Loc(parent.southEast().lat() + latitudeSum/2, parent.southEast().lon() - longitudeSum/2);
            var southWest = new Loc(southEast.lat(), northWest.lon());

            return new Coordinates(
                    northWest,
                    northEast,
                    southEast,
                    southWest
            );
        }
    },

    NORTH_EAST {
        @Override
        public Coordinates calculate(Coordinates parent) {

            var latitudeSum = sum(parent.northWest().lat(), parent.northEast().lat());
            var longitudeSum = sum(parent.southWest().lon(), parent.southWest().lon());

            var northWest = new Loc(parent.northWest().lat(), parent.northWest().lon() + longitudeSum/2);
            var northEast = parent.northEast();
            var southEast = new Loc(parent.southEast().lat() + latitudeSum/2, parent.southEast().lon());
            var southWest = new Loc(southEast.lat(), northWest.lon());

            return new Coordinates(
                    northWest,
                    northEast,
                    southEast,
                    southWest
            );
        }
    },

    SOUTH_EAST {
        @Override
        public Coordinates calculate(Coordinates parent) {
            var latitudeSum = sum(parent.northWest().lat(), parent.northEast().lat());
            var longitudeSum = sum(parent.southWest().lon(), parent.southWest().lon());

            var northWest = new Loc(parent.northWest().lat() - latitudeSum/2, parent.northWest().lon() + longitudeSum/2);
            var northEast = new Loc(northWest.lat(), parent.northEast().lon());
            var southEast = parent.southEast();
            var southWest = new Loc(southEast.lat(), northWest.lon());

            return new Coordinates(
                    northWest,
                    northEast,
                    southEast,
                    southWest
            );
        }
    },

    SOUTH_WEST {
        @Override
        public Coordinates calculate(Coordinates parent) {
            var latitudeSum = sum(parent.northWest().lat(), parent.northEast().lat());
            var longitudeSum = sum(parent.southWest().lon(), parent.southWest().lon());

            var northWest = new Loc(parent.northWest().lat() - latitudeSum/2, parent.northWest().lon());
            var northEast = new Loc(northWest.lat(), parent.northEast().lon() - longitudeSum/2);
            var southEast = new Loc(parent.southEast().lat(), northEast.lon());
            var southWest = parent.southWest();

            return new Coordinates(
                    northWest,
                    northEast,
                    southEast,
                    southWest
            );
        }
    };

    public abstract Coordinates calculate(Coordinates parent);

    private static double sum(double d1, double d2){
        return Math.abs(d1) + Math.abs(d2);
    }
}
