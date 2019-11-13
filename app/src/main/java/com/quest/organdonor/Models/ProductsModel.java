package com.quest.organdonor.Models;

public class ProductsModel {
    private String id;
    private String title;
    private String cat_id;
    private String description;
    private String price;
    private String image;
    private String random;

    public ProductsModel(String id, String title, String cat_id, String description, String price, String image, String random) {
        this.id = id;
        this.title = title;
        this.cat_id = cat_id;
        this.description = description;
        this.price = price;
        this.image = image;
        this.random = random;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
