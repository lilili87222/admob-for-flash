package so.cuo.platform.admob
{

	public class AdmobSize
	{
		
		public static const SMART_BANNER:AdmobSize=new AdmobSize(-1, -2);
		/**iphone 320x50 or 468x60,ipad 728x90 **/
		public static const BANNER_STANDARD:AdmobSize=new AdmobSize(0, 0);
		/**banner size 320,50**/
		public static const BANNER_320x50:AdmobSize=new AdmobSize(320, 50);
		/**banner size 320,100**/
		public static const BANNER_320x100:AdmobSize=new AdmobSize(320, 100);
		/**banner size 300,250**/
		public static const BANNER_300X250:AdmobSize=new AdmobSize(300, 250);
		/**banner size 468,60**/
		public static const BANNER_468X60:AdmobSize=new AdmobSize(468, 60);
		/**banner size 728,90**/
		public static const BANNER_728X90:AdmobSize=new AdmobSize(728, 90);

		protected var _width:int;
		protected var _height:int;

		/**
you  can create a custom size ,for admob more ad size
https://developers.google.com/mobile-ads-sdk/docs/admob/smart-banners
https://developers.google.com/mobile-ads-sdk/docs/admob/intermediate
**/
		public function AdmobSize(w:Number=320, h:Number=50)
		{
			this._width=w;
			this._height=h;
		}

		public function set height(value:int):void
		{
			_height=value;
		}

		public function set width(value:int):void
		{
			_width=value;
		}

		public function get width():int
		{
			return this._width;
		}

		public function get height():int
		{
			return this._height;
		}

		public function equals(adsize:AdmobSize):Boolean
		{
			if (adsize == null)
				return false;
			return adsize.width == this.width && adsize.height == this.height;
		}
	}
}
