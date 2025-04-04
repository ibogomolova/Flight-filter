package com.gridnine.testing.impls;

import com.gridnine.testing.interfaces.FlightFilterService;
import com.gridnine.testing.models.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Этот класс фильтрует и удаляют рейсы, которые уже должны были вылететь.
 */
public class CurrentMomentInTimeFilter implements FlightFilterService {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().get(0).getDepartureDate().isAfter(now))
                .collect(Collectors.toList());
    }
}