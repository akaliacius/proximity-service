package com.meter.data;

import com.meter.geo.Loc;

public class Shop extends LocationHolder {

    private final String id;
    private String name;

    public Shop(Loc location, String id) {
        super(location);
        this.id = id;
    }

    public Shop(Loc location, String id, String name) {
        super(location);
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
