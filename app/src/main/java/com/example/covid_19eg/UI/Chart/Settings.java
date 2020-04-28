package com.example.covid_19eg.UI.Chart;

public class Settings {
    private static final String URL_API = "https://pomber.github.io/covid19/timeseries.json";
    private static String SORT_BY = "DEFAULT";

    public static String getURL() {
        return (URL_API);
    }

    public static void setSortBy(String sortBy) {
        SORT_BY = sortBy;
    }

    public static String getSortBy() {
        return SORT_BY;
    }
}