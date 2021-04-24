package com.excyn.hitzads.Handler;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excyn.hitzads.Listeners.AdRequestListener;
import com.excyn.hitzads.Listeners.IAdEventListener;
import com.excyn.hitzads.Object.AdObject;
import com.excyn.hitzads.R;
import com.excyn.hitzads.Enums.AdType;
import com.excyn.hitzads.Request.AdRequest;

public class BannerAdHandler extends FrameLayout {

    private Context mContext;
    private static IAdEventListener adEventListener;
    private static AdObject adObject;
    private LayoutInflater layoutInflater;

    private LinearLayout bannerLayout, largeBannerLayout, bannerImageLayout, largeBannerImageLayout;
    private LinearLayout bannerBody, bannerBodyLarge;
    private ImageView adImage,adImageLarge,adFullImage, adFullImageLarge;
    private TextView adTitle, adContent,adTitleLarge, adContentLarge;


    public BannerAdHandler (Context context) {
        super(context);
        this.mContext = context;
        inflate();
        bindViews();

    }

    public BannerAdHandler(Context context, AttributeSet attrs){
        super(context,attrs);
        this.mContext = context;
        inflate();
        bindViews();
    }
    public BannerAdHandler(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        this.mContext = context;
        inflate();
        bindViews();
    }



    private void inflate() {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.hitz_banner_ad, this, true);
    }

    private void bindViews() {
        bannerLayout = findViewById(R.id.banner);
        adImage = findViewById(R.id.banner_image);
        bannerBody = findViewById(R.id.banner_body);
        adTitle = findViewById(R.id.banner_title);
        adContent = findViewById(R.id.banner_content);

        largeBannerLayout = findViewById(R.id.banner_large);
        adImageLarge = findViewById(R.id.banner_large_image);
        bannerBodyLarge = findViewById(R.id.banner_large_body);
        adTitleLarge = findViewById(R.id.banner_large_title);
        adContentLarge = findViewById(R.id.banner_large_content);

        bannerImageLayout = findViewById(R.id.banner_image_full);
        adFullImage = findViewById(R.id.banner_full_image);

        largeBannerImageLayout = findViewById(R.id.banner_image_full_large);
        adFullImageLarge = findViewById(R.id.banner_full_image_large);

    }

    public void loadAd(AdObject adObject) {
        this.adObject = adObject;
        if(adObject!=null) {
            loadBannerAd();
        }else{
            if (adEventListener != null) {
                adEventListener.onAdFailedToLoad("Loading Failed");
            }
        }
    }

    public void loadAd(AdRequest adRequest) {
        adRequest.setAdRequestListener(new AdRequestListener() {
            @Override
            public void onAdLoad() {
                adObject = adRequest.getBannerAdObject();
                if(adObject!=null) {
                    loadBannerAd();
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

    public void loadBannerAd(){

        if(adObject.adType.equals(AdType.BANNER_TEXT)){
            bannerLayout.setVisibility(VISIBLE);
            largeBannerLayout.setVisibility(GONE);
            bannerImageLayout.setVisibility(GONE);
            largeBannerImageLayout.setVisibility(GONE);

            Log.d("HitzAds",adObject.getBody());
            bannerBody.setVisibility(VISIBLE);
            if(mContext!=null) {
                Glide.with(mContext.getApplicationContext()).load(adObject.getImage_url()).into(adImage);
            }
            adTitle.setText(adObject.getTitle());
            adContent.setText(adObject.getBody());
            if(adEventListener!=null) {
                adEventListener.onAdLoad();
            }
            bannerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    mContext.startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                }
            });
        }else if(adObject.adType.equals(AdType.BANNER_LARGE_TEXT)){
            bannerLayout.setVisibility(GONE);
            largeBannerLayout.setVisibility(VISIBLE);
            bannerImageLayout.setVisibility(GONE);
            largeBannerImageLayout.setVisibility(GONE);

            Log.d("HitzAds",adObject.getBody());
            bannerBodyLarge.setVisibility(VISIBLE);
            if(mContext!=null) {
                Glide.with(mContext.getApplicationContext()).load(adObject.getImage_url()).into(adImageLarge);
            }
            adTitleLarge.setText(adObject.getTitle());
            adContentLarge.setText(adObject.getBody());
            if(adEventListener!=null) {
                adEventListener.onAdLoad();
            }
            largeBannerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    mContext.startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                }
            });
        }else if(adObject.adType.equals(AdType.BANNER_IMAGE)){
            bannerLayout.setVisibility(GONE);
            largeBannerLayout.setVisibility(GONE);
            bannerImageLayout.setVisibility(VISIBLE);
            largeBannerImageLayout.setVisibility(GONE);
            if(mContext!=null) {
                Glide.with(mContext.getApplicationContext()).load(adObject.getImage_url()).into(adFullImage);
            }
            if(adEventListener!=null) {
                adEventListener.onAdLoad();
            }
            bannerImageLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    mContext.startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                }
            });
        }else if(adObject.adType.equals(AdType.BANNER_LARGE_IMAGE)){
            bannerLayout.setVisibility(GONE);
            largeBannerLayout.setVisibility(GONE);
            bannerImageLayout.setVisibility(GONE);
            largeBannerImageLayout.setVisibility(VISIBLE);
            if(mContext!=null) {
                Glide.with(mContext.getApplicationContext()).load(adObject.getImage_url()).into(adFullImageLarge);
            }
            if(adEventListener!=null) {
                adEventListener.onAdLoad();
            }
            largeBannerImageLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adObject.getLink()));
                    mContext.startActivity(browserIntent);
                    if(adEventListener!=null) {
                        adEventListener.onAdClicked();
                    }
                }
            });
        }
    }



    public void setAdEventListener(IAdEventListener adEventListener){
        this.adEventListener = adEventListener;
    }

}
