package com.gridnine.testing.interfaces;

import com.gridnine.testing.models.Flight;

import java.util.List;

public interface FlightFilterService {
    List<Flight> filter(List<Flight> flights);
}