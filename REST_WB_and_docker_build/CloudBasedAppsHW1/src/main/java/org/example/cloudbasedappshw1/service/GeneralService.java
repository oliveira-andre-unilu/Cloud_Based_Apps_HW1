package org.example.cloudbasedappshw1.service;

import org.example.cloudbasedappshw1.dto.YearPopulation;
import org.example.cloudbasedappshw1.httpclient.Request;
import org.example.cloudbasedappshw1.httpclient.answer.Answer;
import org.example.cloudbasedappshw1.httpclient.answer.Observation;
import org.example.cloudbasedappshw1.httpclient.answer.ObservationValue;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeneralService {

    private Request httpRequest;

    public GeneralService(Request httpRequest) {
        this.httpRequest = httpRequest;
    }

    public List<YearPopulation> getYearPopulation(int year) {
        //Getting raw data
        Answer rawData = httpRequest.executeRequest().block();

        if(rawData == null) {
            return null;
        }
        //Getting index of current year
        int yearIndex = verifyPopulationForYear(rawData, year);
        if(yearIndex == -1) {
            return null;
        }

        //Finding the correct population
        List<YearPopulation> yearPopulations = findPopulationByIndex(rawData, yearIndex, year);
        return yearPopulations;
    }

    private List<YearPopulation> findPopulationByIndex(Answer rawData, int index, int year) {
        //Getting raw observations
        // needed keys -> [<0:0:index>, <1:0:index>, <2:0:index>, <3:0:index>]
        // related sample -> [Foreign males, Luxembourgish males, Luxembourgish females, Foreign females]
        Map<String, List<Object>> allObservations = rawData.getDataSets().get(0).getObservations();

        if(allObservations == null) {
            return null;
        }

        YearPopulation pastPopulation = recursivePastPopulation(rawData, allObservations, index, year);
        YearPopulation futurePopulation = recursiveFuturePopulation(rawData, allObservations, index, year);
        if(pastPopulation == null && futurePopulation != null) {
            return List.of(futurePopulation); // Case where only future population exist
        }
        if(pastPopulation != null && futurePopulation == null) {
            return List.of(pastPopulation); // Case where only past population exist
        }
        if(futurePopulation == null && pastPopulation == null) {
            return null;
        }
        if(pastPopulation.getYear() == futurePopulation.getYear()) {
            return List.of(pastPopulation);
        }else{
            return List.of(pastPopulation, futurePopulation);
        }

    }

    private YearPopulation recursivePastPopulation(Answer rawData, Map<String, List<Object>> allObservations, int index, int year) {
        if(allObservations == null || index < 0 || index >= allObservations.size()) {
            return null;
        }

        //Getting each necessary population
        String foreignMalePop = String.valueOf(allObservations.get("0:0:"+index).getFirst());
        String luxembourgishMalePop = String.valueOf(allObservations.get("1:0:"+index).getFirst());
        String luxembourgishFemalePop = String.valueOf(allObservations.get("2:0:"+index).getFirst());
        String foreignFemalePop = String.valueOf(allObservations.get("3:0:"+index).getFirst());

        if(foreignMalePop.isEmpty() || luxembourgishMalePop.isEmpty() || luxembourgishFemalePop.isEmpty() || foreignFemalePop.isEmpty()) {
            //Getting previous year
            int previousYear = getYearForIndex(rawData, index-1);
            if(previousYear == -1) {
                return null;
            }
            return recursivePastPopulation(rawData, allObservations, index-1, previousYear);
        }

        int foreignMalePopNum;
        int luxembourgishMalePopNum;
        int luxembourgishFemalePopNum;
        int foreignFemalePopNum;

        try{
            foreignMalePopNum = Integer.parseInt(foreignMalePop);
            luxembourgishMalePopNum = Integer.parseInt(luxembourgishMalePop);
            luxembourgishFemalePopNum = Integer.parseInt(luxembourgishFemalePop);
            foreignFemalePopNum = Integer.parseInt(foreignFemalePop);
        }catch(NumberFormatException e){
            return null;
        }

        return new YearPopulation(year, foreignFemalePopNum+luxembourgishFemalePopNum+luxembourgishMalePopNum+foreignMalePopNum,
                luxembourgishFemalePopNum+foreignFemalePopNum,
                luxembourgishMalePopNum+foreignMalePopNum,
                luxembourgishMalePopNum, luxembourgishFemalePopNum,
                foreignMalePopNum, foreignFemalePopNum);
    }

    private YearPopulation recursiveFuturePopulation(Answer rawData, Map<String, List<Object>> allObservations, int index, int year) {
        if(allObservations == null || index < 0 || index >= allObservations.size()) {
            return null;
        }

        //Getting each necessary population
        String foreignMalePop = String.valueOf(allObservations.get("0:0:"+index).getFirst());
        String luxembourgishMalePop = String.valueOf(allObservations.get("1:0:"+index).getFirst());
        String luxembourgishFemalePop = String.valueOf(allObservations.get("2:0:"+index).getFirst());
        String foreignFemalePop = String.valueOf(allObservations.get("3:0:"+index).getFirst());

        if(foreignMalePop.isEmpty() || luxembourgishMalePop.isEmpty() || luxembourgishFemalePop.isEmpty() || foreignFemalePop.isEmpty()) {
            //Getting next year
            int nextYear = getYearForIndex(rawData, index+1);
            if(nextYear == -1) {
                return null;
            }
            return recursiveFuturePopulation(rawData, allObservations, index+1, nextYear);
        }

        int foreignMalePopNum;
        int luxembourgishMalePopNum;
        int luxembourgishFemalePopNum;
        int foreignFemalePopNum;

        try{
            foreignMalePopNum = Integer.parseInt(foreignMalePop);
            luxembourgishMalePopNum = Integer.parseInt(luxembourgishMalePop);
            luxembourgishFemalePopNum = Integer.parseInt(luxembourgishFemalePop);
            foreignFemalePopNum = Integer.parseInt(foreignFemalePop);
        }catch(NumberFormatException e){
            return null;
        }

        return new YearPopulation(year, foreignFemalePopNum+luxembourgishFemalePopNum+luxembourgishMalePopNum+foreignMalePopNum,
                luxembourgishFemalePopNum+foreignFemalePopNum,
                luxembourgishMalePopNum+foreignMalePopNum,
                luxembourgishMalePopNum, luxembourgishFemalePopNum,
                foreignMalePopNum, foreignFemalePopNum);
    }

    private int verifyPopulationForYear(Answer rawData, int year) {
        //Getting index of desired year
        List<Observation> dimensionObs = rawData.getStructure().getDimensions().getObservation();
        List<ObservationValue> allValues = new ArrayList<>();
        for(Observation observation : dimensionObs) {
            if(observation.getId().equals("TIME_PERIOD")) {
                allValues = observation.getValues();
            }
        }

        if(allValues.isEmpty()){
            return -1;
        }
        //Trying to find the year within the Values
        for(int tempIndex = 0 ; tempIndex < allValues.size() ; tempIndex++) {
            //Fetching String to year
            String rawString = allValues.get(tempIndex).getId().split("-")[0];
            int tempYear;
            try{
                tempYear = Integer.parseInt(rawString);
            }catch (NumberFormatException e){
                return -1;
            }
            if (year == tempYear) {
                return tempIndex;
            }
        }
        return -1;
    }

    private int getYearForIndex(Answer rawData, int index) {
        List<Observation> dimensionObs = rawData.getStructure().getDimensions().getObservation();
        List<ObservationValue> allValues = new ArrayList<>();
        for(Observation observation : dimensionObs) {
            if(observation.getId().equals("TIME_PERIOD")) {
                allValues = observation.getValues();
            }
        }

        if(allValues.isEmpty()){
            return -1;
        }
        if(index < 0 || index >= allValues.size()) {
            return -1;
        }
        String rawYear = allValues.get(index).getId().split("-")[0];
        int result;
        try{
            result = Integer.parseInt(rawYear);
        }catch (NumberFormatException e){
            return -1;
        }
        return result;
    }


}
