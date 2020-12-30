package so.cuo.platform.admob;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AdmobExtension implements FREExtension {
	public static   AdmobContext context;
	@Override
	public FREContext createContext(String extId) {
		context = new AdmobContext();
		context.initialize();
		return context;
	}

	@Override
	public void dispose() {
		context.dispose();
		context = null;
	}

	@Override
	public void initialize() {
	}

}
