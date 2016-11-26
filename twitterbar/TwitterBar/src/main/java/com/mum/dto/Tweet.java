package com.mum.dto;

public class Tweet {

    private String imageUrl;
    private String imageText;
    private String userName;
    private String link;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public Tweet(String imageUrl, String imageText,String userName, String link) {
        this.imageUrl = imageUrl;
        this.imageText = imageText;
        this.userName = userName;
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImageUrl[imageURL=" + imageUrl + ", text=" + imageText + ", link=" + link + "]";
    }

}
