import com.gridnine.testing.FlightFilterProcessor;
import com.gridnine.testing.impls.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.impls.CurrentMomentInTimeFilter;
import com.gridnine.testing.impls.LongGroundTimeFilter;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.FlightBuilder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilterProcessor processor = new FlightFilterProcessor(Arrays.asList(
                new CurrentMomentInTimeFilter(),
                new ArrivalBeforeDepartureFilter(),
                new LongGroundTimeFilter()
        ));

        List<Flight> filteredFlights = processor.process(flights);

        System.out.println("\n\tНеотфильтрованные перелеты:");
        System.out.println(flights);

        System.out.println("\n\tОтфильтрованные перелёты:");
        System.out.println(filteredFlights);
    }
}