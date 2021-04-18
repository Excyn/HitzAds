# HitzAds
HitzAds - An Android Library for Mobile Ads

This Library can be used to run house ads promoting your apps, websites, or in-app content to existing app users. 

HitzAds currently support following types of ads

    * BANNER_TEXT
    * BANNER_LARGE_TEXT
    * BANNER_IMAGE
    * BANNER_LARGE_IMAGE
    * FULLSCREEN_IMAGE
    * FULLSCREEN_VIDEO

## Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}dependencies {
	        implementation 'com.github.Excyn:HitzAds:6fbdd74e65'
	}
  
## Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Excyn:HitzAds:6fbdd74e65'
	}

## Step 3 Build the project and start using

### Banner Ads

Add com.excyn.hitzads.Handler.BannerAdHandler to any place in your activity layout

	<com.excyn.hitzads.Handler.BannerAdHandler
            android:id="@+id/adView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
	   
In your Activity java class add the followings.

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
            public void onAdSkipped() {
                Log.d("HitzAdEvent","skipped");
            }

            @Override
            public void onAdClosed() {
                Log.d("HitzAdEvent","closed");
            }
        });
	
	//Load Test ADS (OPTIONAL)
        //bannerAdHandler.loadAd(new BannerAdObject().getTestAd(AdType.BANNER_TEXT));
	
	//Load Real ADS
        bannerAdHandler.loadAd(new AdRequest(MainActivity.this,"url_to_banner_ad_objects_json_file"));
	
	
### FullScreen Ads

Add following code to any of the place in your application when you need to show Fullscreen Ad

		FullscreenAdHandler fullscreenAdHandler = new FullscreenAdHandler();
                fullscreenAdHandler.initiateFullScreenAds(MainActivity.this);
                fullscreenAdHandler.setAdEventListener(new AdEventListener() {
                    @Override
                    public void onAdLoad() {
                        Log.d("HitzAdEvent","loaded");
                        fullscreenAdHandler.showAd();
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
                    public void onAdSkipped() {
                        Log.d("HitzAdEvent","skipped");
                    }

                    @Override
                    public void onAdClosed() {
                        Log.d("HitzAdEvent","closed");
                    }
                });
		// Load Test ADS (OPTIONAL)
                //fullscreenAdHandler.loadAd(new FullscreenAdObject().getTestAd(AdType.FULLSCREEN_IMAGE));
		
		//Load Read ADS
                fullscreenAdHandler.loadAd(new AdRequest(getApplicationContext(),"url_to_fullscreen_ad_objects_json_file"));

## Step 4 Create your Ad Object JSON array and host it on a server to get json file url for AdRequest

### BANNER AD JSON OBJECT ARRAY 

Following json array is an example with 4 ad objects for all 4 types of supporting Banner Ad Types. Modify this array as you wish and host it on a server like https://jsonkeeper.com/ and use the url for AdRequest 

	[
  	{
	    "id": "1",
	    "adType": "BANNER_TEXT",
	    "title": "banner text ad",
	    "body": "body1",
	    "image_url": "https://raw.githubusercontent.com/Excyn/HitzAds/main/logo.JPG",
	    "video_url": "",
	    "link": "https://github.com/Excyn/HitzAds",
	    "button_text": "",
	    "button_color": "",
	    "button_text_color": ""
	  },
	  {
	    "id": "2",
	    "adType": "BANNER_LARGE_TEXT",
	    "title": "banner large text ad",
	    "body": "body2",
	    "image_url": "https://raw.githubusercontent.com/Excyn/HitzAds/main/logo.JPG",
	    "video_url": "",
	    "link": "https://github.com/Excyn/HitzAds",
	    "button_text": "",
	    "button_color": "",
	    "button_text_color": ""
	  },
	  {
	    "id": "2",
	    "adType": "BANNER_IMAGE",
	    "title": "",
	    "body": "",
	    "image_url": "https://picsum.photos/720/60",
	    "video_url": "",
	    "link": "https://github.com/Excyn/HitzAds",
	    "button_text": "",
	    "button_color": "",
	    "button_text_color": ""
	  },
	  {
	    "id": "2",
	    "adType": "BANNER_LARGE_IMAGE",
	    "title": "",
	    "body": "",
	    "image_url": "https://picsum.photos/720/100",
	    "video_url": "",
	    "link": "https://github.com/Excyn/HitzAds",
	    "button_text": "",
	    "button_color": "",
	    "button_text_color": ""
	  }
	]

### FULLSCREEN AD JSON OBJECT ARRAY 

Following json array is an example with 2 ad objects for all 2 types of supporting Fullscreen Ad Types. Modify this array as you wish and host it on a server like https://jsonkeeper.com/ and use the url for AdRequest 

	[
	  {
	    "id": "1",
	    "adType": "FULLSCREEN_IMAGE",
	    "title": "fullscreen text ad",
	    "body": "This is the Ad Body text",
	    "image_url": "https://raw.githubusercontent.com/Excyn/HitzAds/main/logo.JPG",
	    "video_url": "",
	    "link": "https://github.com/Excyn/HitzAds",
	    "button_text": "Download",
	    "button_color": "#FF7171",
	    "button_text_color": "#ffffff"
	  },
	  {
	    "id": "2",
	    "adType": "FULLSCREEN_VIDEO",
	    "title": "fullscreen large vide ad",
	    "body": "This is the Ad Body text",
	    "image_url": "https://picsum.photos/512/512",
	    "video_url": "http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4",
	    "link": "https://github.com/Excyn/HitzAds",
	    "button_text": "Download",
	    "button_color": "#FF7171",
	    "button_text_color": "#ffffff"
	  }
	]
	
## Finally Run - Demo App can be run from this Repo 

![Demo App](https://raw.githubusercontent.com/Excyn/HitzAds/main/Demo.jpeg)

