package com.gridnine.testing.impls;

import com.gridnine.testing.interfaces.FlightFilterService;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Этот класс фильтрует и удаляют рейсы, где общее время, проведённое на земле, превышает два часа.
 */
public class LongGroundTimeFilter implements FlightFilterService {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long groundTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        groundTime += java.time.Duration.between(
                                segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate()
                        ).toHours();
                    }
                    return groundTime <= 2;
                })
                .collect(Collectors.toList());
    }
}