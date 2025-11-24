package org.example.cloudbasedappshw1.dto;

public class YearPopulation {

    private int year;
    private int totalPopulation;
    private int totalFemalePopulation;
    private int totalMalePopulation;
    private int totalLuxembourgishMalePopulation;
    private int totalLuxembourgishFemalePopulation;
    private int totalForeignMalePopulation;
    private int totalForeignFemalePopulation;

    //Constructor
    public YearPopulation(int year, int totalPopulation, int totalFemalePopulation,
                          int totalMalePopulation, int totalLuxembourgishMalePopulation, int totalLuxembourgishFemalePopulation,
                          int totalForeignMalePopulation, int totalForeignFemalePopulation) {
        this.year = year;
        this.totalPopulation = totalPopulation;
        this.totalFemalePopulation = totalFemalePopulation;
        this.totalMalePopulation = totalMalePopulation;
        this.totalLuxembourgishMalePopulation = totalLuxembourgishMalePopulation;
        this.totalLuxembourgishFemalePopulation = totalLuxembourgishFemalePopulation;
        this.totalForeignMalePopulation = totalForeignMalePopulation;
        this.totalForeignFemalePopulation = totalForeignFemalePopulation;
    }


    //Getters and setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public int getTotalFemalePopulation() {
        return totalFemalePopulation;
    }

    public void setTotalFemalePopulation(int totalFemalePopulation) {
        this.totalFemalePopulation = totalFemalePopulation;
    }

    public int getTotalMalePopulation() {
        return totalMalePopulation;
    }

    public void setTotalMalePopulation(int totalMalePopulation) {
        this.totalMalePopulation = totalMalePopulation;
    }

    public int getTotalLuxembourgishMalePopulation() {
        return totalLuxembourgishMalePopulation;
    }

    public void setTotalLuxembourgishMalePopulation(int totalLuxembourgishMalePopulation) {
        this.totalLuxembourgishMalePopulation = totalLuxembourgishMalePopulation;
    }

    public int getTotalLuxembourgishFemalePopulation() {
        return totalLuxembourgishFemalePopulation;
    }

    public void setTotalLuxembourgishFemalePopulation(int totalLuxembourgishFemalePopulation) {
        this.totalLuxembourgishFemalePopulation = totalLuxembourgishFemalePopulation;
    }

    public int getTotalForeignMalePopulation() {
        return totalForeignMalePopulation;
    }

    public void setTotalForeignMalePopulation(int totalForeignMalePopulation) {
        this.totalForeignMalePopulation = totalForeignMalePopulation;
    }

    public int getTotalForeignFemalePopulation() {
        return totalForeignFemalePopulation;
    }

    public void setTotalForeignFemalePopulation(int totalForeignFemalePopulation) {
        this.totalForeignFemalePopulation = totalForeignFemalePopulation;
    }
}
