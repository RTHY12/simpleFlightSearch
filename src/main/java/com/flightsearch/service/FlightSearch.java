
package com.flightsearch.service;

import com.flightsearch.model.Flight;
import com.flightsearch.repo.FlightData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightSearch {

    @Autowired
    private FlightData flightData;

    public List<Flight> find(Date date,
                             String flightNumber) throws IOException {
        return flightData.getData().stream()
                .filter(e -> e.getDeparture().equals(date))
                .filter(e -> e.getFlightNumber().equalsIgnoreCase(flightNumber))
                .collect(Collectors.toList());
    }

    public List<Flight> find(Date date,
                             String origin,
                             String destination
    ) throws IOException {
        return flightData.getData()
                .parallelStream()
                .filter(e -> e.getDeparture().equals(date))
                .filter(e -> e.getOrigin().equalsIgnoreCase(origin))
                .filter(e -> e.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }
}
