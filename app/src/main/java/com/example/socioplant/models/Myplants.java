package com.example.socioplant.models;

public class Myplants {
    private String userId;
    private String plantId;
    private String createdDate;
    private String name;
    private String type;
    private String photo;


    public Myplants(String userId, String plantId, String createdDate, String name, String type, String photo) {
        this.userId = userId;
        this.plantId = plantId;
        this.createdDate = createdDate;
        this.name = name;
        this.type = type;
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
