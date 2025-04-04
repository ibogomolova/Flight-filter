package com.gridnine.testing.impls;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LongGroundTimeFilterTest {

    private final LongGroundTimeFilter filter = new LongGroundTimeFilter();

    @Test
    void testFilterFlightsWithoutLayovers() {
        Flight flight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        ));
        List<Flight> flights = List.of(flight);
        assertEquals(flights, filter.filter(flights));
    }

    @Test
    void testFilterFlightsWithShortGroundTime() {
        Flight validFlight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))
        ));
        List<Flight> flights = List.of(validFlight);
        assertEquals(flights, filter.filter(flights));
    }

    @Test
    void testFilterFlightsWithGroundTimeMoreThanTwoHours() {
        Flight invalidFlight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5))
        ));
        List<Flight> flights = List.of(invalidFlight);
        assertTrue(filter.filter(flights).isEmpty());
    }

    @Test
    void testFilterMixedFlights() {
        Flight validFlight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))
        ));
        Flight invalidFlight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5))
        ));
        List<Flight> flights = List.of(validFlight, invalidFlight);
        assertEquals(List.of(validFlight), filter.filter(flights));
    }

    @Test
    void testFilterEmptyList() {
        List<Flight> flights = List.of();
        assertTrue(filter.filter(flights).isEmpty());
    }

    @Test
    void testFilterMultipleSegments() {
        Flight flight = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6))
        ));
        List<Flight> flights = List.of(flight);
        assertTrue(filter.filter(flights).isEmpty());
    }
}

