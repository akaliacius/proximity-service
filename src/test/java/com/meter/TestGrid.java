package com.meter;

import com.meter.data.Shop;
import com.meter.geo.Coordinates;
import com.meter.geo.Loc;
import com.meter.grid.Grid;
import com.meter.utils.LocationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TestGrid {

    private Grid<Shop> main;
    public Loc currentLocation;
    public Loc loc1;
    public Loc loc2;
    public Loc loc3;
    public Loc loc4;
    public Loc loc5;

    @BeforeEach void setup(){
        main = new Grid<>(Optional.empty(), Coordinates.WORLD_MAP, 2);
        currentLocation = new Loc(51.464123,0.0286684);
        loc1 = new Loc(51.464124,0.0286684);
        loc2 = new Loc(52.464256,0.0286684);
        loc3 = new Loc(53.464798,0.0286684);
        loc4 = new Loc(54.464958,0.0286684);
        loc5 = new Loc(55.464899,0.0286684);
    }

    @Test void sort_location_holders(){
        var limit = 2;
        main.insert(new Shop(loc1, UUID.randomUUID().toString()));
        main.insert(new Shop(loc2, UUID.randomUUID().toString()));
        main.insert(new Shop(loc3, UUID.randomUUID().toString()));

        List<Shop> shops = main.sortLocationHolders(
                main,
                currentLocation,
                limit,
                (l1, l2) -> LocationHelper.calculateDistance(l1.lat(), l1.lon(), l2.lat(), l2.lon())
        );
        assertFalse(shops.isEmpty());
        assertEquals(limit, shops.size());
        var shop = shops.get(0);
        assertEquals(loc1.lat(), shop.getLocation().lat());
        assertEquals(loc1.lon(), shop.getLocation().lon());
        var shop2 = shops.get(1);
        assertEquals(loc2.lat(), shop2.getLocation().lat());
        assertEquals(loc2.lon(), shop2.getLocation().lon());
        System.out.println(shops);
    }

    @Test void test_parent(){
        assertEquals(0, main.getLevel());
        assertFalse(main.amIChild());
        assertFalse(main.amIParent());
        assertTrue(main.amIBoss());
    }

    @Test void insert_test(){
        assertEquals(0, main.size());
        assertTrue(main.insert(new Shop(loc1, UUID.randomUUID().toString())));
        assertEquals(1, main.size());
    }

    @Test void insert_test2(){
        assertEquals(0, main.size());
        var loc = new Loc(98,0.0286684);
        assertThrows(IllegalArgumentException.class, () -> main.insert(new Shop(loc, UUID.randomUUID().toString())));
    }
}
