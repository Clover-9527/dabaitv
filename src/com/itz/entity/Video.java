package com.itz.entity;

public class Video {
    private int vid;
    private String v_title; //标题
    private String v_img;   //封面
    private Double score;   //评分
    private String classify;    //类别
    private String protagonist;//主演
    private String description;//视频简介
    private String v_url;   //播放链接


    public Video() {
    }

    public Video(int vid, String v_title, String v_img, Double score, String classify, String protagonist, String description, String v_url) {
        this.vid = vid;
        this.v_title = v_title;
        this.v_img = v_img;
        this.score = score;
        this.classify = classify;
        this.protagonist = protagonist;
        this.description = description;
        this.v_url = v_url;
    }

    public Video(String v_title, String v_img, Double score, String classify, String protagonist, String description, String v_url) {
        this.v_title = v_title;
        this.v_img = v_img;
        this.score = score;
        this.classify = classify;
        this.protagonist = protagonist;
        this.description = description;
        this.v_url = v_url;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getV_title() {
        return v_title;
    }

    public void setV_title(String v_title) {
        this.v_title = v_title;
    }

    public String getV_img() {
        return v_img;
    }

    public void setV_img(String v_img) {
        this.v_img = v_img;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(String protagonist) {
        this.protagonist = protagonist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getV_url() {
        return v_url;
    }

    public void setV_url(String v_url) {
        this.v_url = v_url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "vid='" + vid + '\'' +
                ", v_title='" + v_title + '\'' +
                ", v_img='" + v_img + '\'' +
                ", score=" + score +
                ", classify='" + classify + '\'' +
                ", protagonist='" + protagonist + '\'' +
                ", description='" + description + '\'' +
                ", v_url='" + v_url + '\'' +
                '}';
    }
}
