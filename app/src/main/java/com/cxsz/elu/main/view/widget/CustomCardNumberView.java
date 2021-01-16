package com.cxsz.elu.main.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.framework.tool.CommonUtils;


/**
 * 卡号自定义显示框
 */

public class CustomCardNumberView extends LinearLayout {

    public CustomCardNumberView(Context context) {
        super(context);
    }

    public CustomCardNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context, String cardNumber) {
        removeAllViews();
        int editViewNum = cardNumber.length();
        int width = 18 - editViewNum / 3;
        int height = 24 - editViewNum / 3;
        int textSize = editViewNum;
        //设置方格间距
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtils.dip2px(context, width), CommonUtils.dip2px(context, height));
        params.setMargins(CommonUtils.dip2px(context, 4), 0, 0, 0);
        if (cardNumber != null && !TextUtils.isEmpty(cardNumber)) {
            //纯数字
            char[] chars = cardNumber.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                TextView textView = new TextView(context);
                textView.setBackgroundResource(R.mipmap.card_number_bg);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(textSize);
                textView.getPaint().setFakeBoldText(true);
                textView.setLayoutParams(params);
                textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                textView.setTextColor(Color.parseColor("#C59938"));
                textView.setText(String.valueOf(chars[i]));
                addView(textView);
            }
        }
    }
}
