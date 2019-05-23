
package com.flightsearch.repo;

import com.flightsearch.model.Flight;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FlightData {


    public List<Flight> getData() throws IOException {
        return Data.loadFlightData();
    }

}
