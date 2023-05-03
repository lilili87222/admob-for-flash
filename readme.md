Admob ANE for Flash Air
==============================
1. [Admob ANE Description](#admob-ane-description)
2. [Admob ANE For Air Features](#admob-ane-for-air-features)
3. [Quick Start](#quick-start)    
	1.[Init Admob ANE ](#1init-admob-ane)     
	2.[Add Admob Banner in adobe Air App](#2add-admob-banner-in-adobe-air-app)    
	3.[Remove Banner](#3remove-banner)    
	4.[Admob Native Express Ads](#4admob-native-express-ads)    
	5.[Admob ANE Show Interstitial ](#5admob-ane-show-interstitial )    
	6.[Custom Admob Banner Ad Sizes](#6custom-admob-banner-ad-sizes)    
	7.[Set Admob Target Param](#7set-admob-target-param)    
	8.[Ad Events](#8ad-events)    
        9.[Admob Rewarded Video](#9admob-rewarded-video)    
	10.[IOS  permission config](10ios--min-version-config)    
	11.[android permission config](#11android-permission-config)    
	12.[Screen size function](#12screen-size-function)    
	13.[ANE ID](#13ane-id)    
4. [change log](#change-log)
5. [Screenshots](#screenshots)
6. [Links](#links)
7. [License](#license)

## Deprecated 
This project not maintain any more,you can check out and edit or change to other project.

## Admob ANE Description
Admob Air Native Extention(Admob ANE) provides a way to integrate admob ads in Air ios and Air Android Game and app.
You can use it for Air iOS and Android App with the same actionscript  code,not need any change ,not need java
or oc.You not need Admob ANE for ios and Admob ANE for android Separate version any more with this Ane.

The Google Mobile Ads SDK is the latest generation in Google mobile advertising featuring refined ad formats and streamlined APIs for access to mobile ad networks and advertising solutions. The SDK enables Air mobile app developers to maximize their monetization in native mobile apps.





## Admob ANE For Air Features
- [x] Support IOS and Android in one ane with the same api
- [x] Support banner(All Banner Sizes)
- [x] Support Intersitial
- [x] Support Rewarded Video
- [x] Support landscape and portrait  and autoOrient
- [x] Support AdRequest targeting methods,such as children target,test mode
- [x] Support Air SDK 32 and Air 33
- [x] Support IOS 8 to ios 13 
- [x] Very simple API
- [x] Support android x64,x86,arm，and 32,64 bit support


## Quick Start
#### 1.Init Admob ANE 
Add Admob ane to air project build path , add the follow code in the script file
```
    import so.cuo.platform.admob.*;
    Admob.getInstance().initAdmobSDK(new ExtraParameter());

```
#### 2.Add Admob Banner in adobe Air App 
Here is the minimal code needed to show admob banner.
```
    Admob.getInstance().showBanner("your banner ID ",AdmobSize.BANNER_320x50,AdmobPosition.BOTTOM_CENTER);

```

The AdmobPosition class specifies where to place the banner. AdmobSize specifies witch size banner to show

#### 3.Remove Banner 
By default, banners are visible. To  hide a banner,
```
    Admob.getInstance().hideBanner();
```


#### 4.Admob ANE Show Interstitial 
How to integrate Interstitial into Air ios  app or flex android app?
Here is the minimal  code to create an interstitial.
```
    Admob.getInstance().cacheInterstitial("your Interstitial ID "); 
```
interstitials need to be loaded before shown. show at an appropriate
stopping point in your app, check that the interstitail is ready before
showing it:
```
    if (Admob.getInstance().isInterstitialReady()) {
      Admob.getInstance().showInterstitial();
    }
```
#### 5.Custom Admob Banner Ad Sizes
In addition to constants on _AdSize_, you can also create a custom size:
```
    //Create a 320x250 banner.
    AdSize adSize = new AdSize(320, 250);
    Admob.getInstance().showBannerAbsolute(adSize,0,30);
```
#### 6.Set Admob Target Param
set Admob target param such as test Ads and children app
If you want to test the ads or the your app with children target,you can set with admob ANE easy
```
       extraParam=new ExtraParameter();
	extraParam.testDeviceID="true";
	extraParam.isChildApp=true;//if is tagForChildDirectedTreatment,set true
        extraParam.nonPersonalizedAds=true;//if want to load non Personalized ads set true
	Admob.getInstance().showBanner("Your banner ID",AdmobSize.BANNER_320x50,AdmobPosition.BOTTOM_CENTER,80,extraParam);
```
#### 7.Ad Events
Both _Banner_ and _Interstitial_ contain the many ad events that you can
register for.    
Here we'll demonstrate setting ad events on a interstitial,and show interstitial when load success:
```
    Admob.getInstance().addEventListener(AdmobEvent.onInterstitialReceive, onAdEvent);
	private function onAdEvent(event:AdmobEvent):void
	{
		if (event.type == AdmobEvent.onBannerReceive)
		{
			trace(event.instanceName,event.data.width, event.data.height);
		}
		if (event.type == AdmobEvent.onInterstitialReceive)
		{
			Admob.getInstance().showInterstitial();
		}
	}
```

#### 9.Admob Rewarded Video
 _Video_ api is similar with _Interstitial_ 
    
Here we'll demonstrate setting ad events on a video,and show video when load success:
```
if(admob.isVideoReady()){
	admob.showVideo();
}else{
	admob.cacheVideo(videoID);
}
    Admob.getInstance().addEventListener(AdmobEvent.onVideoReceive, onVideoEvent);
	private function onVideoEvent(event:AdmobEvent):void
	{
		if (event.type == AdmobEvent.onVideoReceive)
		{
			trace("load video success,you can show video now");
		}
		
	}
```



###  10.IOS  min version config
MinimumOSVersion is required for ios ,admob reqired ios 8 and later;GADApplicationIdentifier is required by admob 7.1 and later
```
	<key>MinimumOSVersion</key>
        <string>12.0</string>
```
simple example
```
 <iPhone>
        <InfoAdditions><![CDATA[
 <key>GADApplicationIdentifier</key>
	<string>ca-app-pub-3940256099942544~1458002511</string>
			<key>UIDeviceFamily</key>
			<array>
				<string>1</string>
				<string>2</string>
			</array>
				<key>MinimumOSVersion</key>
        <string>12.0</string>
			<key>NSAppTransportSecurity</key>
<dict>
    <key>NSAllowsArbitraryLoads</key>
    <true/>
    <key>NSAllowsArbitraryLoadsForMedia</key>
    <true/>
    <key>NSAllowsArbitraryLoadsInWebContent</key>
    <true/>
</dict>
		]]></InfoAdditions>
        <requestedDisplayResolution>high</requestedDisplayResolution>
    </iPhone>
```


### 11.android permission config
Meta Config com.google.android.gms.ads.APPLICATION_ID  is required from admob 17
Please replace ca-app-pub-3940256099942544~3347511713 with your admob ID
```
<android>
        <manifestAdditions><![CDATA[
			<manifest android:installLocation="auto">
					<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
					<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
					<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
					<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
					<uses-permission android:name="com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE" />
					 <application>
						<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version" />
					      <activity android:name="com.google.android.gms.ads.AdActivity" android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize" android:theme="@android:style/Theme.Translucent"/>
						<receiver android:name="com.google.android.gms.measurement.AppMeasurementReceiver" android:enabled="true" android:exported="false" ></receiver>
					       <receiver android:name="com.google.android.gms.measurement.AppMeasurementInstallReferrerReceiver" android:enabled="true" android:exported="true" android:permission="android.permission.INSTALL_PACKAGES" >
							<intent-filter>
							    <action android:name="com.android.vending.INSTALL_REFERRER" />
							</intent-filter>
					        </receiver>

					        <service android:name="com.google.android.gms.measurement.AppMeasurementService" android:enabled="true" android:exported="false" />
					        <service android:name="com.google.android.gms.measurement.AppMeasurementJobService" android:enabled="true" android:exported="false" android:permission="android.permission.BIND_JOB_SERVICE" />
					        <receiver android:name="com.google.android.gms.measurement.AppMeasurementReceiver" android:enabled="true" android:exported="false" ></receiver>

					        <service android:name="com.google.android.gms.measurement.AppMeasurementService" android:enabled="true" android:exported="false" />
						  <meta-data android:name="com.google.android.gms.ads.APPLICATION_ID" android:value="ca-app-pub-3940256099942544~3347511713"/>
					</application>
			</manifest>

		]]></manifestAdditions>
    </android>
```

### 12.Screen size function
this will get  screen size ,unit is dp
```
Admob.getInstance().getScreenSize()

```

### 13.ANE ID
```
<extensionID>so.cuo.platform.admob</extensionID>
```

## version 20200525
1.update admob sdk     
2.support last ios   and  air 33.1 



## Screenshots
![ScreenShot](https://github.com/lilili87222/admob-for-flash/blob/master/images/screen.jpg?raw=true)



This Admob ANE  was a monetization ANE plugin for Flash Air community,More people use, making the api more friendly,the plugin more stable.Thank you for feedback questions and provide advice, thank you for the support and donations.

## Links
Download  https://github.com/lilili87222/admob-for-flash/archive/master.zip    
admob http://apps.admob.com    

## License
[Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html)
