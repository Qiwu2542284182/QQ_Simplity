package qiwu.qq_simplity;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
                    layoutParams.height=1;
                    layoutParams.width=0;
                    view.setVisibility(View.GONE);
                }
            }
        });
    }
    public static void removeDrawable(final int id, final int id2){
        XposedHelpers.findAndHookMethod(Resources.class, "getDrawable", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                int i=(int)param.args[0];
                if (id==i){
                    param.args[0]=id2;
                }
            }
        });
        XposedHelpers.findAndHookMethod(ImageView.class, "setImageResource", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                int i2 = (int) param.args[0];
                if (i2==id) {
                    param.args[0] = id2;
                }
                    
            }
        });

    }

}
