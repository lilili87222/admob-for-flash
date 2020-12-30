package so.cuo.platform.admob
{
	public class AdmobError
	{
		public static const  ERROR_CODE_ERNAL_ERROR :int= 0;
		public static const  ERROR_CODE_INVALID_REQUEST :int= 1;
		public static const  ERROR_CODE_NETWORK_ERROR :int= 2;
		public static const  ERROR_CODE_NO_FILL :int= 3;
		public static function errorString(code:int):String{
			switch(code)
			{
				case AdmobError.ERROR_CODE_ERNAL_ERROR:
				{
					return "ERROR_CODE_ERNAL_ERROR";
					break;
				}
				case AdmobError.ERROR_CODE_INVALID_REQUEST:
				{
					return "ERROR_CODE_INVALID_REQUEST";
					break;
				}
				case AdmobError.ERROR_CODE_NETWORK_ERROR:
				{
					return "ERROR_CODE_NETWORK_ERROR";
					break;
				}
				case AdmobError.ERROR_CODE_NO_FILL:
				{
					return "ERROR_CODE_NO_FILL";
					break;
				}
					
				default:
				{
					return code.toString();
					break;
				}
			}
		}
		public static function errorDesString(code:int):String{
			switch(code)
			{
				case AdmobError.ERROR_CODE_ERNAL_ERROR:
				{
					return "Something happened internally; for instance, an invalid response was received from the ad server.";
					break;
				}
				case AdmobError.ERROR_CODE_INVALID_REQUEST:
				{
					return "The ad request was invalid; for instance, the ad unit ID was incorrect";
					break;
				}
				case AdmobError.ERROR_CODE_NETWORK_ERROR:
				{
					return "The ad request was unsuccessful due to network connectivity";
					break;
				}
				case AdmobError.ERROR_CODE_NO_FILL:
				{
					return "The ad request was successful, but no ad was returned due to lack of ad inventory";
					break;
				}
					
				default:
				{
					return code.toString();
					break;
				}
			}
		}
	}
}