package com.chirag.news.NewsStand;


public class NewsStand {
    private String _id;
    private String title;
    private String image;
    private String content;
    private String state;

    public NewsStand(String _id, String title, String image, String content, String state) {
        this._id = _id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.state = state;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getState() {
        return state;
    }
}