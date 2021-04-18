package com.excyn.hitzads.Handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.excyn.hitzads.Enums.AdType;
import com.excyn.hitzads.Listeners.AdRequestListener;
import com.excyn.hitzads.Listeners.IAdEventListener;
import com.excyn.hitzads.Object.AdObject;
import com.excyn.hitzads.R;
import com.excyn.hitzads.Request.AdRequest;

public class FullscreenAdHandler extends AppCompatActivity {

    private static AdObject adObject;
    private static Activity activity;

    private static IAdEventListener adEventListener;
    private LinearLayout fullImageLayout, fullVideoBody;
    private RelativeLayout fullVideoLayout;
    private ImageView fullscreenImage;
    private VideoView fullscreenVideo;
    private Button adButton,adButtonVideo,closeBtn;
    private TextView adTitle, adContent,adTitleVideo, adContentVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        bindViews();
    }

    public void initiateFullScreenAds(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();

    }

    private void bindViews() {
        setContentView(R.layout.hitz_fullscreen_ad);

        fullImageLayout = findViewById(R.id.fullscreen_image_text);
        fullscreenImage = findViewById(R.id.fullscreen_image);
        adTitle = findViewById(R.id.fullscreen_title);
        adContent = findViewById(R.id.fullscreen_content);
        adButton = findViewById(R.id.fullscreen_button);

        fullVideoLayout = findViewById(R.id.fullscreen_video_ad);
        fullscreenVideo = findViewById(R.id.fullscreen_video);
        adTitleVideo = findViewById(R.id.fullscreen_video_title);
        adContentVideo  = findViewById(R.id.fullscreen_video_content);
        adButtonVideo = findViewById(R.id.fullscreen_video_button);
        fullVideoBody = findViewById(R.id.fullscreen_video_body);

        closeBtn = findViewById(R.id.fullscreen_close);

    }


    public void loadAd(AdObject adObject) {
        this.adObject = adObject;
        if (adEventListener != null) {
            adEventListener.onAdLoad();
        }

    }

    public void loadAd(AdRequest adRequest) {
        adRequest.setAdRequestListener(new AdRequestListener() {
            @Override
            public void onAdLoad() {
                adObject = adRequest.getFullScreenAdObject();
                if(adObject!=null) {
                    if (adEventListener != null) {
                        adEventListener.onAdLoad();
                    }
                }else{
                    if (adEventListener != null) {
                        adEventListener.onAdFailedToLoad("Null Ad Loading Failed");
                    }
                }
            }

            @Override
            public void onAdFailedToLoad(String error) {
                if (adEventListener != null) {
                    adEventListener.onAdFailedToLoad(error);
                }
            }
        });
        adRequest.fetchAdList();
    }

    public void showAd(){
        if(adObject!=null) {
            if (adEventListener != null) {
                adEventListener.onAdOpened();
            }
            if(this.activity!=null) {
                Intent intent = new Intent(this.activity, FullscreenAdHandler.class);
                this.activity.startActivity(intent);
            }else{
                if (adEventListener != null) {
                    adEventListener.onAdFailedToLoad("AdHandler is not initiated yet");
                }
            }
        }else{
            if (adEventListener != null) {
                adEventListener.onAdFailedToLoad("Loading Failed");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adObject.adType.equals(AdType.FULLSCREEN_IMAGE)) {
            fullImageLayout.setVisibility(View.VISIBLE);
            fullVideoLayout.setVisibility(View.GONE);

            Log.d("HitzAds", adObject.getBody());
            Glide.with(FullscreenAdHandler.this).load(adObject.getImage_url()).into(fullscreenImage);

            adTitle.setText(adObject.getTitle());
            adContent.setText(adObject.getBody());
            adButton.setText(adObject.getButton_text());
            adButton.setBackgroundColor(Color.parseColor(adObject.getButton_color()));
            adButton.setTextColor(Color.parseColor(adObject.getButton_text_color()));
            adButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                }
            });

            closeBtn.setText("X");
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adEventListener!=null) {
                        adEventListener.onAdClosed();
                    }
                    finish();
                }
            });

        } else if (adObject.adType.equals(AdType.FULLSCREEN_VIDEO)) {
            fullImageLayout.setVisibility(View.GONE);
            fullVideoLayout.setVisibility(View.VISIBLE);

            Log.d("HitzAds", adObject.getBody());
            fullscreenVideo.setVideoURI(Uri.parse(adObject.getVideo_url()));
            fullscreenVideo.start();
            adTitleVideo.setText(adObject.getTitle());
            adContentVideo.setText(adObject.getBody());
            adButtonVideo.setText(adObject.getButton_text());
            adButtonVideo.setBackgroundColor(Color.parseColor(adObject.getButton_color()));
            adButtonVideo.setTextColor(Color.parseColor(adObject.getButton_text_color()));
            adButtonVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                }
            });
            closeBtn.setText(">>");
            fullscreenVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    closeBtn.setText("X");
                    fullVideoBody.setVisibility(View.VISIBLE);
                }
            });

            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adEventListener!=null) {
                        adEventListener.onAdClosed();
                    }
                    if(closeBtn.getText().equals("X")) {
                        finish();
                    }
                    if(closeBtn.getText().equals(">>")) {
                        fullscreenVideo.pause();
                        closeBtn.setText("X");
                        fullVideoBody.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void setAdEventListener(IAdEventListener adEventListener){
        this.adEventListener = adEventListener;
    }
}