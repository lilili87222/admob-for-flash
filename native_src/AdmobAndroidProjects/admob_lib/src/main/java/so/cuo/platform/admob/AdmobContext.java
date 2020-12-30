package so.cuo.platform.admob;



import java.util.HashMap;

import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import org.json.JSONObject;

public class AdmobContext extends FREContext {
    private static final String initAdmobSDK="admobInitAdmobSDK";
    private static final String hideBanner="admobHideBanner";
    private static final String showBannerAbsolute="admobShowBannerAbsolute";
    private static final String showBanner="admobShowBanner";
	

	
	private static final String isInterstitialReady="admobIsInterstitialReady";
    private static final String showInterstitial="admobShowInterstitial";
    private static final String cacheInterstitial="admobCacheInterstitial";

    private static final String isVideoReady="admobIsVideoReady";
    private static final String showVideo="admobShowVideo";
    private static final String cacheVideo="admobCacheVideo";

    private static final String setAppMuted="admobSetAppMuted";
    private static final String setAppVolume="admobSetAppVolume";

    private static final String getScreenSize="getScreenSize";
    private static final String startAnalytics="admobStartAnalytics";
    private static final String logEvent="admobLogEvent";
	private AdmobSwap admobSwap = new AdmobSwap();

	public void initialize() {
		admobSwap.setContext(this);
	}

	@Override
	public void dispose() {
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functionMap = new HashMap<>();

		functionMap.put(AdmobContext.cacheVideo, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				String interstitialKey = getString(arg1, 0);
				String param = getString(arg1, 1);
				admobSwap.loadVideo(interstitialKey, param);
				return null;
			}
		});
		functionMap.put(AdmobContext.isVideoReady, new FREFunction() {
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				try {
					return FREObject.newObject(admobSwap.isVideoReady());
				} catch (FREWrongThreadException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		functionMap.put(AdmobContext.showVideo, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				admobSwap.showVideo();
				return null;
			}
		});
		
		functionMap.put(AdmobContext.cacheInterstitial, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				String interstitialKey = getString(arg1, 0);
				String param = getString(arg1, 1);
				admobSwap.loadInterstitial(interstitialKey, param);
				return null;
			}
		});
		functionMap.put(AdmobContext.isInterstitialReady, new FREFunction() {
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				try {
					return FREObject.newObject(admobSwap.isInterstitialReady());
				} catch (FREWrongThreadException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		functionMap.put(AdmobContext.showInterstitial, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				admobSwap.showInterstitial();
				return null;
			}
		});

		

		functionMap.put(AdmobContext.showBanner, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				int position = getInt(arg1, 0);
				int offY = getInt(arg1, 1);
				int w = getInt(arg1, 2);
				int h = getInt(arg1, 3);
				String admobID = getString(arg1, 4);
				String instanceName = getString(arg1, 5);
				String param = getString(arg1, 6);
//				String bannerType = getString(arg1, 7);
				instanceName=admobSwap.showBanner(position, offY, w, h, admobID, instanceName, param);
				try {
					return FREObject.newObject(instanceName);
				} catch (FREWrongThreadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
		functionMap.put(AdmobContext.showBannerAbsolute, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				int x = getInt(arg1, 0);
				int y = getInt(arg1, 1);
				int w = getInt(arg1, 2);
				int h = getInt(arg1, 3);
				String admobID = getString(arg1, 4);
				String instanceName = getString(arg1, 5);
				String param = getString(arg1, 6);
//				String bannerType = getString(arg1, 7);
				instanceName=admobSwap.showBannerABS(x, y, w, h, admobID, instanceName, param);
				try {
					return FREObject.newObject(instanceName);
				} catch (FREWrongThreadException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		functionMap.put(AdmobContext.hideBanner, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				String instanceNameString = getString(arg1, 0);
				admobSwap.hideBanner(instanceNameString);
				return null;
			}
		});


		functionMap.put(AdmobContext.getScreenSize,new FREFunction(){
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				try {
					return FREObject.newObject(admobSwap.getScreenSize());
				} catch (FREWrongThreadException e) {
					e.printStackTrace();
				}
				return null;
			}
			
		});
		functionMap.put(AdmobContext.setAppMuted,new FREFunction(){
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				try {
					MobileAds.setAppMuted(getBoolean(arg1,0));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
		});
		functionMap.put(AdmobContext.setAppVolume,new FREFunction(){
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				try {
					MobileAds.setAppVolume((float)arg1[0].getAsDouble());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
		});
		functionMap.put(AdmobContext.initAdmobSDK, new FREFunction() {
			@Override
			public FREObject call(FREContext arg0, FREObject[] arg1) {
				try{
					MobileAds.initialize(arg0.getActivity(), new OnInitializationCompleteListener() {
						@Override
						public void onInitializationComplete(InitializationStatus initializationStatus) {
						}
					});
				}catch (Exception e){
//					e.printStackTrace();
				}
					if(arg1.length>0){
						try {
							String jsonProperties=arg1[0].getAsString();
							RequestConfiguration.Builder adbuilder=MobileAds.getRequestConfiguration().toBuilder();
							JSONObject jsonObject=new JSONObject(jsonProperties);
							if(jsonObject.has("isChildApp")&&jsonObject.getBoolean("isChildApp")){
								adbuilder.setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE);
							}
							if(jsonObject.has("isUnderAgeOfConsent")&&jsonObject.getBoolean("isUnderAgeOfConsent")){
								adbuilder.setTagForUnderAgeOfConsent(RequestConfiguration.TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE);
							}
							if(jsonObject.has("maxAdContentRating")){
								String max=jsonObject.getString("maxAdContentRating");
								if(max!=null&&!(max=max.trim()).isEmpty()){
									adbuilder .setMaxAdContentRating(max);
								}
							}
							if(jsonObject.has("volume")){
								int appVolume=jsonObject.getInt("volume");
								if(appVolume>100||appVolume<0)appVolume=100;
								MobileAds.setAppVolume(appVolume/100);
							}
							if(jsonObject.has("isAppMuted")&&jsonObject.getBoolean("isAppMuted")){
//								Log.w("Ads","setAppMutedtrue");
								MobileAds.setAppMuted(jsonObject.getBoolean("isAppMuted"));
							}
							MobileAds.setRequestConfiguration(adbuilder.build());
						}catch (Exception e){
							e.printStackTrace();
						}
					}

				return null;
			}
		});
		return functionMap;
	}

	protected int getInt(FREObject[] ps, int index) {
		if (index < 0 || index > ps.length - 1) {
			return 0;
		}
		if (ps[index] == null) {
			return 0;
		}
		try {
			return ps[index].getAsInt();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	protected boolean getBoolean(FREObject[] ps, int index) {
		if (index < 0 || index > ps.length - 1) {
			return false;
		}
		if (ps[index] == null) {
			return false;
		}
		try {
			return ps[index].getAsBool();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	protected String getString(FREObject[] ps, int index) {
		if (index < 0 || index > ps.length - 1) {
			return null;
		}
		if (ps[index] == null) {
			return null;
		}
		try {
			return ps[index].getAsString();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
