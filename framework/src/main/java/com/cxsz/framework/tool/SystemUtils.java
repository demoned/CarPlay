package com.cxsz.framework.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.cxsz.framework.R;
import com.cxsz.framework.constant.KeyConstants;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by cxsz-hp11 on 2018/6/2.
 */

public class SystemUtils {

    public static boolean isDayOrNight() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH", Locale.CHINA);// HH:mm:ss
        Date date = new Date();
        int hour = Integer.parseInt(simpleDateFormat.format(date));
        LoggerUtil.e("Date获取当前日期时间" + simpleDateFormat.format(date));
        if (hour > 18 || hour < 7) {
            return true;
        }
        return false;
    }

    public static void startMain(Activity activity, final Intent intent, int time) {
        startMain(activity, intent, time, true);
    }

    public static void startMain(final Activity activity, final Intent intent, int time, final boolean flag) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(intent);
                activity.finish();
            }
        }, time);
    }

    /**
     * 点击两次退出APP
     */
    public static long mExitTime;

    @SuppressLint("MissingPermission")
    public static void exit(Context context) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(context, context.getString(R.string.one_more_exit), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            try {
                activityMgr.killBackgroundProcesses(context.getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.exit(0);
        }
    }

    //添加头部的token
    public static Map<String, String> getHeaderMap(Context context) {
        Map<String, String> mapHeader = new HashMap<>();
        String userId = SpUtil.getString(context, KeyConstants.NET_USER_ID, "");
        String accessToken = SpUtil.getString(context, KeyConstants.NET_TOKEN, "");
        if (!userId.equals("") && !accessToken.equals("")) {
            mapHeader.put(KeyConstants.NET_USER_ID, userId);
            mapHeader.put(KeyConstants.NET_TOKEN, accessToken);
            return mapHeader;
        } else return null;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getDays(String endTime, String startTime) {
        int days = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = format.parse(endTime);
            Date date = format.parse(startTime);
            days = differentDays(date, date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static int getRemainDays(String endTime) {
        int days = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = format.parse(endTime);
            Date date = format.parse(format.format(new Date()));
            days = differentDays(date, date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static void writeData(int code, String contentInfo) {
        String filePath = "/sdcard/AppInfo/";
        String fileName;
        if (code == 1) {
            fileName = "buyAppInfo.txt";
        } else if (code == 2) {
            fileName = "appSign.txt";
        } else {
            fileName = "appInfo.txt";
        }
        writeTxtToFile(contentInfo, filePath, fileName);
    }

    // 将字符串写入到文本文件中
    private static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    //生成文件
    private static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    //生成文件夹
    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    /**
     * 转换 yyyy-MM-dd HH:mm:ss 格式时间为毫秒值
     *
     * @param date
     * @return
     */
    public static long stringDateToLong(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }
}
