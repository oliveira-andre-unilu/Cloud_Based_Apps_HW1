package org.example.cloudbasedappshw1.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.example.cloudbasedappshw1.dto.YearPopulation;
import org.example.cloudbasedappshw1.service.GeneralService;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    private final GeneralService generalService;

    public APIController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/getPopulation")
    public List<YearPopulation> getPopulation(@RequestParam @Min(1800) @Max(2100) int year) {
        //Trying to get the information
        List<YearPopulation> requestedPopulation = generalService.getYearPopulation(year);
        if(requestedPopulation == null) {
            throw  new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not fetch the data from the requested year");
        }
        return requestedPopulation;
    }
}
