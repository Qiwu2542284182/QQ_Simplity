package qiwu.qq_simplity;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Deng on 2018/1/6.
 */

public class MainHook implements IXposedHookLoadPackage{
    private ClassLoader classLoader;
    private Class drawableId;
    private Class Id;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        CheckActive.isActive(loadPackageParam);
        if (!loadPackageParam.packageName.equals("com.tencent.mobileqq"))
            return;
        if (!SettingUtils.isOn())return;
        DeleteFile.delete("/storage/emulated/0/Tencent/QQfile_recv/","app-debug");
        XposedBridge.log("QQ版本"+getQQ_Version());
        XposedHelpers.findAndHookMethod("com.tencent.mobileqq.app.InjectUtils", loadPackageParam.classLoader, "injectExtraDexes",
                Application.class, boolean.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        final Application application = (Application) param.args[0];
                        if (Build.VERSION.SDK_INT<21){
                            XposedHelpers.findAndHookMethod("com.tencent.common.app.BaseApplicationImpl", application.getClassLoader(), "onCreate", new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    startHook(application.getClassLoader());
                                }
                            });
                        }else {
                            startHook(application.getClassLoader());
                        }
                    }
        });
    }

    private void startHook(final ClassLoader classLoader){
        drawableId = XposedHelpers.findClass("com.tencent.mobileqq.R$drawable", classLoader);
        Id = XposedHelpers.findClass("com.tencent.mobileqq.R$id", classLoader);
        ComponentHook.cleanLeba(Id);
        if (SettingUtils.iscleanRedTouth()) {
            ComponentHook.cleanRedTouth(drawableId);
        }
        if (SettingUtils.iscleanQunTips()) {
            ComponentHook.cleanQunTips(Id);
        }
        if (SettingUtils.iscleanGreenAd()) {
            ComponentHook.cleanGreenAd(Id);
        }

        if (SettingUtils.iscleanTimFile()) {
            ComponentHook.cleanTimFile(Id);
        }
        if (SettingUtils.isBanRevoke()){
            RevokeHook.banRevoke(classLoader);
        }
        if (SettingUtils.iscleanBannner()) {
            ComponentHook.cleanBanner(Id);
        }
        ComponentHook.cleanCe(Id);
        ComponentHook.cleanPanellconLinearLayout(Id);
        if (SettingUtils.iscleanPendant()){
            FunctionHook.cleanPendant(classLoader);
        }
        if (SettingUtils.iscleanGift()){
            FunctionHook.cleanGift(classLoader);
        }
        if(SettingUtils.iscleankeyWordList()){
            FunctionHook.cleankeyWordList(classLoader);
        }
        if(SettingUtils.iscleanBubble()){
            FunctionHook.cleanBubble(classLoader);
        }
        if (SettingUtils.iscleanFont()){
            FunctionHook.cleanFont(classLoader);
            FunctionHook.cleanFontImage(classLoader);
        }
        if (SettingUtils.iscleanEmotionDrop()){
            FunctionHook.cleanEmotionDrop(classLoader);
        }
        if (SettingUtils.iscleanQunRemove()){
            ComponentHook.cleanQunRemove(Id);
        }
        if (SettingUtils.iscleanUpgrade()){
            FunctionHook.cleanUpgrade(classLoader);
        }
        if (SettingUtils.iscleanSplashAd()){
            DeleteFile.delete("data/data/com.tencent.mobileqq/files/splashpic","");
        }
        if (SettingUtils.isBanFlashPic()){
            FlashPicHook.banFlashPic(classLoader);
        }
        if (SettingUtils.iscleannow()){
            FunctionHook.cleanNow(classLoader);
        }
        if (getQQ_Version().compareTo("7.3.2")>=0){
            if (SettingUtils.iscleanRecommemd()){
                FunctionHook.cleanRecommemd(classLoader);
                DeleteFile.delete("data/data/com.tencent.mobileqq/files","recommemd_emotion_file_");
            }
            if (SettingUtils.iscleanEnterEffect()){
                FunctionHook.cleanEnterEffect(classLoader);
            }
            if (SettingUtils.iscleanMedal()){
                ComponentHook.cleanMedal(classLoader);
            }
            if (SettingUtils.iscleanSettingMain()) {
                ComponentHook.cleanSettingMain(classLoader);
            }
            if (getQQ_Version().compareTo("7.3.5")>=0){
                if(SettingUtils.iscleanSearch()){
                    FunctionHook.cleanSearch(classLoader);
                }
                if (SettingUtils.iscleanPicXiao()){
                    ComponentHook.cleanPicXiao(Id);
                }
            }
        }
    }

    public static String getQQ_Version(){
        String s;
        Context context=(Context)XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread",null),"currentActivityThread",new Object[0]),"getSystemContext",new Object[0]);
        try{
            s=context.getPackageManager().getPackageInfo("com.tencent.mobileqq",0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            XposedBridge.log(e);
            return null;
        }
        return s;
    }
}
