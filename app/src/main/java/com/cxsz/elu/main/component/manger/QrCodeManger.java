package com.cxsz.elu.main.component.manger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;

import com.cxsz.elu.R;
import com.cxsz.elu.main.App;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

/**
 * 二维码工具类
 */
public class QrCodeManger {

    private static QrCodeManger qRcodeManger;
    private OnShowCodeListener mOnShowCodeListener;

    public static QrCodeManger getIntances() {
        if (qRcodeManger == null) {
            synchronized (QrCodeManger.class) {
                if (qRcodeManger == null)
                    qRcodeManger = new QrCodeManger();
            }
        }
        return qRcodeManger;
    }

    public void getQRCode(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = encodeToQRWithLogo(url, 220, BitmapFactory.decodeResource(App.getContext().getResources(), R.mipmap.logo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mOnShowCodeListener.showBuyQrCodeImage(bitmap);
    }

    public void setOnShowCodeListener(OnShowCodeListener onShowCodeListener) {
        this.mOnShowCodeListener = onShowCodeListener;
    }

    public interface OnShowCodeListener {
        void showBuyQrCodeImage(Bitmap bitmap);
    }

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    //TODO 生成二维码
    public static Bitmap encodeToQRWidth(String contentsToEncode, int dimension) throws Exception {
        if (TextUtils.isEmpty(contentsToEncode)) return null;
        BarcodeFormat format = BarcodeFormat.QR_CODE;
        Map hints = new EnumMap(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix result = new MultiFormatWriter().encode(contentsToEncode, format, dimension, dimension, hints);
        int width = result.getWidth();
        int height = result.getHeight();
        boolean isFirstBlack = true;
        int startX = 0;
        int startY = 0;
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                if (result.get(x, y) && isFirstBlack) {
                    isFirstBlack = false;
                    startX = x;
                    startY = y;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        Matrix m = new Matrix();
        float sx = (width + 2f * startX) / width;
        float sy = (height + 2f * startY) / height;
        m.postScale(sx, sy);
        Bitmap qrBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(qrBitmap);
        canvas.translate(-startX, -startY);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, m, paint);
        canvas.save();
        return qrBitmap;
    }

    //生成带logo的二维码
    public static Bitmap encodeToQRWithLogo(String contentsToEncode, int dimension, Bitmap logo) throws Exception {
        return addLogo(encodeToQRWidth(contentsToEncode, dimension), logo);
    }

    //add logo on pic
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //set logo width 1/5 of pic width.
        float scaleFactor = srcWidth * 1.0f / 8 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }
        return bitmap;
    }
}
