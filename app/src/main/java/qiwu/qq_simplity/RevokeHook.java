package qiwu.qq_simplity;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Deng on 2018/2/21.
 */

public class RevokeHook {
    public static boolean isRevoke=false;
    public static ArrayList messageuin=new ArrayList();
    public static void banRevoke(ClassLoader classLoader){
        try{
            Class<?>hookclass=classLoader.loadClass("com.tencent.mobileqq.app.message.BaseMessageManager");
            XposedHelpers.findAndHookMethod(hookclass, "a", ArrayList.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    isRevoke=false;
                }
            });
            XposedHelpers.findAndHookMethod(hookclass, "b", ArrayList.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    ArrayList arrayList=(ArrayList)param.args[0];
                    String a=arrayList.get(0).toString();
                    if (!messageuin.contains(a)){
                        messageuin.add(a);
                        isRevoke=true;
                    }else {
                        param.args[0]=null;
                    }
                }
            });
            Class<?>hookclass2=classLoader.loadClass("com.tencent.mobileqq.app.message.QQMessageFacade");
            XposedHelpers.findAndHookMethod(hookclass2, "b", String.class, int.class, long.class, boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (isRevoke){
                        param.args[2]=0;
                    }

                }
            });
            Class<?>hookclass3=classLoader.loadClass("com.tencent.mobileqq.graytip.UniteGrayTipParam");
            XposedHelpers.findAndHookConstructor(hookclass3,String.class,String.class,String.class,int.class,int.class,int.class,long.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    String message=(String)param.args[2];
                    if (isRevoke&&message.contains("撤回了一条")){
                        message=message.replace("撤回了","尝试撤回");
                    }
                    param.args[2]=message;
                }
            });
        }catch (Exception e){
            XposedBridge.log(e);
        }

    }
}
