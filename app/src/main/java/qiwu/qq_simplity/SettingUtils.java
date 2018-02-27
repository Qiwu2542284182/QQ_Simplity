package qiwu.qq_simplity;


import java.lang.ref.WeakReference;
import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by Deng on 2018/1/12.
 */

class SettingUtils{
    private static WeakReference<XSharedPreferences> xSharedPreferences = new WeakReference<>(null);
    private static XSharedPreferences getPref() {
        XSharedPreferences preferences = xSharedPreferences.get();
        if (preferences == null) {
            preferences = new XSharedPreferences(BuildConfig.APPLICATION_ID);
            preferences.makeWorldReadable();
            xSharedPreferences = new WeakReference<>(preferences);
        } else {
            preferences.reload();
        }
        return preferences;
    }
    public static boolean iscleanPendant(){return getPref().getBoolean("AA",false);}
    public static boolean iscleanBubble(){return getPref().getBoolean("AB",false);}
    public static boolean iscleanFont(){return getPref().getBoolean("AC",false);}
    public static boolean iscleanGift(){return getPref().getBoolean("AD",false);}
    public static boolean iscleankeyWordList(){return getPref().getBoolean("AE",false);}
    public static boolean iscleanRecommemd(){return getPref().getBoolean("AF",false);}
    public static boolean iscleanSearch(){return getPref().getBoolean("AG",false);}
    public static boolean iscleanEmotionDrop(){return getPref().getBoolean("AH",false);}
    public static boolean iscleanUpgrade(){return getPref().getBoolean("AI",false);}
    public static boolean iscleanEnterEffect(){return getPref().getBoolean("AJ",false);}
    public static boolean iscleanSplashAd(){return getPref().getBoolean("AK",false);}
    public static boolean iscleanRedTouth(){return getPref().getBoolean("BA",false);}
    public static boolean iscleanXiao(){return getPref().getBoolean("BC",false);}
    public static boolean iscleanMedal(){return getPref().getBoolean("BD",false);}
    public static boolean iscleanCeVIP(){return getPref().getBoolean("BE",false);}
    public static boolean iscleanCeMian(){return getPref().getBoolean("BF",false);}
    public static boolean iscleanSettingMain(){return getPref().getBoolean("BG",false);}
    public static boolean iscleanBannner(){return getPref().getBoolean("BI",false);}
    public static boolean iscleanQunTips(){return getPref().getBoolean("BJ",false);}
    public static boolean iscleanQunRemove(){return getPref().getBoolean("BK",false);}
    public static boolean iscleanTimFile(){return getPref().getBoolean("BL",false);}
    public static boolean iscleanBuluo(){return getPref().getBoolean("BM",false);}
    public static boolean iscleanPicXiao(){return getPref().getBoolean("BN",false);}
    public static boolean iscleanGif(){return getPref().getBoolean("BO",false);}
    public static boolean iscleanRedbag(){return getPref().getBoolean("BP",false);}
    public static boolean iscleanPoke(){return getPref().getBoolean("BQ",false);}
    public static boolean iscleanGreenAd(){return getPref().getBoolean("BR",false);}
    public static boolean isBanFlashPic(){return getPref().getBoolean("ZB",false);}
    public static boolean isBanRevoke(){return getPref().getBoolean("ZC",false);}
    public static String qqcolor(){return getPref().getString("ZD","000000");}
    public static boolean isOn(){return getPref().getBoolean("CB",false);}

}
