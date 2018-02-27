package qiwu.qq_simplity;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Deng on 2018/2/16.
 */

public class RemoveImagine {
    public static void remove(final int id){
        XposedHelpers.findAndHookMethod(View.class, "setLayoutParams", ViewGroup.LayoutParams.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                View view=(View) param.thisObject;
                if (view.getId()==id){
                    ViewGroup.LayoutParams layoutParams=(ViewGroup.LayoutParams)param.args[0];
                    layoutParams.height=0;
                    layoutParams.width=0;
                    view.setVisibility(View.GONE);
                }
            }
        });
    }
    public static void removeDrawable(final int id){
        XposedHelpers.findAndHookMethod(Resources.class, "getDrawable", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                int i=(int)param.args[0];
                if (id==i){
                    param.setResult(null);
                }
            }
        });
    }

}
