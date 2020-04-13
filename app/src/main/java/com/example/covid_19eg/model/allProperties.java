package com.example.covid_19eg.model;

public class allProperties {


     String country,imageUrl,cases,deaths,recovered;

    public allProperties(String country, String imageUrl, String cases, String deaths, String recovered ) {
        this.country = country;
        this.imageUrl = imageUrl;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;

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

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
