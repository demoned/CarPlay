package com.cxsz.framework.tool;

import android.os.Environment;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Author : Horice
 * Date : 2017/3/13.
 */

public class LoggerUtil {
    private static boolean sDebug = false;

    public static synchronized void init(boolean debug, String tag) {
        sDebug = debug;
        if (debug) {
            Logger.init(tag).hideThreadInfo().methodCount(0);
        }
    }

    public static void d(String tag, String msg) {
        if (sDebug) {
            Logger.t(tag).d(msg);
        }
        if (sDebug && (tag.equals("gps") || tag.equals("wifi"))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            save(time + "-TAG:" + tag + "\tlog:" + msg + "\n");
        }
    }

    public static void i(String tag, String msg) {
        if (sDebug) {
            Logger.t(tag).i(msg);
        }
        if (sDebug && (tag.equals("gps") || tag.equals("wifi"))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            save(time + "-TAG:" + tag + "\tlog:" + msg + "\n");
        }
    }

    public static void d(String msg) {
        if (sDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if (sDebug) {
            Logger.i(msg);
        }
    }

    public static void w(String msg) {
        if (sDebug) {
            Logger.w(msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sDebug) {
            Logger.w(tag,msg);
        }
    }

    public static void e(String msg) {
        if (sDebug) {
            Logger.e(msg);
        }
    }

    public static void e(String msg, Throwable throwable) {
        if (sDebug) {
            Logger.e(throwable, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebug) {
            Logger.e(tag, msg);
        }
    }

    public static void json(String msg) {
        if (sDebug) {
            Logger.json(msg);
        }
    }

    //TODO：use "false" before release.
    private static boolean isPathExist = true;
    private static boolean isStoreInFile = false;//是不是要写log

    private static boolean save(String pathString, String fileName, String msg) {
        if (isStoreInFile) {
            File pathFile = new File(pathString);
            if (!pathFile.exists()) {
                isPathExist = pathFile.mkdirs();
            }
            if (isPathExist) {
                File file = new File(pathFile, fileName);
                try {
                    FileOutputStream e = new FileOutputStream(file, true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(e, "UTF-8");
                    outputStreamWriter.write(msg);
                    outputStreamWriter.flush();
                    e.close();
                    Log.d("@@@", "save: " + "写入文件成功");
                    return true;
                } catch (FileNotFoundException var6) {
                    var6.printStackTrace();
                    return false;
                } catch (UnsupportedEncodingException var7) {
                    var7.printStackTrace();
                    return false;
                } catch (IOException var8) {
                    var8.printStackTrace();
                    return false;
                } catch (Exception var9) {
                    var9.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean save(String msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时");
        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return save(Environment.getExternalStorageDirectory().getPath() + "/Log_wo", time + ".txt", msg);
    }
}
