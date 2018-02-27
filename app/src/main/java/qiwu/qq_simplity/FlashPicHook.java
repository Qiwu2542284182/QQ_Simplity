package qiwu.qq_simplity;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Deng on 2018/2/12.
 */

public class FlashPicHook {
    public static void banFlashPic(ClassLoader classLoader){
        try{
            Class<?>clazz=classLoader.loadClass("com.tencent.widget.CountDownProgressBar");
            XposedHelpers.findAndHookMethod(clazz, "setTotalMills", long.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[0]=-4500;
                }
            });
        }catch (Exception e){
            XposedBridge.log(e);
        }
        try{
            Class<?>claa1=classLoader.loadClass("com.tencent.mobileqq.dating.HotChatFlashPicActivity");
            XposedHelpers.findAndHookMethod(claa1, "onTouch", View.class, MotionEvent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[1]=MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,0,0,0);
                }
            });
            XposedHelpers.findClass("com.tencent.mobileqq.dating.HotChatFlashPicActivity",classLoader);
        }catch (Exception e){
            XposedBridge.log(e);
        }
        try{
            Class<?>claa=classLoader.loadClass("com.tencent.mobileqq.data.MessageRecord");
            XposedHelpers.findAndHookMethod(claa, "saveExtInfoToExtStr", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    String a=(String)param.args[0];
                    String b=(String)param.args[1];
                    if (a.equals("commen_flash_pic")&&b.equals("true")){
                        param.args[1]="false";
                    }
                }
            });
        }catch (Exception e){
            XposedBridge.log(e);
        }
    }
}
