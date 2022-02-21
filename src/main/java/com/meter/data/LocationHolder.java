package com.meter.data;

import com.meter.geo.Loc;

public abstract class LocationHolder {
    private Loc location;

    public LocationHolder(Loc location) {
        this.location = location;
    }

    public Loc getLocation() {
        return location;
    }
}
