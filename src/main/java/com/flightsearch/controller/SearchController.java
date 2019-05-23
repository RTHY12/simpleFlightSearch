package com.flightsearch.controller;

import com.flightsearch.model.Flight;
import com.flightsearch.service.FlightSearch;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class SearchController {

    @Autowired
    private FlightSearch flightSearch;


    @GetMapping(value = "/search")
    public List<Flight> search(@RequestParam("date") String date,
                               @RequestParam(required = false, value = "origin") String origin,
                               @RequestParam(required = false, value = "destination") String destination,
                               @RequestParam(required = false, value = "flightNumber") String flightNumber) throws ParseException, IOException {
        if (Objects.isNull(date)) {
            throw new RuntimeException("Date is mandatory");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = simpleDateFormat.parse(date);
        if (!Strings.isNullOrEmpty(flightNumber)) {
            if (Strings.isNullOrEmpty(origin) || Strings.isNullOrEmpty(destination)) {
                throw new RuntimeException("Origin destiation and flight number both cannot be searched");
            }
            return flightSearch.find(d, flightNumber);
        } else if (!Strings.isNullOrEmpty(destination) && !Strings.isNullOrEmpty(origin)) {
            if (!Strings.isNullOrEmpty(flightNumber)) {
                throw new RuntimeException("Invalid input");
            }
            return flightSearch.find(d, origin, destination);
        } else {
            throw new RuntimeException("Invalid Inputs..");
        }


    }

}
