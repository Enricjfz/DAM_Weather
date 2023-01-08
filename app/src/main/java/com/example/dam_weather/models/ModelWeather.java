package com.example.dam_weather.models;

public class ModelWeather {

    private String name_city;
    private String icon_path;
    private double temperature;
    private String date;

    public ModelWeather(String name_city, String icon_path, double temperature, String date) {
        this.name_city = name_city;
        this.icon_path = icon_path;
        this.temperature = temperature;
        this.date = date;
    }

    public String getName_city() {
        return name_city;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
