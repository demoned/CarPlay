package com.cxsz.elu.main.component;

/**
 * 数据格式转换类
 */
public class FormatUtils {
    /**
     * put xxxMB get xGBxxMB.
     */
    public static String formatMB(int use) {
        String mb;
        if (use >= 0) {
            if (use >= 0 && use < 1024) {
                mb = use + "M";
            } else {
                int GB = use / 1024;
                int MB = use % 1024;
                mb = GB + "G " + MB + "M";
            }
            return mb;
        } else {
            return "未知";
        }
    }

    public static String formatFlowSize(double size) {
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = String.format("%.1f", size) + "M";
        } else if (size >=1024) {
            double sizeForG = size / 1024;
            fileSizeString = String.format("%.1f", sizeForG) + "G";
        }
        return fileSizeString;
    }

    //支付状态反馈，目前已转到H5
    public static String formatSMSString(String total) {
        if (total.contains("\\n")) {
            String s = total.replaceAll("\\\\n", "\n");
            return s;
        } else {
            return total;
        }
    }
}
