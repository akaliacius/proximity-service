package com.meter.geo;

import org.junit.jupiter.api.Test;

import static com.meter.geo.Coordinates.WORLD_MAP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCoordinates {

    @Test void any_valid_location_should_be_on_map(){
        Coordinates worldMap = WORLD_MAP;
        var loc = new Loc(51.464124,0.0286684);
        assertTrue(Coordinates.isLocationInRange(worldMap, loc));
    }

    @Test void location_with_invalid_lat_not_on_map(){
        Coordinates worldMap = WORLD_MAP;
        var loc = new Loc(91,0.0286684);
        assertFalse(Coordinates.isLocationInRange(worldMap, loc));
    }

    @Test void location_with_invalid_lng_not_on_map(){
        Coordinates worldMap = WORLD_MAP;
        var loc = new Loc(51.464124,181);
        assertFalse(Coordinates.isLocationInRange(worldMap, loc));
    }

    @Test void location_with_invalid_lat_and_lng_not_on_map(){
        Coordinates worldMap = WORLD_MAP;
        var loc = new Loc(91,181);
        assertFalse(Coordinates.isLocationInRange(worldMap, loc));
    }
}
