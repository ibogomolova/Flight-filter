package com.gridnine.testing;

import com.gridnine.testing.impls.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.impls.CurrentMomentInTimeFilter;
import com.gridnine.testing.impls.LongGroundTimeFilter;
import com.gridnine.testing.interfaces.FlightFilterService;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterProcessorTest {

    private FlightFilterProcessor flightFilterProcessor;
    private List<FlightFilterService> filters;

    @BeforeEach
    public void setUp() {
        filters = new ArrayList<>();
        filters.add(new ArrivalBeforeDepartureFilter());
        filters.add(new CurrentMomentInTimeFilter());
        filters.add(new LongGroundTimeFilter());
        flightFilterProcessor = new FlightFilterProcessor(filters);
    }

    @Test
    public void testProcessWithValidFlights() {
        List<Flight> flights = new ArrayList<>();

        Segment segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));
        Flight validFlight = new Flight(List.of(segment1, segment2));

        flights.add(validFlight);

        List<Flight> filteredFlights = flightFilterProcessor.process(flights);

        assertEquals(1, filteredFlights.size());
    }

    @Test
    public void testProcessWithArrivalBeforeDeparture() {
        List<Flight> flights = new ArrayList<>();

        Segment segment1 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(1));
        Flight invalidFlight = new Flight(List.of(segment1));

        flights.add(invalidFlight);

        List<Flight> filteredFlights = flightFilterProcessor.process(flights);

        assertEquals(0, filteredFlights.size());
    }

    @Test
    public void testProcessWithPastDeparture() {
        List<Flight> flights = new ArrayList<>();

        Segment segment1 = new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1));
        Flight pastFlight = new Flight(List.of(segment1));

        flights.add(pastFlight);

        List<Flight> filteredFlights = flightFilterProcessor.process(flights);

        assertEquals(0, filteredFlights.size());
    }

    @Test
    public void testProcessWithLongGroundTime() {
        List<Flight> flights = new ArrayList<>();

        Segment segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6));
        Flight longGroundTimeFlight = new Flight(List.of(segment1, segment2));

        flights.add(longGroundTimeFlight);

        List<Flight> filteredFlights = flightFilterProcessor.process(flights);

        assertEquals(0, filteredFlights.size());
    }

    @Test
    public void testProcessWithMultipleFilters() {
        List<Flight> flights = new ArrayList<>();

        Segment segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));
        Flight validFlight = new Flight(List.of(segment1, segment2));

        Segment segment3 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(1));
        Flight invalidFlight = new Flight(List.of(segment3));

        flights.add(validFlight);
        flights.add(invalidFlight);

        List<Flight> filteredFlights = flightFilterProcessor.process(flights);

        assertEquals(1, filteredFlights.size());
    }
}