package so.cuo.platform.admob;

import android.graphics.Point;

import com.adobe.fre.FREContext;

public class AdmobSwap {
	private FREContext context;
	private ClassicBannerHandler classicBannerHandler;
	private InterstitialHandler interstitialHandler;
	private VideoHandler videolHandler;
	public AdmobSwap(){
		classicBannerHandler=new ClassicBannerHandler();
		interstitialHandler=new InterstitialHandler();
	}
	public void setContext(FREContext ctx){
		this.context=ctx;
		classicBannerHandler.setContext(ctx);
		interstitialHandler.setContext(ctx);
		videolHandler=new VideoHandler();
		videolHandler.setContext(ctx);
	}
	public String showBannerABS(int x, int y, int w, int h, String admobID, String instanceName, String param) {
		instanceName=classicBannerHandler.createBanner(w, h,admobID, instanceName, param);
		 classicBannerHandler.showBannerABS(instanceName, x, y, param,true);
		 return instanceName;
	}

	public String showBanner(int position, int offY, int w, int h, String admobID, String instanceName, String param) {
		instanceName=classicBannerHandler.createBanner(w, h,admobID, instanceName, param);
		classicBannerHandler.showBanner(instanceName, position, offY, param,true);
		return instanceName;
	}

	public void hideBanner(String instanceName) {
		classicBannerHandler.hideBanner(instanceName);
	}

	public void loadInterstitial(String admobID,String param){
		interstitialHandler.loadInterstitial(admobID, param);
	}
	public boolean isInterstitialReady(){
		return interstitialHandler.isInterstitialReady();
	}
	public void showInterstitial(){
		interstitialHandler.showInterstitial();
	}
	interface  Crash{
		public void crash(String msg);
	}
	public void loadVideo(String admobID,String param){
		videolHandler.loadVideo(admobID, param);
	}
	public boolean isVideoReady(){
		return videolHandler.isVideoReady();
	}
	public void showVideo(){
		videolHandler.showVideo();
	}
	
	public String getScreenSize(){
		Point point=BannerHandler.getScrennSize(context.getActivity());
		return point.x+"_"+point.y;
	}
}
