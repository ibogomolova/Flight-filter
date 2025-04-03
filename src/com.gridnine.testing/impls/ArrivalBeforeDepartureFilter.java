package com.gridnine.testing.impls;

import com.gridnine.testing.interfaces.FlightFilterService;
import com.gridnine.testing.models.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Этот класс фильтрует и удаляют рейсы у которых прилет раньше чем вылет.
 */
public class ArrivalBeforeDepartureFilter implements FlightFilterService {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}