package  so.cuo.platform.admob
{
	import flash.events.Event;
	/**
	 * ad event 
	 * ref  https://developers.google.com/mobile-ads-sdk/docs/admob/intermediate
	 **/
	public class AdmobEvent extends Event
	{
		/**
		 * Event which will be fired when  ad is clicked  and not leave app. will show a full screen in app ad
		 */
		public static const onBannerDismiss:String = "onBannerDismiss";
		/**
		 *Event which will be fired when fail to receive ad
		 **/
		public static const onBannerFailedReceive:String = "onBannerFailedReceive";
		/**
		 * Event which will be fired when ad clicked and will leave app.
		 */
		public static const onBannerLeaveApplication:String = "onBannerLeaveApplication";
		/**
		 * Event which will be fired when ad will been add to screen.
		 */
		public static const onBannerPresent:String = "onBannerPresent";
		/**
		 * Event which will be fired when ad received success 
		 */
		public static const onBannerReceive:String = "onBannerReceive";
		
		
		/**
		 * Event which will be fired when  ad is clicked  and not leave app. will show a full screen in app ad
		 */
		public static const onInterstitialDismiss:String = "onInterstitialDismiss";
		/**
		 *Event which will be fired when fail to receive ad
		 **/
		public static const onInterstitialFailedReceive:String = "onInterstitialFailedReceive";
		/**
		 * Event which will be fired when ad clicked and will leave app.
		 */
		public static const onInterstitialLeaveApplication:String = "onInterstitialLeaveApplication";
		/**
		 * Event which will be fired when ad will been add to screen.
		 */
		public static const onInterstitialPresent:String = "onInterstitialPresent";
		/**
		 * Event which will be fired when ad received success 
		 */
		public static const onInterstitialReceive:String = "onInterstitialReceive";
		
		
//		public static const onNativeBannerReceive:String = "onNativeBannerReceive";
//		public static const onNativeBannerFailed:String = "onNativeBannerFailed";
//		public static const onNativeBannerPresent:String = "onNativeBannerPresent";
//		public static const onNativeBannerDismiss:String = "onNativeBannerDismiss";
//		public static const onNativeBannerLeaveApplication:String = "onNativeBannerLeaveApplication";
		
		
		public static const onVideoRewarded:String = "onVideoRewarded";
		public static const onVideoLoadFail:String = "onVideoLoadFail";
		public static const onVideoReceive:String = "onVideoReceive";
		public static const onVideoOpen:String = "onVideoOpen";
		public static const onVideoPlaying:String = "onVideoPlaying";
		public static const onVideoClosed:String = "onVideoClosed";
		public static const onVideoLeaveApplication:String = "onVideoLeaveApplication";
//		public static const onVideoClick:String = "onVideoClick";
		public static const onVideoCompleted:String = "onVideoCompleted";
		
		public var instanceName:String;
		public var data:Object;
		public function AdmobEvent(type:String,data:*=null,instanceName:String=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.data=data;
			this.instanceName=instanceName;
		}
	}
}
