package com.excyn.hitzads.Object;

import com.excyn.hitzads.Enums.AdType;

public abstract class AdObject implements IAdObject {

    public String adId;
    public AdType adType;
    public String title;
    public String body;
    public String image_url;
    public String video_url;
    public String link;
    public String button_text;
    public String button_color;
    public String button_text_color;
    private String web_ad_link;

    public AdObject(){}

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getButton_text() {
        return button_text;
    }

    public void setButton_text(String button_text) {
        this.button_text = button_text;
    }

    public String getButton_color() {
        return button_color;
    }

    public void setButton_color(String button_color) {
        this.button_color = button_color;
    }

    public String getButton_text_color() {
        return button_text_color;
    }

    public void setButton_text_color(String button_text_color) {
        this.button_text_color = button_text_color;
    }

    public String getWeb_ad_link() {
        return web_ad_link;
    }

    public void setWeb_ad_link(String web_ad_link) {
        this.web_ad_link = web_ad_link;
    }
}
