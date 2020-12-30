package so.cuo.platform.admob
{
	
/**
 *  extra parameter to control admob request,such as if you are testing ,is this app made for child .
 * just set property that you want to change the default value. 
 * more info about the parameter can found https://developers.google.com/mobile-ads-sdk/docs/admob/additional-controls#play-coppa
 */
	public class ExtraParameter
	{
		public static const MAX_AD_CONTENT_RATING_G:String="G";
		public static const MAX_AD_CONTENT_RATING_T:String="T";
		public static const MAX_AD_CONTENT_RATING_PG:String="PG";
		public static const MAX_AD_CONTENT_RATING_MA:String="MA";
//		public static const IS_UNDER_AGE_OF_CONSENT_YES:String="YES";
//		public static const IS_UNDER_AGE_OF_CONSENT_NO:String="NO";
		
		private var _testDeviceID:String;
		private var _keyWord:Array=new Array();//"a b c"
		private var _contentUrl:String;
		private var _latitude:Number;
		private var _longitude:Number;
		private var _isChildApp:Boolean;//
		private var _nonPersonalizedAds:Boolean;//none Personalized ads npa
		
		private var _maxAdContentRating:String;//value can been one of G PG T MA
		private var _isUnderAgeOfConsent:Boolean;
		
		private var _isAppMuted:Boolean;
		private var _volume:int=100;//100;



		public function get isAppMuted():Boolean
		{
			return _isAppMuted;
		}

		public function set isAppMuted(value:Boolean):void
		{
			_isAppMuted = value;
		}

		public function get volume():Number
		{
			return _volume;
		}

		public function set volume(value:Number):void
		{
			_volume = value;
		}

		public function get isUnderAgeOfConsent():Boolean
		{
			return _isUnderAgeOfConsent;
		}

		public function set isUnderAgeOfConsent(value:Boolean):void
		{
			_isUnderAgeOfConsent = value;
		}

		public function get maxAdContentRating():String
		{
			return _maxAdContentRating;
		}

		public function set maxAdContentRating(value:String):void
		{
			_maxAdContentRating = value;
		}

		public function get nonPersonalizedAds():Boolean
		{
			return _nonPersonalizedAds;
		}

		public function set nonPersonalizedAds(value:Boolean):void
		{
			_nonPersonalizedAds = value;
		}

		public function get isChildApp():Boolean
		{
			return _isChildApp;
		}
/**just set when this app is  for child **/
		public function set isChildApp(value:Boolean):void
		{
			_isChildApp = value;
		}
		public function set tagForChildDirectedTreatment(v:Boolean):void{
			_isChildApp=v;
		}

		public function setLocation(latitude:Number,longitude:Number):void
		{
			this._latitude=latitude;
			this._longitude=longitude;
		}


		public function get contentUrl():String
		{
			return _contentUrl;
		}

		public function set contentUrl(value:String):void
		{
			if(value==null||value.indexOf("@")>=0||value.indexOf(",")>=0){
				trace("url contains char '@' , ','  will fail");
				return ;
			}
			_contentUrl = value;
		}


		public function get keyWord():Array
		{
			return _keyWord;
		}
/**keyword format   "key1 key2 key3 key4" **/
		public function set keyWord(value:Array):void
		{
//			if(value==null||value.indexOf("@")>=0||value.indexOf(",")>=0){
//				trace("keyword contains char '@' , ','  will fail");
//				return ;
//			}
//			trace("keyword format  'key1 key2 key3' ");
			_keyWord = value;
		}

		public function get testDeviceID():String
		{
			return _testDeviceID;
		}
/** you can set the device id value to get admob test ad,device id sample C10FA0762720A0FD0E64FE3825A8B64F**/
		public function set testDeviceID(value:String):void
		{
			if(value==null||value.indexOf("@")>=0||value.indexOf(",")>=0){
				trace("device contains char '@' , ','  will fail");
				return ;
			}
			_testDeviceID = value;
		}

		public function toString():String{////"key"@"value","key2"@"value2","keyWord"@"a b c"
			return JSON.stringify(this);
			/*
			var ret:String="";
			if(testDeviceID!=null&&testDeviceID!=""){
				ret+=",testDeviceID@"+testDeviceID;
			}
			if(keyWord!=null&&keyWord!=""){
				ret+=",keyword@"+keyWord;
			}

			if(contentUrl!=null&&contentUrl!=""){
				ret+=",contentURL@"+contentUrl;
			}

			if(_latitude!=0||_longitude!=0){
				ret+=",location@"+_latitude+"_"+_longitude;
			}
			if(isChildApp){
				ret+=",isChildApp@1";
			}
			if(_nonPersonalizedAds){
				ret+=",nonPersonalized@1";
			}
			if(this._isUnderAgeOfConsent!=null){
				ret+=",tag_for_under_age_of_consent@"+this._isUnderAgeOfConsent;
			}
			if(this._maxAdContentRating!=null){
				ret+=",max_ad_content_rating@"+this._maxAdContentRating;
			}

			if(ret.charAt(0)==","){
				ret=ret.substr(1);
			}
			return ret;
			*/
		}
	}
}