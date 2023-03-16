package com.itz.entity;

public class CollectList {
    private UserCollections userCollections;
    private User user;
    private Video video;

    public CollectList() {
    }

    public CollectList(UserCollections userCollections, User user, Video video) {
        this.userCollections = userCollections;
        this.user = user;
        this.video = video;
    }

    public UserCollections getUserCollections() {
        return userCollections;
    }

    public void setUserCollections(UserCollections userCollections) {
        this.userCollections = userCollections;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "CollectList{" +
                "userCollections=" + userCollections +
                ", user=" + user +
                ", video=" + video +
                '}';
    }
}
