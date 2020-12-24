package
{
	import flash.display.Sprite;
	
	import so.cuo.platform.admob.Admob;
	import so.cuo.platform.admob.AdmobEvent;
	import so.cuo.platform.admob.ExtraParameter;
	
	public class InterstitialDemo extends Sprite
	{
		private var admob:Admob= Admob.getInstance();
		public function InterstitialDemo()
		{
			super();
			if(admob.supportDevice){
				admob.initAdmobSDK(new ExtraParameter());
				admob.addEventListener(AdmobEvent.onBannerFailedReceive, onAdReceived);
				admob.addEventListener(AdmobEvent.onBannerReceive, onAdReceived);
				admob.cacheInterstitial("your_admob_Interstitial_id");
			}
		}
		protected function onAdReceived(event:AdmobEvent):void
		{
			if(event.type==AdmobEvent.onInterstitialReceive){
				admob.showInterstitial();
			}
		}
	}
}