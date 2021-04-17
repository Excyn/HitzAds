package com.excyn.hitzads.Request;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.excyn.hitzads.Enums.AdType;
import com.excyn.hitzads.Listeners.IAdRequestListener;
import com.excyn.hitzads.Object.AdObject;
import com.excyn.hitzads.Object.BannerAdObject;
import com.excyn.hitzads.Object.FullscreenAdObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class AdRequest {

    private String json_url;
    private Context context;
    private static ArrayList<AdObject> bannerAdObjects;
    private static ArrayList<AdObject> fullscreenAdObjects;

    private IAdRequestListener adRequestListener;

    public AdRequest(Context context, String json_url) {
        this.context = context;
        this.json_url = json_url;
    }

    public void fetchAdList() {
        bannerAdObjects = new ArrayList<AdObject>();
        fullscreenAdObjects = new ArrayList<AdObject>();

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, json_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String type = jsonObject.getString("adType");
                                AdType adType = null;
                                AdObject adObject = null;
                                 switch (type){
                                     case "BANNER_TEXT":
                                         adType = AdType.BANNER_TEXT;
                                         adObject = new BannerAdObject();
                                         break;
                                     case "BANNER_LARGE_TEXT":
                                         adType = AdType.BANNER_LARGE_TEXT;
                                         adObject = new BannerAdObject();
                                         break;
                                     case "BANNER_IMAGE":
                                         adType = AdType.BANNER_IMAGE;
                                         adObject = new BannerAdObject();
                                         break;
                                     case "BANNER_LARGE_IMAGE":
                                         adType = AdType.BANNER_LARGE_IMAGE;
                                         adObject = new BannerAdObject();
                                         break;
                                     case "FULLSCREEN_IMAGE":
                                         adType = AdType.FULLSCREEN_IMAGE;
                                         adObject = new FullscreenAdObject();
                                         break;
                                     case "FULLSCREEN_VIDEO":
                                         adType = AdType.FULLSCREEN_VIDEO;
                                         adObject = new FullscreenAdObject();
                                         break;
                                 }
                                adObject.setAdType(adType);
                                adObject.setAdId(jsonObject.getString("id"));
                                adObject.setTitle(jsonObject.getString("title"));
                                adObject.setBody(jsonObject.getString("body"));
                                adObject.setImage_url(jsonObject.getString("image_url"));
                                adObject.setVideo_url(jsonObject.getString("video_url"));
                                adObject.setLink(jsonObject.getString("link"));
                                adObject.setButton_text(jsonObject.getString("button_text"));
                                adObject.setButton_color(jsonObject.getString("button_color"));
                                adObject.setButton_text_color(jsonObject.getString("button_text_color"));

                                if(type.equals("BANNER_TEXT") || type.equals("BANNER_LARGE_TEXT") || type.equals("BANNER_IMAGE") || type.equals("BANNER_LARGE_IMAGE")){
                                    bannerAdObjects.add(adObject);
                                }

                                if(type.equals("FULLSCREEN_IMAGE") || type.equals("FULLSCREEN_VIDEO")){
                                    fullscreenAdObjects.add(adObject);
                                }

                            }
                            if(adRequestListener!=null){
                                adRequestListener.onAdLoad();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if(adRequestListener!=null){
                                adRequestListener.onAdFailedToLoad(e.getMessage());
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(adRequestListener!=null){
                    adRequestListener.onAdFailedToLoad(error.getMessage());
                }
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public AdObject getFullScreenAdObject(){
        if(fullscreenAdObjects.size()>0){
        Random rand = new Random();
        int id = rand.nextInt(fullscreenAdObjects.size() - 0 + 1) + 0;
        return fullscreenAdObjects.get(id);
        }else{
            return null;
        }
    }

    public AdObject getBannerAdObject(){
        if(bannerAdObjects.size()>0) {
            Random rand = new Random();
            int id = rand.nextInt(bannerAdObjects.size() - 0 + 1) + 0;
            return bannerAdObjects.get(id);
        }else{
            return null;
        }
    }

    public void setAdRequestListener(IAdRequestListener adRequestListener){
        this.adRequestListener = adRequestListener;
    }
}
