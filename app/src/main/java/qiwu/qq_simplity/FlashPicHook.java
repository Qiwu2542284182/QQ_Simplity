package qiwu.qq_simplity;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.Method;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Deng on 2018/2/12.
 */

public class FlashPicHook {
    public static void banFlashPic(ClassLoader classLoader){
        try{
            Class<?>clazz=classLoader.loadClass("com.tencent.widget.CountDownProgressBar");
            XposedHelpers.findAndHookMethod(clazz, "a", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    return null;
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
        }catch (Exception e){
            XposedBridge.log(e);
        }
        try{
            final Class<?>claa=classLoader.loadClass("com.tencent.mobileqq.activity.aio.photo.AIOImageProviderService");
            Method[] methods=claa.getDeclaredMethods();
            for (Method method:methods){
                final Class<?>[]name=method.getParameterTypes();
                if (method.getName().equals("a")&&method.getGenericReturnType().toString().equals("void")&&name.length==1&&name[0].getName().equals("long")){
                    XposedBridge.hookMethod(method, new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                            return null;
                        }
                    });
                }
            }
        }catch (Exception e){
            XposedBridge.log(e);
        }
    }
}
