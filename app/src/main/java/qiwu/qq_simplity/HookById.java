package qiwu.qq_simplity;


import de.robv.android.xposed.XposedHelpers;


/**
 * Created by Deng on 2018/2/16.
 */

public class HookById {
    public static int getDrawableId(Class c,String drawableName){
        return XposedHelpers.getStaticIntField(c,drawableName);
    }
    public static int getLayoutId(Class c,String layoutName){
        return XposedHelpers.getStaticIntField(c,layoutName);
    }
    public static int getId(Class c,String idName){
        return XposedHelpers.getStaticIntField(c,idName);
    }
    public static int getDimenId(Class c,String dimenName){
        return XposedHelpers.getStaticIntField(c,dimenName);
    }

}
