/**
 *
 */
package com.cxsz.framework.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * 土司工具类
 */
public class ToastUtil {

    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
