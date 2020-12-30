package so.cuo.platform.admob;

import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class ClassicBannerHandler extends BannerHandler {
	protected View initAdView(String instanceName, String admobID, AdSize size) {
		AdView adView = new AdView(context.getActivity());
		adView.setAdUnitId(admobID);
		adView.setAdListener(new BannerAdListener(instanceName));
		adView.setAdSize(size);
		return adView;
	}

	protected void destroyView(View banner) {
		AdView view = (AdView) banner;
		view.destroy();
		view.setAdListener(null);
	}

	protected void requestBannerAD(View banner, String param) {
		AdView view = (AdView) banner;
		view.loadAd(BannerHandler.getRequest(param));
	}

	class BannerAdListener extends AdListener {
		private String bannerName;

		 BannerAdListener(String instanceName) {
			this.bannerName = instanceName;
		}

		@Override
		public void onAdClosed() {
			super.onAdClosed();
			context.dispatchStatusEventAsync(AdEvent.onBannerDismiss, "onDidDismissScreen");
		}

		@Override
		public void onAdFailedToLoad(int errorCode) {
			super.onAdFailedToLoad(errorCode);
			context.dispatchStatusEventAsync(AdEvent.onBannerFailedReceive, errorString(errorCode));
		}

		@Override
		public void onAdLeftApplication() {
			super.onAdLeftApplication();
			context.dispatchStatusEventAsync(AdEvent.onBannerLeaveApplication, "adViewWillLeaveApplication");
		}

		@Override
		public void onAdLoaded() {
			super.onAdLoaded();
			View view = bannerList.get(bannerName);
			repositionBanner(bannerName);
			context.dispatchStatusEventAsync(AdEvent.onBannerReceive, BannerHandler.convertPxOrDip(context.getActivity(), view.getWidth()) + "_" + BannerHandler.convertPxOrDip(context.getActivity(), view.getHeight()));
		}

		@Override
		public void onAdOpened() {
			super.onAdOpened();
			context.dispatchStatusEventAsync(AdEvent.onBannerPresent, "adViewWillPresentScreen");
		}

	}
}
