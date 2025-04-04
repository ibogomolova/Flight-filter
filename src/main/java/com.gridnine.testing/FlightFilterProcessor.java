package com.gridnine.testing;

import com.gridnine.testing.interfaces.FlightFilterService;
import com.gridnine.testing.models.Flight;

import java.util.List;

public class FlightFilterProcessor {
    private final List<FlightFilterService> filters;

    public FlightFilterProcessor(List<FlightFilterService> filters) {
        this.filters = filters;
    }

    public List<Flight> process(List<Flight> flights) {
        for (FlightFilterService filter : filters) {
            flights = filter.filter(flights);
        }
        return flights;
    }
}
