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
            RemoveImagine.removeDrawable(Integer.parseInt(RedTouchId.get(i).toString()));
        }
        XposedHelpers.findAndHookMethod(ImageView.class, "setImageResource", int.class,new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                int i=(int)param.args[0];
                if (i==HookById.getDrawableId(c, "skin_tips_dot_small")){
                    param.args[0]=null;
                }
            }
        });
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
    public static void cleanXiao(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.aio.item.PicItemBuilder");
            XposedHelpers.findAndHookMethod(hookclass, "a", "com.tencent.mobileqq.activity.aio.BaseChatItemLayout", "com.tencent.mobileqq.data.MessageForPic", "com.tencent.mobileqq.activity.aio.OnLongClickAndTouchListener", "com.tencent.mobileqq.activity.aio.item.PicItemBuilder$Holder", boolean.class, new XC_MethodReplacement() {
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
    public static void cleanQunRemove(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.ChatActivityUtils");
            XposedHelpers.findAndHookMethod(hookclass, "a","android.content.Context",String.class,"android.view.View$OnClickListener","android.view.View$OnClickListener",new  XC_MethodHook() {
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
    public static void cleanPicXiao(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.aio.item.PicItemBuilder");
            XposedHelpers.findAndHookMethod(hookclass, "a", "com.tencent.mobileqq.app.QQAppInterface", Context.class, View.class, "com.tencent.mobileqq.data.ChatMessage", "com.tencent.mobileqq.activity.aio.SessionInfo", boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[5]=false;
                }
            });
        }catch (Exception e){
            XposedBridge.log(e);
        }
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