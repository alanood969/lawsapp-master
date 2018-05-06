package com.example.taha.laws.data;

//Author: Mohammad
public class Post {
    private int ID;
    private String title;
    private String content;
    private Users user = new Users();
    private int topic;
    private String imageID;
    private String createDate;
    private String modifyDate;

    public Post() {
    }

    public Post(Post post) {
        this.ID = post.ID;
        this.title = post.title;
        this.content = post.content;
        this.user.setID(post.user.getID());
        this.topic = post.topic;
        this.imageID = post.imageID;
        this.createDate = post.createDate;
        this.modifyDate = post.modifyDate;
    }

    public Post(int ID, String title, String content, Users user, int topic, String imageID, String createDate, String modifyDate) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.user.setID(user.getID());
        this.topic = topic;
        this.imageID = imageID;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Users getUser() {
        return user;
    }

    public int getTopic() {
        return topic;
    }

    public String getImageID() { return imageID; }

    public String getCreateDate() {
        return createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public void setImageID(String imageID) { this.imageID = imageID; }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
