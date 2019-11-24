package com.quest.organdonor.models;

public class PostModel {

    private String post_id;
    private String user_id;
    private String user_name;
    private String user_image;
    private String description;

    public PostModel(String post_id, String user_id, String user_name, String user_image, String description) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_image = user_image;
        this.description = description;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
