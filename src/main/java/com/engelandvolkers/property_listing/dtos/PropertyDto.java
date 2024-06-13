package com.engelandvolkers.property_listing.dtos;

public class PropertyDto {

    private String pid;
    private String name;
    private double price;
    //private String currency = "EUR";

    public PropertyDto(String pid, String name, Double price) {
        this.pid = pid;
        this.name = name;
        this.price = price;
    }

}
