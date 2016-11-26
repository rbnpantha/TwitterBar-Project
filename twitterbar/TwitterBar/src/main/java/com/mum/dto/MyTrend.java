package com.mum.dto;

public class MyTrend {

    private String name;
    private String linkUrl;

    public MyTrend(String name, String linkUrl) {
        this.name = name;
        this.linkUrl = linkUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

}
