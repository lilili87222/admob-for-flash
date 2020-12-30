package so.cuo.platform.admob;
import com.adobe.fre.FREContext;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class VideoHandler {
    private FREContext context;
    private RewardedAd rewardedVideoAd;
    private RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
        @Override
        public void onRewardedAdLoaded() {
            context.dispatchStatusEventAsync(AdEvent.onVideoReceive, "FULL_ADMOB_LEVEL");
        }

        @Override
        public void onRewardedAdFailedToLoad(int errorCode) {
            context.dispatchStatusEventAsync(AdEvent.onVideoLoadFail, BannerHandler.errorString(errorCode));
        }
    };

    public void setContext(FREContext ctx) {
        this.context = ctx;
    }

    RewardedAdCallback adCallback = new RewardedAdCallback() {
        public void onRewardedAdOpened() {
            context.dispatchStatusEventAsync(AdEvent.onVideoOpen, "FULL_ADMOB_LEVEL");
        }

        public void onRewardedAdClosed() {
            context.dispatchStatusEventAsync(AdEvent.onVideoClosed, "FULL_ADMOB_LEVEL");
        }

        @Override
        public void onUserEarnedReward(com.google.android.gms.ads.rewarded.RewardItem rewardItem) {
            context.dispatchStatusEventAsync(AdEvent.onVideoRewarded, rewardItem.getAmount() + "");
        }

        public void onRewardedAdFailedToShow(int errorCode) {
            context.dispatchStatusEventAsync(AdEvent.onVideoFailToShow, errorCode + "");
        }
    };

    public void loadVideo(final String videoID, final String param) {
            if (rewardedVideoAd != null && rewardedVideoAd.isLoaded()) return;
            rewardedVideoAd = new RewardedAd(context.getActivity(), videoID);
            rewardedVideoAd.loadAd(BannerHandler.getRequest(param), adLoadCallback);
    }

    public boolean isVideoReady() {
        if (rewardedVideoAd == null) return false;
        return rewardedVideoAd.isLoaded();
    }

    public void showVideo() {
        if (rewardedVideoAd != null && rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show(context.getActivity(), adCallback);
        }
    }
}
