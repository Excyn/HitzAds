package com.excyn.hitzadsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.excyn.hitzads.Enums.AdType;
import com.excyn.hitzads.Handler.BannerAdHandler;
import com.excyn.hitzads.Listeners.AdEventListener;
import com.excyn.hitzads.Object.BannerAdObject;
import com.excyn.hitzads.Request.AdRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BannerAdHandler bannerAdHandler = findViewById(R.id.adView);
        bannerAdHandler.setAdEventListener(new AdEventListener() {
            @Override
            public void onAdLoad() {
                Log.d("HitzAdEvent","loaded");
            }

            @Override
            public void onAdFailedToLoad(String error) {
                Log.d("HitzAdEvent","failed");
            }

            @Override
            public void onAdOpened() {
                Log.d("HitzAdEvent","opened");
            }

            @Override
            public void onAdClicked() {
                Log.d("HitzAdEvent","clicked");
            }

            @Override
            public void onAdClosed() {
                Log.d("HitzAdEvent","closed");
            }
        });

        bannerAdHandler.loadAd(new BannerAdObject().getTestAd(AdType.BANNER_TEXT));
        //bannerAdHandler.loadAd(new AdRequest(MainActivity.this,"https://jsonkeeper.com/b/P5KF"));
    }
}