package
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	
	import so.cuo.platform.admob.Admob;
	import so.cuo.platform.admob.AdmobEvent;
	import so.cuo.platform.admob.ExtraParameter;

	public class EventDemo extends Sprite
	{
		private var admob:Admob= Admob.getInstance();

		public function EventDemo()
		{
			super();
			if (admob.supportDevice)
			{
				admob.initAdmobSDK(new ExtraParameter());//Interstitial and banner use the same key
				admob.addEventListener(AdmobEvent.onBannerFailedReceive, onAdReceived);
				admob.addEventListener(AdmobEvent.onBannerReceive, onAdReceived);
			}
			stage.addEventListener(MouseEvent.CLICK, clickStage);
		}

		protected function clickStage(event:MouseEvent):void
		{
			if (admob.isInterstitialReady())
			{
				admob.showVideo();
			}
			else
			{
				admob.cacheVideo("your_admob_video_id");
			}
		}

		protected function onAdReceived(event:AdmobEvent):void
		{
			trace(event.type, event.data);
		}
	}
}
