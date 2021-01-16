package com.cxsz.elu.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.cxsz.elu.R;
import com.cxsz.elu.main.view.activity.MainUi;
import com.cxsz.elu.main.view.widget.NotifyDialog;
import com.cxsz.framework.base.BaseActivity;
import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.tool.LoggerUtil;
import com.cxsz.framework.tool.SystemUtils;

/**
 * 简  述  欢迎页
 * 作  者  demons
 * 类  名  WelcomeActivity
 * 时  间  2020/5/25 10:59 PM
 */
public class WelcomeActivity extends BaseActivity {
    private NotifyDialog requestPermissionDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.welcome_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        checkPermissions();
    }

    /**
     * 动态权限管理
     */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions;
            if (!checkPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                } else {
                    permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                }
                ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, KeyConstants.PERMISSION_STORAGE_CODE);
            } else if (!checkPermissionGranted(Manifest.permission.READ_PHONE_STATE)) {
                permissions = new String[]{Manifest.permission.READ_PHONE_STATE};
                ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, KeyConstants.PERMISSION_READ_PHONE_STATE_CODE);
            } else if (!checkPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, KeyConstants.PERMISSION_ACCESS_FINE_LOCATION);
            } else if (!checkPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
                ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, KeyConstants.PERMISSION_ACCESS_COARSE_LOCATION);
            } else {
                openView();
            }
        } else {
            openView();
        }
    }

    private void openView() {
        SystemUtils.startMain(WelcomeActivity.this, new Intent(WelcomeActivity.this, MainUi.class), 1000);
    }

    /**
     * 判断权限是否授予
     *
     * @param permission 权限
     */
    private boolean checkPermissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 显示权限请求提示框
     *
     * @param permission  权限
     * @param requestCode 请求码
     */
    private void showRequestPermissionDialog(String permission, int requestCode) {
        if (TextUtils.isEmpty(permission)) return;
        String explanation = "";
        switch (requestCode) {
            case KeyConstants.PERMISSION_STORAGE_CODE:
                explanation = getString(R.string.request_sdcard_permission);
                break;
            case KeyConstants.PERMISSION_READ_PHONE_STATE_CODE:
                explanation = getString(R.string.request_read_phone_state_permission);
                break;
            case KeyConstants.PERMISSION_ACCESS_FINE_LOCATION:
            case KeyConstants.PERMISSION_ACCESS_COARSE_LOCATION:
                explanation = getString(R.string.request_access_coarse_location_permission);
                break;
            default:
                break;
        }
        if (requestPermissionDialog == null) {
            requestPermissionDialog = NotifyDialog.newInstance(getString(R.string.dialog_tips), explanation, R.string.dialog_exit, R.string.grant,
                    new NotifyDialog.OnNegativeClickListener() {
                        @Override
                        public void onClick() {
                            requestPermissionDialog.dismiss();
                            requestPermissionDialog = null;
                            finish();
                        }
                    }, new NotifyDialog.OnPositiveClickListener() {
                        @Override
                        public void onClick() {
                            requestPermissionDialog.dismiss();
                            requestPermissionDialog = null;
                            checkPermissions();
                        }
                    });
        }
        if (!requestPermissionDialog.isShowing() && !isFinishing()) {
            requestPermissionDialog.setContent(explanation);
            requestPermissionDialog.show(getSupportFragmentManager(), "request_permission_dialog");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestPermissionDialog != null) {
            if (requestPermissionDialog.isShowing()) {
                requestPermissionDialog.dismiss();
            }
            requestPermissionDialog = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KeyConstants.CHECK_GPS_CODE) {
            checkPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        }
        boolean isOk = true;
        for (int i = 0; i < grantResults.length; i++) {
            int result = grantResults[i];
            LoggerUtil.w("requestCode : " + requestCode + " ,result = " + result);
            if (result != PackageManager.PERMISSION_GRANTED) {
                String permission = null;
                if (i < permissions.length) {
                    permission = permissions[i];
                }
                if (!TextUtils.isEmpty(permission)) {
                    LoggerUtil.w("permission : " + permission);
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(WelcomeActivity.this, permission)) {
                        finish();
                    } else {
                        showRequestPermissionDialog(permission, requestCode);
                    }
                    isOk = false;
                    break;
                }
            }
        }
        if (isOk) {
            checkPermissions();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);//true对任何Activity都适用
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
