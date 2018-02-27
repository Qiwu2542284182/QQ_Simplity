package qiwu.qq_simplity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SettingActivity extends Activity{
    private SettingFragment mSettingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingFragment=new SettingFragment();
        getFragmentManager().beginTransaction().replace(getResources().getIdentifier("content","id","android"),mSettingFragment).commit();
    }

    public  class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener,Preference.OnPreferenceChangeListener,Preference.OnPreferenceClickListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setWorldReadable();
            addPreferencesFromResource(R.xml.setting);
            checkstate();
            ((Preference)getPreferenceScreen().findPreference("CA")).setOnPreferenceChangeListener(this);
            ((Preference)getPreferenceScreen().findPreference("DB")).setOnPreferenceClickListener(this);
            try {
                PackageManager manager=getActivity().getPackageManager();
                PackageInfo info=manager.getPackageInfo("com.tencent.mobileqq",0);
                String version=info.versionName;
                if (version.compareTo("7.3.2")>0){
                    findPreference("DA").setSummary("QQ当前版本："+version+"（支持的版本）");
                }else {
                    findPreference("DA").setSummary("QQ当前版本："+version+"（不支持的版本）");
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                findPreference("DA").setSummary("未安装QQ");
            }

        }
        @Override
        public void onPause() {
            super.onPause();
            PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onResume() {
            super.onResume();
            PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("CA")){
            }else {
                Toast.makeText(getActivity(),"设置已保存，重启QQ生效",Toast.LENGTH_SHORT).show();
            }
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setWorldReadable();
                }
            },200);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (preference.getKey().equals("CA")){
                hideIcon((Boolean)newValue);
            }
            return true;
        }

        private void setWorldReadable() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                File dataDir = new File(getApplicationInfo().dataDir);
                File prefsDir = new File(dataDir, "shared_prefs");
                File prefsFile = new File(prefsDir, getPreferenceManager().getSharedPreferencesName() + ".xml");
                if (prefsFile.exists()) {
                    for (File file : new File[]{dataDir, prefsDir, prefsFile}) {
                        file.setReadable(true,false);
                        file.setExecutable(true,false);
                    }
                }
            } else {
                getPreferenceManager().setSharedPreferencesMode(MODE_WORLD_READABLE);
            }
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference.getKey().equals("DB")){
                try {
                    String str = "market://details?id=" + BuildConfig.APPLICATION_ID;
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    intent.setPackage("com.coolapk.market");
                    intent.setFlags(0x10000000);
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.coolapk.com/apk/" + BuildConfig.APPLICATION_ID)));
                }
            }
            return true;
        }
    }

    private void checkstate(){
        if (!isModuleActive()){
            new AlertDialog.Builder(this)
                    .setMessage("xposed模块未激活，请到xposed框架的模块列表勾选本模块并重启手机或检查是否禁用资源钩子，否则模块无法生效")
                    .setPositiveButton("打开xposed框架", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=getPackageManager().getLaunchIntentForPackage("de.robv.android.xposed.installer");
                            if (intent!=null){
                                startActivity(intent);
                            }else {
                                Toast.makeText(SettingActivity.this,"未安装xposed框架",Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("忽略",null).show();
        }
    }
    private void hideIcon(boolean b){
        ComponentName componentName = new ComponentName(this, SettingActivity.this.getClass().getName()+"Alias");
        PackageManager packageManager = getPackageManager();
        if (b){
            packageManager.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
        }else{
            packageManager.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,PackageManager.DONT_KILL_APP);
        }
    }
    private boolean isModuleActive(){
        return false;
    }
}
