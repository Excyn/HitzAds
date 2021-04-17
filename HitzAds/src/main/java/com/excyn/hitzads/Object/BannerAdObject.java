package com.excyn.hitzads.Object;

import com.excyn.hitzads.Enums.AdType;

public class BannerAdObject extends AdObject{

    public BannerAdObject() {
    }

    public BannerAdObject(String adId, AdType adType, String title, String body, String image_url, String video_url, String link, String button_text, String button_color, String button_text_color) {
        super(adId, adType, title, body, image_url, video_url, link, button_text, button_color, button_text_color);
    }

    @Override
    public AdObject getTestAd(AdType adType) {
        AdObject adObject = new BannerAdObject();
        if(adType.equals(AdType.BANNER_TEXT)){
            adObject.setAdId("1");
            adObject.setAdType(AdType.BANNER_TEXT);
            adObject.setTitle("Test Hitz Ad");
            adObject.setBody("This is Test Ad Body From HitzAd Server !! Yeah It works fine !!");
            adObject.setImage_url("https://github.com/Excyn/HitzAds/raw/main/logo.JPG");
            adObject.setLink("https://www.google.com/");
            return adObject;
        }else if(adType.equals(AdType.BANNER_LARGE_TEXT)){
            adObject.setAdId("1");
            adObject.setAdType(AdType.BANNER_LARGE_TEXT);
            adObject.setTitle("Test Large Hitz Ad");
            adObject.setBody("This is Large Test Ad Body From HitzAd Server !! Yeah It works fine !!");
            adObject.setImage_url("https://github.com/Excyn/HitzAds/raw/main/logo.JPG");
            adObject.setLink("https://www.google.com/");
            return adObject;
        }else if(adType.equals(AdType.BANNER_IMAGE)){
            adObject.setAdId("1");
            adObject.setAdType(AdType.BANNER_IMAGE);
            adObject.setImage_url("https://picsum.photos/720/60");
            adObject.setLink("https://www.google.com/");
            return adObject;
        }else if(adType.equals(AdType.BANNER_LARGE_IMAGE)){
            adObject.setAdId("1");
            adObject.setAdType(AdType.BANNER_LARGE_IMAGE);
            adObject.setImage_url("https://picsum.photos/720/100");
            adObject.setLink("https://www.google.com/");
            return adObject;
        }
        return null;
    }


}
