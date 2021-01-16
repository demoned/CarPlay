package com.cxsz.elu.main.component;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import com.cxsz.elu.main.App;

import java.util.List;

/**
 * 获取app信息，版本号，版本名
 */
public class APPInfoUtils {

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), PackageManager.GET_CONFIGURATIONS).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName() {
        String versionName = null;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionName = App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), PackageManager.GET_CONFIGURATIONS).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 检查自己是不是系统应用
     */
    public static boolean isSystemApp() {
        Context ctx = App.getContext();
        PackageManager pm = ctx.getPackageManager();
        List<ApplicationInfo> installedApps = pm.getInstalledApplications(0);

        for (ApplicationInfo ai : installedApps) {
            if (ai.packageName.equals("com.net.cxsz.yiluwo_new")) {
                return (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            }
        }
        return false;
    }

    /**
     * 获取运行内存大小
     */
    public static long getRamSize() {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                Context ctx = App.getContext();
                ActivityManager actManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
                ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
                actManager.getMemoryInfo(memInfo);
                return memInfo.totalMem / 1024 / 1024;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取存储空间大小
     */
    public static long getStorageSize() {
        try {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long bytesAvailable = (long)stat.getBlockSize() * (long)stat.getBlockCount();
            return bytesAvailable / 1024 / 1024;
        } catch (Exception e) {
            return 0;
        }
    }
}
