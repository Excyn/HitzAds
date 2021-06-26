package com.excyn.hitzads.Handler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
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
    private static String closeButtonText;
    private static int closeButtonTextSize;
    private Activity activity;

    public static IAdEventListener adEventListener;
    private LinearLayout fullDownloadLayout, fullVideoBody, fullImageLayout;
    private RelativeLayout fullVideoLayout, fullWebLayout;
    private ImageView fullscreenDownloadImage, fullscreenImage;
    private VideoView fullscreenVideo;
    private Button adButton,adButtonVideo,closeBtn;
    private TextView adTitle, adContent,adTitleVideo, adContentVideo;
    private WebView adWebView;

    private static boolean closeAd = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        bindViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adObject.setVideo_url("");

    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void initiateFullScreenAds(Activity activity){
        this.activity = activity;
    }

    private void bindViews() {
        setContentView(R.layout.hitz_fullscreen_ad);

        fullDownloadLayout = findViewById(R.id.fullscreen_download);
        fullscreenDownloadImage = findViewById(R.id.fullscreen_download_image);
        adTitle = findViewById(R.id.fullscreen_download_title);
        adContent = findViewById(R.id.fullscreen_download_content);
        adButton = findViewById(R.id.fullscreen_download_button);

        fullVideoLayout = findViewById(R.id.fullscreen_video_ad);
        fullscreenVideo = findViewById(R.id.fullscreen_video);
        adTitleVideo = findViewById(R.id.fullscreen_video_title);
        adContentVideo  = findViewById(R.id.fullscreen_video_content);
        adButtonVideo = findViewById(R.id.fullscreen_video_button);
        fullVideoBody = findViewById(R.id.fullscreen_video_body);

        fullImageLayout = findViewById(R.id.fullscreen_image_view);
        fullscreenImage = findViewById(R.id.fullscreen_image);

        fullWebLayout = findViewById(R.id.fullscreen_web);
        adWebView = findViewById(R.id.webview);

        closeBtn = findViewById(R.id.fullscreen_close);

    }

    public void changeCloseButton(String text,int size){
        closeButtonText = text;
        closeButtonTextSize = size;
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
    protected void onDestroy() {
        super.onDestroy();
        if(adEventListener!=null) {
            adEventListener.onAdClosed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        closeAd = false;
        if(closeButtonText.isEmpty()){ closeButtonText = "X"; }
        if(closeButtonTextSize == 0 ){ closeButtonTextSize = 12; }
        closeBtn.setText(closeButtonText);
        closeBtn.setTextSize(Float.parseFloat(Integer.toString(closeButtonTextSize)));
        if (adObject.adType.equals(AdType.FULLSCREEN_DOWNLOAD)) {
            fullDownloadLayout.setVisibility(View.VISIBLE);
            fullImageLayout.setVisibility(View.GONE);
            fullVideoLayout.setVisibility(View.GONE);
            fullWebLayout.setVisibility(View.GONE);

            Log.d("HitzAds", adObject.getBody());
            Glide.with(FullscreenAdHandler.this).load(adObject.getImage_url()).into(fullscreenDownloadImage);

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
                    closeAd = true;
                    finish();
                }
            });

            closeBtn.setText(closeButtonText);
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adEventListener!=null) {
                        adEventListener.onAdClosed();
                    }
                    finish();
                }
            });

        } else if(adObject.adType.equals(AdType.FULLSCREEN_IMAGE)){
            fullDownloadLayout.setVisibility(View.GONE);
            fullImageLayout.setVisibility(View.VISIBLE);
            fullVideoLayout.setVisibility(View.GONE);
            fullWebLayout.setVisibility(View.GONE);

            Glide.with(FullscreenAdHandler.this).load(adObject.getImage_url()).into(fullscreenImage);
            fullscreenImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                    closeAd = true;
                    finish();
                }
            });

            closeBtn.setText(closeButtonText);
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
            fullDownloadLayout.setVisibility(View.GONE);
            fullImageLayout.setVisibility(View.GONE);
            fullVideoLayout.setVisibility(View.VISIBLE);
            fullWebLayout.setVisibility(View.GONE);

            Log.d("HitzAds", adObject.getBody());
            if(!adObject.getVideo_url().equals("")) {
                fullscreenVideo.setVideoURI(Uri.parse(adObject.getVideo_url()));
                fullscreenVideo.start();
                closeBtn.setText(">>");
            }else{
                fullscreenVideo.setVisibility(View.GONE);
                closeBtn.setText(closeButtonText);
                fullVideoBody.setVisibility(View.VISIBLE);
            }
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
                    closeAd = true;
                    finish();
                }
            });
            fullscreenVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    closeBtn.setText(closeButtonText);
                    fullVideoBody.setVisibility(View.VISIBLE);
                }
            });

            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(closeBtn.getText().equals(closeButtonText)) {
                        if(adEventListener!=null) {
                            adEventListener.onAdClosed();
                        }
                        finish();
                    }
                    if(closeBtn.getText().equals(">>")) {
                        closeBtn.setText(closeButtonText);
                        fullVideoBody.setVisibility(View.VISIBLE);
                        if(adEventListener!=null) {
                            adEventListener.onAdSkipped();
                        }
                        fullscreenVideo.pause();
                    }
                }
            });
        } else if (adObject.adType.equals(AdType.FULLSCREEN_WEB)) {
            fullDownloadLayout.setVisibility(View.GONE);
            fullImageLayout.setVisibility(View.GONE);
            fullVideoLayout.setVisibility(View.GONE);
            fullWebLayout.setVisibility(View.VISIBLE);

            adWebView.loadUrl(adObject.getWeb_ad_link());

            closeBtn.setText(closeButtonText);
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adEventListener!=null) {
                        adEventListener.onAdClosed();
                    }
                    finish();
                }
            });
        }
    }

    public void setAdEventListener(IAdEventListener adEventListener){
        FullscreenAdHandler.adEventListener = adEventListener;
    }
}
