package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    public List<Flight> getAvailableFlights() {
        List<Flight> flights = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        // Flight 1
        cal.set(2025, Calendar.JUNE, 10, 9, 30);
        Date dep1 = cal.getTime();
        cal.set(2025, Calendar.JUNE, 10, 12, 15);
        Date arr1 = cal.getTime();

        flights.add(new Flight(
                "F001",
                "Delta Airlines",
                "DL123",
                dep1,
                arr1,
                "JFK",
                "LAX",
                "5h 45m",
                320.50
        ));

        // Flight 2
        cal.set(2025, Calendar.JUNE, 10, 14, 0);
        Date dep2 = cal.getTime();
        cal.set(2025, Calendar.JUNE, 10, 17, 10);
        Date arr2 = cal.getTime();

        flights.add(new Flight(
                "F002",
                "United Airlines",
                "UA456",
                dep2,
                arr2,
                "JFK",
                "LAX",
                "6h 10m",
                350.75
        ));

        // Flight 3
        cal.set(2025, Calendar.JUNE, 10, 18, 45);
        Date dep3 = cal.getTime();
        cal.set(2025, Calendar.JUNE, 10, 21, 30);
        Date arr3 = cal.getTime();

        flights.add(new Flight(
                "F003",
                "American Airlines",
                "AA789",
                dep3,
                arr3,
                "JFK",
                "LAX",
                "5h 45m",
                310.00
        ));

        // Flight 4
        cal.set(2025, Calendar.JUNE, 11, 6, 0);
        Date dep4 = cal.getTime();
        cal.set(2025, Calendar.JUNE, 11, 8, 55);
        Date arr4 = cal.getTime();

        flights.add(new Flight(
                "F004",
                "Southwest Airlines",
                "SW100",
                dep4,
                arr4,
                "JFK",
                "LAX",
                "5h 55m",
                295.00
        ));

        return flights;
    }
}
