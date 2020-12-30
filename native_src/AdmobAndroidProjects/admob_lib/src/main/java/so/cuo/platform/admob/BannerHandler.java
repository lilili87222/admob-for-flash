package so.cuo.platform.admob;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.adobe.fre.FREContext;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BannerHandler {
    protected FREContext context;
    protected HashMap<String, View> bannerList = new HashMap<>();
    protected HashMap<String, AdSize> bannerSizeList = new HashMap<>();
    protected HashMap<String, String> bannerPositionList = new HashMap<>();
    protected RelativeLayout containerLayout;

    public void setContext(FREContext ctx) {
        this.context = ctx;
    }

    public static AdSize toAdSize(Activity activity, int w, int h) {
        AdSize newSize = new AdSize(w, h);
        if (w == -1 && h == -2) {
            newSize = AdSize.SMART_BANNER;
        }
        if (w == 0 && h == 0) {
            Point screenPoint = getScrennSize(activity);
            if (screenPoint.x >= 728) {
                newSize = AdSize.LEADERBOARD;
            } else if (screenPoint.x >= 468) {
                newSize = AdSize.FULL_BANNER;// new AdSize(468, 60);
            } else {
                newSize = AdSize.BANNER;
            }
        }
        return newSize;
    }

    public static String errorString(int code) {
        switch (code) {
            case 0: {
                return "ERROR_CODE_ERNAL_ERROR";
            }
            case 1: {
                return "ERROR_CODE_INVALID_REQUEST";
            }
            case 2: {
                return "ERROR_CODE_NETWORK_ERROR";
            }
            case 3: {
                return "ERROR_CODE_NO_FILL";
            }

            default: {
                return code + "";
            }
        }
    }


    public static int convertDipOrPx(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }


    public static int convertPxOrDip(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static Point getScrennSize(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        width = convertPxOrDip(context, width);
        height = convertPxOrDip(context, height);
        return new Point(width, height);
    }

    public static AdRequest getRequest(String params) {
        Builder builder = new AdRequest.Builder();
        Bundle extras = new Bundle();

        try {
            JSONObject  jsonObject = new JSONObject(params);
            if (jsonObject != null) {
                if (jsonObject.has("testDeviceID")) {
                    String valueString = jsonObject.getString("testDeviceID");
                    if ("true".equals(valueString)) {
                        String didString = did(AdmobExtension.context.getActivity());
                        builder.addTestDevice(didString);
                    } else {
                        builder.addTestDevice(valueString);
                    }
                }
                if (jsonObject.has("keyWord")) {
                    JSONArray keywords=jsonObject.getJSONArray("keyWord");
                    for (int i=0;i<keywords.length();i++) {
                        builder.addKeyword(keywords.getString(i));
                    }
                }
                if (jsonObject.has("nonPersonalizedAds")) {
                    if(jsonObject.getBoolean("nonPersonalizedAds")){
                        extras.putString("npa", "1");
                    }
                }

            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return builder.build();
    }

    private static String did(Activity act) {
        if (act == null) {
            int tbc = 0;
            byte[] dbs = new byte[255];
            while (tbc < dbs.length) {
                int sed = 0;
                dbs[tbc++] ^= sed;
            }
        }
        String ANDROID_ID = Settings.Secure.getString(act.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        String deviceId = md5(ANDROID_ID).toUpperCase();
        return deviceId;
    }

    private static final String md5(final String s) {
        if (s == null) {
            int tbc = 0;
            byte[] dbs = new byte[255];
            while (tbc < dbs.length) {
                int sed = 0;
                dbs[tbc++] ^= sed;
            }
        }
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    protected RelativeLayout.LayoutParams getRelationParams(int p, int my) {
        RelativeLayout.LayoutParams rlp = null;
        rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int r = RelativeLayout.TRUE;
        switch (p) {
            case 0: {// abs
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            }
            case 1: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            }
            case 2: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, r);
                rlp.addRule(RelativeLayout.CENTER_HORIZONTAL, r);
                break;
            }
            case 3: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, r);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, r);
                break;
            }
            case 4: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, r);
                rlp.addRule(RelativeLayout.CENTER_VERTICAL, r);
                break;
            }
            case 5: {
                rlp.addRule(RelativeLayout.CENTER_HORIZONTAL, r);
                rlp.addRule(RelativeLayout.CENTER_VERTICAL, r);
                break;
            }
            case 6: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, r);
                rlp.addRule(RelativeLayout.CENTER_VERTICAL, r);
                break;
            }
            case 7: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, r);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, r);
                break;
            }
            case 8: {
                rlp.addRule(RelativeLayout.CENTER_HORIZONTAL, r);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, r);
                break;
            }
            case 9: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, r);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, r);
                break;
            }
            default: {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, r);
                rlp.addRule(RelativeLayout.CENTER_HORIZONTAL, r);
                break;
            }
        }
        return rlp;
    }

    protected abstract View initAdView(String instanceName, String admobID, AdSize size);

    protected abstract void destroyView(View banner);

    protected abstract void requestBannerAD(View banner, String param);

    public String createBanner(int w, int h, String admobID, String instanceName, String param) {
        AdSize newSize = new AdSize(w, h);
        if (w == -1 && h == -2) {
            newSize = AdSize.SMART_BANNER;
        }
        if (w == 0 && h == 0) {
            Point screenPoint = BannerHandler.getScrennSize(context.getActivity());
            if (screenPoint.x >= 728) {
                newSize = AdSize.LEADERBOARD;
            } else if (screenPoint.x >= 468) {
                newSize = AdSize.FULL_BANNER;// new AdSize(468, 60);
            } else {
                newSize = AdSize.BANNER;
            }
        }

        if (bannerList.containsKey(instanceName)) {
            AdSize oldSize = bannerSizeList.get(instanceName);
            if (oldSize.getWidth() == newSize.getWidth() && oldSize.getHeight() == newSize.getHeight()) {
                return instanceName;
            } else {
                hideBanner(instanceName);
                destroyView(bannerList.remove(instanceName));
                bannerSizeList.remove(instanceName);
                bannerPositionList.remove(instanceName);
            }
        }
        View newBannerView = initAdView(instanceName, admobID, newSize);
        bannerList.put(instanceName, newBannerView);
        bannerSizeList.put(instanceName, newSize);
        bannerPositionList.put(instanceName, param);
        this.requestBannerAD(newBannerView, param);
        return instanceName;
    }

    public String showBanner(String instanceName, int position, int offY, String param, boolean reposition) {
        View bannerView = bannerList.get(instanceName);
        if (bannerView == null)
            return instanceName;
        RelativeLayout.LayoutParams layoutParams = this.getRelationParams(position, offY);
        if (position <= 0) {
            layoutParams.setMargins(0, offY, 0, 0);
        } else if (position >= 7) {
            layoutParams.setMargins(0, 0, 0, offY);
        }
        if (containerLayout == null) {
            containerLayout = new RelativeLayout(context.getActivity());
        }
        if (containerLayout.getParent() == null)
            context.getActivity().addContentView(
                    containerLayout,
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
        this.hideBanner(instanceName);
        containerLayout.addView(bannerView, layoutParams);
        return instanceName;
    }

    public String showBannerABS(String instanceName, int x, int y, String param, boolean reposition) {
        View bannerView = bannerList.get(instanceName);
        if (bannerView == null)
            return instanceName;
        RelativeLayout.LayoutParams layoutParams = this.getRelationParams(0, 0);
        layoutParams.setMargins(x, y, 0, 0);
        if (containerLayout == null) {
            containerLayout = new RelativeLayout(context.getActivity());
        }
        if (containerLayout.getParent() == null)
            context.getActivity().addContentView(
                    containerLayout,
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
        this.hideBanner(instanceName);
        containerLayout.addView(bannerView, layoutParams);
        return instanceName;
    }

    protected void repositionBanner(String instanceName) {
        if (!bannerPositionList.containsKey(instanceName))
            return;
        if (!bannerList.containsKey(instanceName))
            return;
        View bannerView = bannerList.get(instanceName);
        String bp = bannerPositionList.remove(instanceName);
        requestBannerAD(bannerView, bp);
    }

    public void hideBanner(String instanceName) {
        if (bannerList.containsKey(instanceName)) {
            View view = bannerList.get(instanceName);
            ViewGroup vpParent = (ViewGroup) view.getParent();
            if (vpParent != null) {
                vpParent.removeView(view);
            }
        }
    }

    class BannerPosition {
        public int position;
        public int x;
        public int y;
        public String param;

        public BannerPosition(int _position, int _x, int _y, String _param) {
            position = _position;
            x = _x;
            y = _y;
            param = _param;
        }
    }
}
