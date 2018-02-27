package qiwu.qq_simplity;

import android.app.Notification;

import java.io.File;
import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Deng on 2018/1/19.
 */

public class FunctionHook {
    public static void cleanGift(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.troopgift.TroopGiftAnimationController");
            XposedHelpers.findAndHookMethod(hookclass,"a",
                    "com.tencent.mobileqq.data.MessageForDeliverGiftTips", new XC_MethodReplacement() {
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
    public static void cleanPendant(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.vas.PendantInfo");
            XposedHelpers.findAndHookMethod(hookclass, "a", int.class, Object.class, int.class,
                    new XC_MethodHook() {
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
    public static void cleankeyWordList(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.model.EmoticonManager");
            XposedHelpers.findAndHookMethod(hookclass, "i", new XC_MethodReplacement() {
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
    public static void cleanSearch(ClassLoader classLoader) {
        try {
            Class<?> hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.Leba");
            XposedHelpers.findAndHookMethod(hookclass, "a","java.util.List", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    return null;
                }
            });
        }catch (Exception e){
            XposedBridge.log("error");
            XposedBridge.log(e);
        }
    }
    public static void cleanRecommemd(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.app.EmoticonHandler");
            XposedHelpers.findAndHookMethod(hookclass, "c",int.class, new XC_MethodReplacement() {
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
    public static void cleanBubble(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.bubble.BubbleManager");
            XposedHelpers.findAndHookMethod(hookclass, "a", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    File file=new File("data/data/com.tencent.mobileqq/files/imei");
                    return file;
                }
            });
        }catch (Exception e){
            XposedBridge.log("载入错误");
            XposedBridge.log(e);
        }

    }
    public static void cleanFontImage(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.TextPreviewActivity");
            XposedHelpers.findAndHookMethod(hookclass, "a", int.class, new XC_MethodReplacement() {
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
    public static void cleanFont(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.aio.item.TextItemBuilder");
            XposedHelpers.findAndHookMethod(hookclass, "a", "com.tencent.mobileqq.activity.aio.BaseBubbleBuilder$ViewHolder",
                    "com.tencent.mobileqq.data.ChatMessage", new XC_MethodReplacement() {
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
    public static void cleanBuluo(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.Leba");
            XposedHelpers.findAndHookMethod(hookclass, "B",  new XC_MethodReplacement() {
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
    public static void cleanEnterEffect(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.troop.enterEffect.TroopEnterEffectController");
            Field[]fields=hookclass.getDeclaredFields();
            for (Field field:fields){
                if (field.toString().contains("final")){
                    field.setAccessible(true);
                    field.set(null,"");
                }
            }
        }catch (Exception e){
            XposedBridge.log("载入错误");
            XposedBridge.log(e);
        }

    }
    public static void cleanEmotionDrop(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.activity.aio.anim.AioAnimationConfigHelper");
            XposedHelpers.findAndHookMethod(hookclass, "a", new XC_MethodHook() {
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
    public static void cleanUpgrade(ClassLoader classLoader){
        Class<?>hookclass=null;
        try{
            hookclass=classLoader.loadClass("com.tencent.mobileqq.app.automator.step.AfterSyncMsg");
            XposedHelpers.findAndHookMethod(hookclass, "e", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object o=param.thisObject;
                    XposedHelpers.findAndHookMethod("com.tencent.mobileqq.app.MessageHandler", o.getClass().getClassLoader(), "c", "com.tencent.qphone.base.remote.ToServiceMsg", "com.tencent.qphone.base.remote.FromServiceMsg", Object.class, new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                            return null;
                        }
                    });
                }
            });
        }catch (Exception e){
            XposedBridge.log("载入错误");
            XposedBridge.log(e);
        }
    }
}

