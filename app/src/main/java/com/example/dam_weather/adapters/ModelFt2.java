package com.example.dam_weather.adapters;

public class ModelFt2 {

    private String fecha;
    private String max_temp;
    private String min_temp;
    private String precipitaciones;
    private String maxwind_kph;
    private String icon_path;



    public ModelFt2 (String date, String max_temp, String min_temp, String precipitaciones, String maxwind_kph, String icon_path) {
        this.fecha = date;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.precipitaciones = precipitaciones;
        this.maxwind_kph = maxwind_kph;
        this.icon_path = icon_path;


    }

    public String getFecha() {
        return fecha;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public String getPrecipitaciones() {
        return precipitaciones;
    }

    public String getMaxwind_kph() {
        return maxwind_kph;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    public void setPrecipitaciones(String precipitaciones) {
        this.precipitaciones = precipitaciones;
    }

    public void setMaxwind_kph(String maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }
}
