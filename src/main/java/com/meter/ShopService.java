package com.meter;

import com.meter.data.Shop;
import com.meter.geo.Coordinates;
import com.meter.geo.Loc;
import com.meter.grid.Grid;
import com.meter.utils.LocationHelper;

import java.util.List;
import java.util.Optional;

public class ShopService {

    public static final int MAX_CAPACITY = 500;
    private final Grid<Shop> shopGrid;

    public ShopService() {
        this.shopGrid = new Grid<>(Optional.empty(), Coordinates.WORLD_MAP, MAX_CAPACITY);
    }

    public LocationResponse getNearest(Loc location, int limit){
        List<Shop> shops = shopGrid.sortLocationHolders(
                shopGrid,
                location,
                limit,
                (location1, location2) -> LocationHelper.calculateDistance(
                        location1.lat(),
                        location1.lon(),
                        location2.lat(),
                        location2.lon()
                )
        );
        var locationResponse = new LocationResponse();
        locationResponse.setCurrentLocation(location);
        locationResponse.setShops(shops);
        return locationResponse;
    }

    public boolean insertShop(Shop shop){
        return shopGrid.insert(shop);
    }
}
