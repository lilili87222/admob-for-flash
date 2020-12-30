package
{
	import flash.display.Sprite;
	
	import so.cuo.platform.admob.Admob;
	import so.cuo.platform.admob.AdmobPosition;
	import so.cuo.platform.admob.AdmobSize;
	import so.cuo.platform.admob.ExtraParameter;
	
	public class SimpleDemo extends Sprite
	{
		public function SimpleDemo()
		{
			super();
			var admob:Admob= Admob.getInstance();
			if(admob.supportDevice){
				admob.initAdmobSDK(new ExtraParameter());
				admob.showBanner("your_admob_banner_id",AdmobSize.BANNER_320x50,AdmobPosition.BOTTOM_CENTER);
			}
		}
	}
}