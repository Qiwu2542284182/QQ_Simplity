package qiwu.qq_simplity;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Deng on 2018/2/12.
 */

public class CheckActive {
    public static void isActive(final XC_LoadPackage.LoadPackageParam loadPackageParam){
        if (!loadPackageParam.packageName.equals("qiwu.qq_simplity"))return;
        XposedHelpers.findAndHookMethod("qiwu.qq_simplity.SettingActivity",loadPackageParam.classLoader,"isModuleActive", XC_MethodReplacement.returnConstant(true));
    }
}
