package com.example.covid_19eg.models;

public class properties {


    String country,imageUrl;
    int cases,todayCases,deaths,todayDeaths,recovered,active;

    public properties(String country, String imageUrl, int cases, int todayCases, int deaths, int todayDeaths, int recovered, int active) {
        this.country = country;
        this.imageUrl=imageUrl;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
    }

    public properties() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


}
