package com.gridnine.testing.impls;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalBeforeDepartureFilterTest {

    private final ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();

    @Test
    void testFilterValidFlights() {
        Flight validFlight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)))
        );
        List<Flight> flights = List.of(validFlight);
        assertEquals(flights, filter.filter(flights));
    }

    @Test
    void testFilterInvalidFlights() {
        Flight invalidFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now())));
        List<Flight> flights = List.of(invalidFlight);
        assertTrue(filter.filter(flights).isEmpty());
    }

    @Test
    void testFilterMixedFlights() {
        Flight validFlight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2))));
        Flight invalidFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now())));
        List<Flight> flights = List.of(validFlight, invalidFlight);
        assertEquals(List.of(validFlight), filter.filter(flights));
    }

    @Test
    void testFilterEmptyList() {
        List<Flight> flights = List.of();
        assertTrue(filter.filter(flights).isEmpty());
    }
}