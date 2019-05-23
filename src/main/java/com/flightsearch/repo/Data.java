package com.flightsearch.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.model.Flight;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class Data {


    public static List<Flight> loadFlightData() throws IOException {
        List<Flight> flights = Lists.newArrayList();
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader buffer =
                new BufferedReader(new InputStreamReader(
                        IOUtils.buffer(Data.class.getClassLoader().getResourceAsStream("flights.json"))));
        String s = null;
        while ((s = buffer.readLine()) != null) {
            s = s.trim();
            if (s.isEmpty()) {
                continue;
            }
            Flight f = mapper.readValue(s, Flight.class);
            flights.add(f);
        }
        return flights;
    }
}
