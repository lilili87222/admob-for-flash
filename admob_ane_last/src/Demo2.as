package
{
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.system.Capabilities;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	
	import so.cuo.platform.admob.Admob;
	import so.cuo.platform.admob.AdmobEvent;
	import so.cuo.platform.admob.AdmobPosition;
	import so.cuo.platform.admob.AdmobSize;
	import so.cuo.platform.admob.ExtraParameter;
	
	
	public class Demo2 extends Sprite
	{
		private var admob:Admob;
		public var appID:String;
		public var bannerID:String;
		public var fullID:String;
		public var nativeID:String;
		public var videoID:String;
		public var extraParam:ExtraParameter;
		
		public var xPosition:TextField
		public var yPosition:TextField
		public var bannerType:TextField
		
		public function Demo2()
		{
			super();
			
			var isIOS:Boolean=Capabilities.manufacturer.indexOf('iOS') > -1;
			if(isIOS){
				appID="ca-app-pub-3940256099942544~1458002511";
				bannerID="ca-app-pub-3940256099942544/2934735716";
				fullID="ca-app-pub-3940256099942544/4411468910";
				videoID="ca-app-pub-3940256099942544/1712485313";
				nativeID = "ca-app-pub-3940256099942544/3986624511";
			}else{
				appID="ca-app-pub-3940256099942544~3347511713";
				bannerID="ca-app-pub-3940256099942544/6300978111";
				fullID="ca-app-pub-3940256099942544/1033173712";
				videoID="ca-app-pub-3940256099942544/5224354917";
				nativeID = "ca-app-pub-3940256099942544/2247696110";
			}
			initUI();
			admob=Admob.getInstance();
			
			extraParam=new ExtraParameter();
			extraParam.testDeviceID="true";
			/*
			//config you may set as follow
			extraParam.isAppMuted=true;
			extraParam.maxAdContentRating=ExtraParameter.MAX_AD_CONTENT_RATING_T;
			extraParam.keyWord=["a","b"];
			extraParam.nonPersonalizedAds=true;
			extraParam.isChildApp=true;
			*/
			if (admob.supportDevice)
			{
				admob.initAdmobSDK(extraParam);//config admob appid in xxx-app.xml
				admob.addEventListener(AdmobEvent.onInterstitialReceive, onAdEvent);
				admob.addEventListener(AdmobEvent.onBannerReceive, onAdEvent);
				admob.addEventListener(AdmobEvent.onVideoLoadFail, onAdEvent);
				admob.addEventListener(AdmobEvent.onVideoRewarded, onAdEvent);
				admob.enableTrace=true;
				//				
				trace(admob.getScreenSize(), admob.getScreenSize().height);
				log.text+="supported\n";
			}else{
				trace("--------- not support");
				log.text+="not supported\n";
			}
			
		    showStartAuto();
		}
		
		private function showStartAuto():void
		{
			admob.cacheInterstitial(fullID,extraParam);
			admob.showBanner(bannerID,AdmobSize.BANNER_320x50,AdmobPosition.BOTTOM_CENTER);
			admob.showBannerAbsolute(bannerID,AdmobSize.BANNER_320x50,0,250,null,"classicBanner");
			
		}
		private var log:TextField=new TextField();
		
		private function initUI():void
		{
			stage.align=StageAlign.TOP_LEFT;
			stage.scaleMode=StageScaleMode.NO_SCALE;
			var ui:UI=new UI(onClick);
			addChild(ui);
			ui.addButton("relation", 20, 40);
			ui.addButton("absolute", 160, 40);
			ui.addButton("hide", 300, 40);
			
			xPosition=ui.addButton("8", 20, 120);
			yPosition=ui.addButton("300", 160, 120);
			bannerType=ui.addButton("bannerType", 300, 120);
			
			
			ui.addButton("interstitial", 20, 320);
			ui.addButton("showVideo", 160, 320);
			ui.addButton("logEvent", 0, 420);
			
			xPosition.type=yPosition.type=bannerType.type=TextFieldType.INPUT;
			xPosition.border=yPosition.border=bannerType.border=true;
			
			log.width=400;
			log.height=200;
			log.y=480;
			log.border=true;
			this.addChild(log);
		}
		
		private function onAdEvent(event:AdmobEvent):void
		{
			log.text+=event.type+event.data+"\n";
			if(event.data=="3"){
				log.text+="code correct,but ad not found to fill";
			}
			if (event.type == AdmobEvent.onBannerReceive)
			{
				trace(event.instanceName,event.data.width, event.data.height);
			}
			if (event.type == AdmobEvent.onInterstitialReceive)
			{
				admob.showInterstitial();
			}
		}
		
		private function onClick(label:String):void
		{
			
			trace("click button "+label);
			log.appendText(label);
			if (!admob.supportDevice)
			{
				trace("not support device");
				return
			}
			else
			{
				var xPositionValue:int=parseInt(xPosition.text);
				var yPositionValue:int=parseInt(yPosition.text);
				var bannerTypeValue:int=parseInt(bannerType.text);
				var adsize:AdmobSize;
				if (bannerTypeValue == 1)
				{
					adsize=AdmobSize.BANNER_320x50;
				}
				if (bannerTypeValue == 2)
				{
					adsize=AdmobSize.BANNER_320x100;
				}
				if (bannerTypeValue == 3)
				{
					adsize=AdmobSize.BANNER_300X250;
				}
				if (bannerTypeValue == 4)
				{
					adsize=AdmobSize.BANNER_468X60;
				}
				if (bannerTypeValue == 5)
				{
					adsize=AdmobSize.BANNER_728X90;
				}
				if (bannerTypeValue == 6)
				{
					adsize=AdmobSize.SMART_BANNER;
				}
				if (adsize == null)
				{
					adsize=AdmobSize.BANNER_STANDARD;
				}
				
				if (label == "hide")
				{
					admob.hideBanner();
					admob.hideBanner("classicBanner");
				}
				if (label == "absolute")
				{
					admob.showBannerAbsolute(bannerID,adsize, xPositionValue, yPositionValue, extraParam);
				}
				else if (label == "relation")
				{
					if (xPositionValue > 9)
						xPositionValue=9;
					if (xPositionValue < 1)
						xPositionValue=1;
					admob.showBanner(bannerID,adsize, xPositionValue,0, extraParam);
				}
				else if (label == "interstitial")
				{
					if (admob.isInterstitialReady())
					{
						admob.showInterstitial();
					}
					else
					{
						admob.cacheInterstitial(fullID,extraParam);
					}
				}
				else if(label=="showVideo"){
					if(admob.isVideoReady()){
						admob.showVideo();
					}else{
						admob.cacheVideo(videoID,extraParam);
					}
				}
				else if(label=="logEvent"){
					//					admob.startFirebaseAnalytics();
					//admob.logFirebaseEvent("loginUser",JSON.stringify({"name":"login","time":"1222222222222222"}));
				}
			}
		}
	}
}
