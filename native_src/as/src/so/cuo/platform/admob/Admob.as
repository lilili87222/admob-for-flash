package so.cuo.platform.admob
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.geom.Rectangle;
	import flash.system.Capabilities;

//	[Event(name="onNativeBannerReceive", type="so.cuo.platform.admob.AdmobEvent")]
//	[Event(name="onNativeBannerFailed", type="so.cuo.platform.admob.AdmobEvent")]
//	[Event(name="onNativeBannerPresent", type="so.cuo.platform.admob.AdmobEvent")]
//	[Event(name="onNativeBannerDismiss", type="so.cuo.platform.admob.AdmobEvent")]
//	[Event(name="onNativeBannerLeaveApplication", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onBannerDismiss", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onBannerFailedReceive", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onBannerLeaveApplication", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onBannerPresent", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onBannerReceive", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onInterstitialDismiss", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onInterstitialFailedReceive", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onInterstitialLeaveApplication", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onInterstitialPresent", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onInterstitialReceive", type="so.cuo.platform.admob.AdmobEvent")]
	
	
	[Event(name="onVideoRewarded", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onVideoLoadFail", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onVideoReceive", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onVideoOpen", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onVideoPlaying", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onVideoClosed", type="so.cuo.platform.admob.AdmobEvent")]
	[Event(name="onVideoLeaveApplication", type="so.cuo.platform.admob.AdmobEvent")]
	
	/**
	 * 
	 * @author Administrator
	 */
	public class Admob extends EventDispatcher
	{
		/**
		 * 
		 * @default 
		 */
		public static const version:String="admob ane";
		public var enableTrace:Boolean=true;
		public static const defaultNativeBannerName:String="defaultNativeBannerName";
		public static const defaultBannerName:String="defaultBannerName";
		private static var instance:Admob;
		private var extensionContext:ExtensionContext=null;
		private var screen:Object;

		public static function getInstance():Admob
		{
			if (instance == null)
			{
				instance=new Admob();
				trace(version);
			}
			return instance;
		}

		/**
		 * 
		 */
		public function Admob()
		{
			extensionContext=ExtensionContext.createExtensionContext("so.cuo.platform.admob", null);
			if (extensionContext != null)
			{
				extensionContext.addEventListener(StatusEvent.STATUS, onAdHandler);
			}
		}
		public function initAdmobSDK(extraPrama:ExtraParameter=null):void
		{
			if (!this.supportDevice)
				return;
			var param:String=extraPrama == null ? "" : extraPrama.toString();
			extensionContext.call(AdmobFunNames.initAdmobSDK,param);
			
		}
		/** does current device  support  ,support both ios and android now**/
		public function get supportDevice():Boolean
		{
			var ok:Boolean=Capabilities.manufacturer.indexOf('iOS') > -1 || Capabilities.manufacturer.indexOf('Android') > -1;
			return ok && this.extensionContext != null;
		}

		/**dispose this ane,you would never use this ane any more**/
		public function dispose():void
		{
			if (extensionContext != null)
			{
				extensionContext.removeEventListener(StatusEvent.STATUS, onAdHandler);
				extensionContext.dispose();
				extensionContext=null;
			}
			instance=null;
		}

		private function logTrace(msg:String):void
		{
			if (enableTrace)
				trace(msg);
		}

		/**device screen size**/
		public function getScreenSize():Rectangle
		{
			if (this.supportDevice)
			{
				if (this.screen == null)
				{
					var sizeString:String=extensionContext.call(AdmobFunNames.getScreenSize) as String;
					var v:Array=sizeString.split("_");
					screen=new Rectangle(0, 0, parseInt(v[0]), parseInt(v[1]));
				}
				return new Rectangle(0, 0, screen.width, screen.height);
			}
			else
			{
				return new Rectangle(0, 0);
			}
		}

		private function onAdHandler(e:StatusEvent):void
		{
			var admobEvent:AdmobEvent;
		/*	if (e.code.indexOf("Native") >= 0)
			{
				if (e.code == AdmobEvent.onNativeBannerReceive)
				{
					var datas:Array=e.level.split("_",3);
					admobEvent=new AdmobEvent(e.code, datas[1], datas[0]);
					admobEvent.data=new AdmobSize(datas[1], datas[2]);
				}else{
					var indexOfDet:int=e.level.indexOf("_");
					var bname:String=e.level.substring(0,indexOfDet);
					var d:String=e.level.substring(indexOfDet);
					admobEvent=new AdmobEvent(e.code, d,bname);
				}
			}
			else
			{*/
				if (e.code.indexOf("Banner")>= 0)
				{
					if (e.code == AdmobEvent.onBannerReceive)
					{
						var size:Array=e.level.split("_");
						var p:AdmobSize=new AdmobSize(parseInt(size[0]), parseInt(size[1]));
						admobEvent=new AdmobEvent(e.code, p, defaultBannerName);
					}else{
						admobEvent=new AdmobEvent(e.code, e.level, defaultBannerName);
					}
				}
				else if(e.code.indexOf("Video")>=0)
				{
					admobEvent=new AdmobEvent(e.code, e.level, "defaultVideo");
				}
				else
				{
					admobEvent=new AdmobEvent(e.code, e.level, "defaultInstitial");
				}
//			}
			if(admobEvent!=null){
				logTrace("admob event:"+JSON.stringify(admobEvent));
				this.dispatchEvent(admobEvent);
			}
		//	logTrace("admob ane log:" + e.type + "  code:" + e.code + "  level:" + e.level);
		}

		//--------------------------------------------
		/**load Interstitial ad,you would load full screen ad before show it**/
		public function cacheInterstitial(interstitialKey:String,extraPrama:ExtraParameter=null):void
		{
			if (!this.supportDevice)
				return;
			var param:String=extraPrama == null ? "{}" : extraPrama.toString();
			extensionContext.call(AdmobFunNames.cacheInterstitial, interstitialKey, param);

		}

		/**has load Interstitial success? just show after the Interstitial ad is ready**/
		public function isInterstitialReady():Boolean
		{
			if (!this.supportDevice)
				return false;
			return extensionContext.call(AdmobFunNames.isInterstitialReady);
		}

		/**show Interstitial on screen**/
		public function showInterstitial():void
		{
			if (!this.supportDevice)
				return;
			extensionContext.call(AdmobFunNames.showInterstitial);
		}

		
		/**load video ad,you would load full screen ad before show it**/
		public function cacheVideo(videoID:String,extraPrama:ExtraParameter=null):void
		{
			if (!this.supportDevice)
				return;
			var param:String=extraPrama == null ? "{}" : extraPrama.toString();
//			trace(param);
			extensionContext.call(AdmobFunNames.cacheVideo, videoID, param);
			
		}
		
		/**has load video success? just show after the Interstitial ad is ready**/
		public function isVideoReady():Boolean
		{
			if (!this.supportDevice)
				return false;
			return extensionContext.call(AdmobFunNames.isVideoReady);
		}
		
		/**show video on screen**/
		public function showVideo():void
		{
			if (!this.supportDevice)
				return;
			extensionContext.call(AdmobFunNames.showVideo);
		}
		//--------------------------------------------------------
		/**show admob banner relation position,position values in  AdmobPosition
		 * eg.  admob.showBanner(Admob.BANNER,AdmobPosition.BOTTOM_CENTER);
		 * **/
		public function showBanner(bannerID:String,adSize:AdmobSize, position:int, offY:int=0, extraParam:ExtraParameter=null, instanceName:String=defaultBannerName):void
		{
			if (!this.supportDevice)
				return;
			var param:String=extraParam == null ? "{}" : extraParam.toString();
			instanceName=instanceName == null ? defaultBannerName : instanceName;
			extensionContext.call(AdmobFunNames.showBanner, position, offY, adSize.width, adSize.height, bannerID, instanceName, param);
		}

		/**show admob banner absolute position,position x,y
		 * eg.  admob.showBannerAbsolute(Admob.BANNER,0,280);
		 * **/
		public function showBannerAbsolute(bannerID:String,adSize:AdmobSize, x:Number, y:Number, extraParam:ExtraParameter=null, instanceName:String=defaultBannerName):void
		{
			if (!this.supportDevice)
				return;
			var param:String=extraParam == null ? "{}" : extraParam.toString();
			instanceName=instanceName == null ? defaultBannerName : instanceName;
			extensionContext.call(AdmobFunNames.showBannerAbsolute, x, y, adSize.width, adSize.height, bannerID, instanceName, param);
		}

		/**hide ad banner**/
		public function hideBanner(instanceName:String=defaultBannerName):void
		{
			if (!this.supportDevice)
				return;
			extensionContext.call(AdmobFunNames.hideBanner, instanceName);
		}
		/*
		public function setAppMuted(mute:Boolean):void
		{
			if (!this.supportDevice)
				return;
			extensionContext.call(AdmobFunNames.setAppMuted,mute);
		}
		public function setAppVolume(v:Number):void
		{
			if (!this.supportDevice)
				return;
			extensionContext.call(AdmobFunNames.setAppVolume,v);
		}
		*/
		//--------------------------------------------------------------
		/*
		public function startFirebaseAnalytics():void
		{
			if (!Capabilities.manufacturer.indexOf('Android') > -1)
				return;
			extensionContext.call(AdmobFunNames.startAnalytics);
		}
		public function logFirebaseEvent( eventName:String, jsonString:String):void
		{
			if (!Capabilities.manufacturer.indexOf('Android') > -1)
				return;
			extensionContext.call(AdmobFunNames.logEvent,eventName,jsonString);
		}
		*/
	}
}
