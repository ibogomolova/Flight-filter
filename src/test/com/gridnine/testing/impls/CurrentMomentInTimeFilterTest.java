package com.gridnine.testing.impls;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrentMomentInTimeFilterTest {
    private final CurrentMomentInTimeFilter filter = new CurrentMomentInTimeFilter();

    @Test
    void testFilterFutureFlights() {
        Flight futureFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3))));
        List<Flight> flights = List.of(futureFlight);
        assertEquals(flights, filter.filter(flights));
    }

    @Test
    void testFilterPastFlights() {
        Flight pastFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1))));
        List<Flight> flights = List.of(pastFlight);
        assertTrue(filter.filter(flights).isEmpty());
    }

    @Test
    void testFilterMixedFlights() {
        Flight futureFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3))));
        Flight pastFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1))));
        List<Flight> flights = List.of(futureFlight, pastFlight);
        assertEquals(List.of(futureFlight), filter.filter(flights));
    }

    @Test
    void testFilterEmptyList() {
        List<Flight> flights = List.of();
        assertTrue(filter.filter(flights).isEmpty());
    }
}