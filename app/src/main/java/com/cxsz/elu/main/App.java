package com.cxsz.elu.main;

import android.app.Application;

import com.cxsz.elu.main.component.LocationUtil;
import com.cxsz.elu.main.component.service.MealRequestServiceImpl;
import com.cxsz.framework.constant.NetService;
import com.cxsz.framework.tool.LoggerUtil;
import com.demons.alivelibrary.DaemonEnv;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

public class App extends Application {
    private static App instance;

    public static App getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LoggerUtil.init(NetService.IS_DEBUG, "FlowSystem");
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance().setExcludeFontScale(true);
        DaemonEnv.initialize(this, MealRequestServiceImpl.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        LocationUtil.getInstance().initLocationInfo(this);
    }
}
