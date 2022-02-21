package com.meter.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCoordinateType {

    @Test
    void test_north_west(){
        var parent = Coordinates.WORLD_MAP;
        var result = CoordinateType.NORTH_WEST.calculate(parent);

        var northWest = result.northWest();
        assertEquals(parent.northWest().lat(), northWest.lat());
        assertEquals(parent.northWest().lon(), northWest.lon());

        var northEast = result.northEast();
        assertEquals(parent.northEast().lat(), northEast.lat());
        assertEquals(0, northEast.lon());

        var southEast = result.southEast();
        assertEquals(0, southEast.lat());
        assertEquals(northEast.lon(), southEast.lon());

        var southWest = result.southWest();
        assertEquals(southEast.lat(), southWest.lat());
        assertEquals(northWest.lon(), southWest.lon());
    }

    @Test
    void test_north_east(){
        var parent = Coordinates.WORLD_MAP;
        var result = CoordinateType.NORTH_EAST.calculate(parent);

        var northWest = result.northWest();
        assertEquals(parent.northWest().lat(), northWest.lat());
        assertEquals(0, northWest.lon());

        var northEast = result.northEast();
        assertEquals(parent.northEast().lat(), northEast.lat());
        assertEquals(parent.northEast().lon(), northEast.lon());

        var southEast = result.southEast();
        assertEquals(0, southEast.lat());
        assertEquals(parent.southEast().lon(), southEast.lon());

        var southWest = result.southWest();
        assertEquals(southEast.lat(), southWest.lat());
        assertEquals(northWest.lon(), southWest.lon());
    }

    @Test
    void test_south_east(){
        var parent = Coordinates.WORLD_MAP;
        var result = CoordinateType.SOUTH_EAST.calculate(parent);

        var northWest = result.northWest();
        assertEquals(0, northWest.lat());
        assertEquals(0, northWest.lon());

        var northEast = result.northEast();
        assertEquals(northWest.lat(), northEast.lat());
        assertEquals(parent.northEast().lon(), northEast.lon());

        var southEast = result.southEast();
        assertEquals(parent.southEast().lat(), southEast.lat());
        assertEquals(parent.southEast().lon(), southEast.lon());

        var southWest = result.southWest();
        assertEquals(southEast.lat(), southWest.lat());
        assertEquals(northWest.lon(), southWest.lon());
    }

    @Test
    void test_south_west(){
        var parent = Coordinates.WORLD_MAP;
        var result = CoordinateType.SOUTH_WEST.calculate(parent);

        var northWest = result.northWest();
        assertEquals(0, northWest.lat());
        assertEquals(parent.northWest().lon(), northWest.lon());

        var northEast = result.northEast();
        assertEquals(northWest.lat(), northEast.lat());
        assertEquals(0, northEast.lon());

        var southEast = result.southEast();
        assertEquals(parent.southEast().lat(), southEast.lat());
        assertEquals(northEast.lon(), southEast.lon());

        var southWest = result.southWest();
        assertEquals(southEast.lat(), southWest.lat());
        assertEquals(northWest.lon(), southWest.lon());
    }
}
