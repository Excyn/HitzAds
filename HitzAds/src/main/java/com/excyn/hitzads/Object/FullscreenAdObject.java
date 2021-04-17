package com.excyn.hitzads.Object;

import com.excyn.hitzads.Enums.AdType;

public class FullscreenAdObject extends AdObject{

    public FullscreenAdObject() {
    }

    public FullscreenAdObject(String adId, AdType adType, String title, String body, String image_url, String video_url, String link, String button_text, String button_color, String button_text_color) {
        super(adId, adType, title, body, image_url, video_url, link, button_text, button_color, button_text_color);
    }

    @Override
    public AdObject getTestAd(AdType adType) {
        AdObject adObject = new FullscreenAdObject();
        if(adType.equals(AdType.FULLSCREEN_IMAGE)){
            adObject.setAdId("1");
            adObject.setAdType(AdType.FULLSCREEN_IMAGE);
            adObject.setTitle("Test Hitz Image Ad");
            adObject.setBody("This is Test Fullscreen Image Ad Body From HitzAd Server !! Yeah It works fine !! Here we can show longer content and also a customizable button below this text. Yeah it's a great feature");
            adObject.setImage_url("https://images.squarespace-cdn.com/content/v1/58ab6de88419c2e78d86aae9/1566865224151-008W4IA6BUDI92ZFLV7M/ke17ZwdGBToddI8pDm48kGfiFqkITS6axXxhYYUCnlRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpxQ1ibo-zdhORxWnJtmNCajDe36aQmu-4Z4SFOss0oowgxUaachD66r8Ra2gwuBSqM/image-asset.png");
            adObject.setButton_text("Install App");
            adObject.setButton_color("#FF7171");
            adObject.setButton_text_color("#ffffff");
            adObject.setLink("https://www.hitxgh.com/");
            return adObject;
        }else if(adType.equals(AdType.FULLSCREEN_VIDEO)) {
            adObject.setAdId("1");
            adObject.setAdType(AdType.FULLSCREEN_VIDEO);
            adObject.setTitle("Test Hitz Video Ad");
            adObject.setBody("This is Test Fullscreen Video Ad Body From HitzAd Server !! Yeah It works fine !! Here we can show longer content and also a customizable button below this text. Yeah it's a great feature");
            adObject.setImage_url("https://images.squarespace-cdn.com/content/v1/58ab6de88419c2e78d86aae9/1566865224151-008W4IA6BUDI92ZFLV7M/ke17ZwdGBToddI8pDm48kGfiFqkITS6axXxhYYUCnlRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpxQ1ibo-zdhORxWnJtmNCajDe36aQmu-4Z4SFOss0oowgxUaachD66r8Ra2gwuBSqM/image-asset.png");
            adObject.setVideo_url("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
            adObject.setButton_text("Install App");
            adObject.setButton_color("#FF7171");
            adObject.setButton_text_color("#ffffff");
            adObject.setLink("https://www.hitxgh.com/");
            return adObject;
        }
        return null;
    }


}
