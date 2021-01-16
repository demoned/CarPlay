package com.cxsz.elu.main.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cxsz.elu.R;
import com.cxsz.elu.main.component.LocationUtil;
import com.cxsz.elu.main.component.service.MealRequestServiceImpl;
import com.cxsz.elu.main.view.fragment.PackageInformationFragment;
import com.cxsz.framework.base.BaseActivity;
import com.cxsz.framework.tool.LoggerUtil;
import com.demons.alivelibrary.DaemonEnv;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 简  述  套餐详情主视图
 * 作  者  demons
 * 类  名  MainUi
 * 时  间  2020/5/25 10:59 PM
 */
public class MainUi extends BaseActivity {
    private LinearLayout layLoading;
    //倒计时总时长
    private int allCount = 3;
    private Disposable disposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView(Bundle savedInstanceState) {
        LocationUtil.getInstance().startLocation();
        layLoading = findViewById(R.id.layout_loading);
        createTask();
        loadRootFragment(R.id.meal_info_area, PackageInformationFragment.getInstance());
    }

    private void createTask() {
        disposable = Flowable.intervalRange(0, allCount, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCancel(new Action() {
                    @Override
                    public void run() throws Exception {
                        LoggerUtil.e(TAG, "任务结束！");
                    }
                })
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        LoggerUtil.e(TAG, "倒计时剩余：" + num);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        checkNetwork();
                    }
                })
                .subscribe();
    }

    /**
     * 检查网络
     */
    private void checkNetwork() {
        try {
//            if (TextUtils.isEmpty(SimCardUtils.getIccId(App.getContext()))) {
//                layLoading.setVisibility(View.VISIBLE);
//            } else {
//                layLoading.setVisibility(View.GONE);
                stopCountDown();
                MealRequestServiceImpl.sShouldStopService = false;
                DaemonEnv.startServiceMayBind(MealRequestServiceImpl.class);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭倒计时
     */
    private void stopCountDown() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    @Override
    public void finish() {
        super.finish();
        stopCountDown();
    }
}
