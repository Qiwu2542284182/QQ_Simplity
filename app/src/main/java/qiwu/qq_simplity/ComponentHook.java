package qiwu.qq_simplity;

import android.content.Context;
import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Sampler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;


/**
 * Created by Deng on 2018/1/19.
 */

public class ComponentHook {
    public static void cleanRedTouth(final Class c){
        ArrayList RedTouchId= new ArrayList();
        RedTouchId.add(HookById.getDrawableId(c, "skin_tips_dot"));
        RedTouchId.add(HookById.getDrawableId(c, "skin_tips_dot_small"));
        RedTouchId.add(HookById.getDrawableId(c, "skin_tips_new"));
        if (MainHook.getQQ_Version().compareTo("7.3.5")>0){
            RedTouchId.add(HookById.getDrawableId(c,"shortvideo_redbag_outicon"));
        }
        for (int i=0;i<RedTouchId.size();i++){
            RemoveImagine.removeDrawable(Integer.parseInt(RedTouchId.get(i).toString()),HookById.getDrawableId(c,"skin_searchbar_button_pressed_theme_version2"));
        }
    }

    public static void cleanLeba(Class c){
        if (SettingUtils.iscleanQzone()){
            RemoveImagine.remove(HookById.getId(c,"qzone_feed_entry_sub_iv"));
        }
        if (SettingUtils.iscleanNearBy()){
            RemoveImagine.remove(HookById.getId(c,"nearby_people_entry_sub_iv"));
        }
        if (SettingUtils.iscleanBuluo()){
            RemoveImagine.remove(HookById.getId(c,"xingqu_buluo_entry_sub_iv"));
        }
    }
    public static void cleanCe(Class c) {
        int myvip=HookById.getLayoutId(c,"myvip");
        int cuKingCard=HookById.getId(c,"cuKingCard");
        if (SettingUtils.iscleanCeVIP()){
            RemoveImagine.remove(myvip);
        }
        if (SettingUtils.iscleanCeMian()){
            RemoveImagine.remove(cuKingCard);
        }
        if (SettingUtils.iscleanCeCard()){
            RemoveImagine.remove(HookById.getId(c,"mycards"));
        }
        if (SettingUtils.iscleanVideo()){
            RemoveImagine.remove(HookById.getId(c,"myvideos"));
        }

    }
    public static void cleanMedal(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.aio.item.MedalNewsItemBuilder");
            XposedHelpers.findAndHookMethod(hookclass, "a", "com.tencent.mobileqq.data.MessageRecord", "com.tencent.mobileqq.activity.aio.AbstractChatItemBuilder$ViewHolder", "android.view.View", "android.widget.LinearLayout", "com.tencent.mobileqq.activity.aio.OnLongClickAndTouchListener", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(null);
                }
            });
        }catch (Exception e){
            XposedBridge.log("载入错误");
            XposedBridge.log(e);
        }

    }
    public static void cleanSettingMain(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.QQSettingSettingActivity");
            XposedHelpers.findAndHookMethod(hookclass, "a", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    return null;
                }
            });
        }catch (Exception e){
            XposedBridge.log("载入错误");
            XposedBridge.log(e);
        }

    }

    public static void cleanBanner(Class c){
        int close=HookById.getId(c,"close");
        int AdView=HookById.getId(c,"adview1");
        RemoveImagine.remove(close);
        RemoveImagine.remove(AdView);
    }
    public static void cleanQunTips(Class c){
        RemoveImagine.remove(HookById.getId(c,"troop_assistant_feeds_title_small_video"));
        RemoveImagine.remove(HookById.getId(c,"troop_assistant_feeds_title_super_owner"));
    }

    public static void cleanTimFile(Class c){
        int Id=HookById.getId(c,"timtips");
        RemoveImagine.remove(Id);
    }
    public static void cleanQunRemove(Class c){
        RemoveImagine.remove(HookById.getId(c,"chat_top_bar"));
        RemoveImagine.remove(HookById.getId(c,"chat_top_bar_confirm_btn"));
        RemoveImagine.remove(HookById.getId(c,"chat_top_bar_text"));
        RemoveImagine.remove(HookById.getId(c,"chat_top_bar_btn"));
    }
    public static void cleanPicXiao(Class c){
        RemoveImagine.remove(HookById.getId(c,"pic_light_emoj"));
    }
    public static void cleanGreenAd(Class c){
        int[]AdId={HookById.getId(c,"shuoshuo_ad_upload_quality"),HookById.getId(c,"quality_hd_ad"),HookById.getId(c,"quality_ad")};
        for (int i=0;i<AdId.length;i++){
            RemoveImagine.remove(AdId[i]);
        }
    }
    public static void cleanPanellconLinearLayout(Class c){
        if (SettingUtils.iscleanGif()){
            int GifId=HookById.getId(c,"qq_aio_panel_hot_pic");
            RemoveImagine.remove(GifId);
        }if (SettingUtils.iscleanRedbag()){
            int RedbagId=HookById.getId(c,"qq_aio_panel_hongbao");
            RemoveImagine.remove(RedbagId);
        }
        if (SettingUtils.iscleanPoke()){
            int PokeId=HookById.getId(c,"qq_aio_panel_poke");
            RemoveImagine.remove(PokeId);
        }
    }
}