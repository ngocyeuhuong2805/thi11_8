package com.example.thi11_8_ph26008.models;

public class Spmodel {
    private String id;
    private String name;
    private String image;
    private String description;
    private String color;
    private int price;

    public Spmodel(String name, String image, String description, String color, int price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public Spmodel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
