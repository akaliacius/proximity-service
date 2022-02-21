package com.meter;

import com.meter.data.Shop;
import com.meter.geo.Loc;

import java.util.List;

public class LocationResponse {

    private Loc currentLocation;
    private List<Shop> shops;

    public Loc getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Loc currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
