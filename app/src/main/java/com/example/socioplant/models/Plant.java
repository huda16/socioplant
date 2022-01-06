package com.example.socioplant.models;

public class Plant {
    private String id;
    private String name;
    private String type;
    private String description;
    private String behavior;
    private String photo;

    public Plant() {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.behavior = behavior;
        this.photo = photo;
    }
    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
