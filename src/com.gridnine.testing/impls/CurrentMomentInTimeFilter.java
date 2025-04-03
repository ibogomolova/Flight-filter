package com.gridnine.testing.impls;

import com.gridnine.testing.interfaces.FlightFilterService;
import com.gridnine.testing.models.Flight;

import java.util.List;

public class CurrentMomentInTimeFilter implements FlightFilterService {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return null;
    }
}
