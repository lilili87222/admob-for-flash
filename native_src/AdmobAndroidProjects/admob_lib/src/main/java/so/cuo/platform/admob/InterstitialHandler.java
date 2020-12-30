package so.cuo.platform.admob;

import com.adobe.fre.FREContext;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialHandler {
	private FREContext context;
	private InterstitialAd interstitial;

	public void setContext(FREContext ctx) {
		this.context = ctx;
	}

	public void loadInterstitial(String admobID, String param) {
		if (interstitial == null) {
			interstitial = new InterstitialAd(context.getActivity());
			interstitial.setAdUnitId(admobID);
			interstitial.setAdListener(new InterstitialAdListener());
		}
		if(!interstitial.isLoading()){
			interstitial.loadAd(BannerHandler.getRequest(param));
		}
	}

	public boolean isInterstitialReady() {
		return interstitial!=null&&interstitial.isLoaded();
	}

	public void showInterstitial() {
		if (isInterstitialReady()) {
			interstitial.show();
		}
	}

	class InterstitialAdListener extends AdListener {

		 InterstitialAdListener() {
			super();
		}

		@Override
		public void onAdClosed() {
			super.onAdClosed();
			context.dispatchStatusEventAsync(AdEvent.onInterstitialDismiss, "FULL_ADMOB_LEVEL");
		}

		@Override
		public void onAdFailedToLoad(int errorCode) {
			super.onAdFailedToLoad(errorCode);
			context.dispatchStatusEventAsync(AdEvent.onInterstitialFailedReceive, BannerHandler.errorString(errorCode));
		}

		@Override
		public void onAdLeftApplication() {
			super.onAdLeftApplication();
			context.dispatchStatusEventAsync(AdEvent.onInterstitialLeaveApplication, "FULL_ADMOB_LEVEL");
		}

		@Override
		public void onAdLoaded() {
			super.onAdLoaded();
			context.dispatchStatusEventAsync(AdEvent.onInterstitialReceive, "FULL_ADMOB_LEVEL");
		}

		@Override
		public void onAdOpened() {
			super.onAdOpened();
			context.dispatchStatusEventAsync(AdEvent.onInterstitialPresent, "FULL_ADMOB_LEVEL");
		}
	}
}
