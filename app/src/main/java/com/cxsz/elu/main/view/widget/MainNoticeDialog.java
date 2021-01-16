package com.cxsz.elu.main.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.elu.main.view.activity.MainUi;

/**
 * 绑定手机的提示窗口
 * Created by yangqian on 2017/2/3.
 */

public class MainNoticeDialog extends Dialog {
    public Context context;
    public View closeDialog;
    private TextView noticeInfo;
    private TextView noticeDetailInfo;
    public boolean isCancel = true;
    private String content;
    private boolean isStay;

    public MainNoticeDialog(Context context, boolean isCancel, String content, boolean isStay) {
        super(context, R.style.custom_dialog_style);
        this.context = context;
        this.isCancel = isCancel;
        this.content = content;
        this.isStay = isStay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_notice_layout);
        // 设置弹窗外可点击
        setCanceledOnTouchOutside(isCancel);
        setCancelable(isCancel);
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        noticeInfo = findViewById(R.id.notice_info);
        noticeDetailInfo = findViewById(R.id.notice_detail_info);
        closeDialog = findViewById(R.id.close_bind_dialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (isStay) {
                    Intent intent = new Intent(context, MainUi.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
        noticeInfo.setText("温馨提示");
        noticeDetailInfo.setText(content);
    }

}
