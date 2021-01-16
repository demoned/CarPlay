package com.cxsz.elu.main.component.service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;

import com.cxsz.elu.main.App;
import com.cxsz.elu.main.component.MQTTClientImpl;
import com.cxsz.elu.main.component.MQTTUtils;
import com.cxsz.elu.main.component.OnGetMealInfoListener;
import com.cxsz.elu.main.model.LockScreenUploadBean;
import com.cxsz.elu.main.model.bean.EventBean;
import com.cxsz.elu.main.model.bean.LocationInfoBean;
import com.cxsz.elu.main.model.bean.LocationUploadBean;
import com.cxsz.elu.main.model.bean.MQTTResponseBean;
import com.cxsz.elu.main.model.bean.RegisterDeviceBean;
import com.cxsz.elu.main.model.net.NetResponseService;
import com.cxsz.elu.main.view.activity.LockActivity;
import com.cxsz.elu.main.view.widget.MainNoticeDialog;
import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.tool.ActivityManager;
import com.cxsz.framework.tool.LoggerUtil;
import com.cxsz.framework.tool.SpUtil;
import com.demons.alivelibrary.AbsWorkService;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 简  述  套餐查询后台服务类
 * 作  者  demons
 * 类  名  MealRequestServiceImpl
 * 时  间  2020/5/25 11:09 PM
 */
public class MealRequestServiceImpl extends AbsWorkService {
    public static final String LOG_TAG = "OneNet";
    public static Disposable sDisposable;
    //是否 任务完成, 不再需要服务运行?
    public static boolean sShouldStopService;
    private MainNoticeDialog mainNoticeWindows;
    private MQTTClientImpl mqttClient;
    private String deviceName;
    private LocationUploadBean locationUploadBean;
    private boolean isPowerOn;

    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) sDisposable.dispose();
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }

    /**
     * 是否 任务完成, 不再需要服务运行?
     *
     * @return 应当停止服务, true; 应当启动服务, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return sShouldStopService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isPowerOn = true;
        EventBus.getDefault().register(this);
        String token = getToken();
        RegisterDeviceBean registerDeviceBean = SpUtil.getObject(App.getContext(), KeyConstants.ONE_NET_DEVICE_INFO);
        if (null != registerDeviceBean) {
            initOneNet(registerDeviceBean);
        } else {
            NetResponseService.getInstance().registerDevice(token);
        }
    }

    private String getToken() {
        String version = "2020-05-29";
        String resourceName = "userid/" + KeyConstants.USER_ID;
        String expirationTime = System.currentTimeMillis() + 10 + "";
        String signatureMethod = MQTTUtils.SignatureMethod.SHA1.name().toLowerCase();
        String token = null;
        try {
            token = MQTTUtils.assembleToken(version, resourceName, expirationTime, signatureMethod, KeyConstants.USER_ACCESS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        sDisposable = Observable
                .interval(3, TimeUnit.MINUTES)
                //取消任务时取消定时唤醒
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        cancelJobAlarmSub();
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long count) throws Exception {
                        //在这里去执行定时操作逻辑
                        NetResponseService.getInstance().getSimByParam(new OnGetMealInfoListener() {
                            @Override
                            public void showLoading() {

                            }

                            @Override
                            public void closeLoading() {

                            }
                        });
                        NetResponseService.getInstance().getSimPackage(new OnGetMealInfoListener() {
                            @Override
                            public void showLoading() {

                            }

                            @Override
                            public void closeLoading() {

                            }
                        });
                    }
                });
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        stopService();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNoticeEvent(EventBean eventBean) {
        if (eventBean.getCode() == KeyConstants.SHOW_CURRENT_MEAL_WINDOW) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return;
                } else {
                    //Android6.0以上
                    if (null != mainNoticeWindows) {
                        mainNoticeWindows.dismiss();
                        mainNoticeWindows = null;
                    }
                    mainNoticeWindows = new MainNoticeDialog(App.getContext(), true, "您的当前套餐为" + eventBean.getIccidNumberFromNet(), true);
                    mainNoticeWindows.show();
                }
            } else {
                //Android6.0以下，不用动态声明权限
                if (null != mainNoticeWindows) {
                    mainNoticeWindows.dismiss();
                    mainNoticeWindows = null;
                }
                mainNoticeWindows = new MainNoticeDialog(App.getContext(), true, "您的当前套餐为" + eventBean.getIccidNumberFromNet(), true);
                mainNoticeWindows.show();
            }
        } else if (eventBean.getCode() == KeyConstants.SHOW_MEAL_END_DAY_WINDOW) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return;
                } else {
                    //Android6.0以上
                    if (null != mainNoticeWindows) {
                        mainNoticeWindows.dismiss();
                        mainNoticeWindows = null;
                    }
                    mainNoticeWindows = new MainNoticeDialog(App.getContext(), true, "您的套餐时间快过期,请及时充值!", true);
                    mainNoticeWindows.show();
                }
            } else {
                //Android6.0以下，不用动态声明权限
                if (null != mainNoticeWindows) {
                    mainNoticeWindows.dismiss();
                    mainNoticeWindows = null;
                }
                mainNoticeWindows = new MainNoticeDialog(App.getContext(), true, "您的套餐时间快过期,请及时充值!", true);
                mainNoticeWindows.show();
            }
        } else if (eventBean.getCode() == KeyConstants.SHOW_MEAL_END_FLOW_WINDOW) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return;
                } else {
                    //Android6.0以上
                    if (null != mainNoticeWindows) {
                        mainNoticeWindows.dismiss();
                        mainNoticeWindows = null;
                    }
                    mainNoticeWindows = new MainNoticeDialog(App.getContext(), true, "您的流量余额不足,请及时充值!", true);
                    mainNoticeWindows.show();
                }
            } else {
                //Android6.0以下，不用动态声明权限
                if (null != mainNoticeWindows) {
                    mainNoticeWindows.dismiss();
                    mainNoticeWindows = null;
                }
                mainNoticeWindows = new MainNoticeDialog(App.getContext(), true, "您的流量余额不足,请及时充值!", true);
                mainNoticeWindows.show();
            }
        } else if (eventBean.getCode() == KeyConstants.INIT_ONE_NET) {
            RegisterDeviceBean registerDeviceBean = SpUtil.getObject(App.getContext(), KeyConstants.ONE_NET_DEVICE_INFO);
            initOneNet(registerDeviceBean);
        }
    }

    private void initOneNet(RegisterDeviceBean registerDeviceBean) {
        // 应用长连接服务地址
        String broker = KeyConstants.HOST_URL;
        // 设备名称
        deviceName = registerDeviceBean.getName();
        String key = registerDeviceBean.getSec_key();
        mqttClient = new MQTTClientImpl(broker, deviceName, key, new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                LoggerUtil.e(LOG_TAG, cause.getMessage());
            }

            @SuppressLint("NewApi")
            @Override
            public void messageArrived(String topic, MqttMessage message) {
                LoggerUtil.d(LOG_TAG, topic + "====" + message.toString());
                MQTTResponseBean mqttResponseBean = new Gson().fromJson(message.toString(), MQTTResponseBean.class);
                try {
                    LockScreenUploadBean lockScreenUploadBean = new LockScreenUploadBean();
                    lockScreenUploadBean.setId(mqttResponseBean.getId());
                    lockScreenUploadBean.setCode(200);
                    lockScreenUploadBean.setMsg("success");
                    mqttClient.pubProperty(new Gson().toJson(lockScreenUploadBean));
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                int status = mqttResponseBean.getParams().getCxkgxp();
                if (1 == status) {
                    Intent intent = new Intent(MealRequestServiceImpl.this, LockActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MealRequestServiceImpl.this.startActivity(intent);
                } else if (2 == status) {
                    ActivityManager.getManager().finishActivity(LockActivity.class);
                } else if (3 == status) {
                    isPowerOn = true;
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                LoggerUtil.d(LOG_TAG, "发送完成:" + token.isComplete());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocationInfoBean event) {
        if (event.getLatitude() != 0 && event.getLongitude() != 0 && isPowerOn) {
            try {
                long time = System.currentTimeMillis();
                locationUploadBean = new LocationUploadBean();
                locationUploadBean.setId(String.valueOf(time));
                locationUploadBean.setVersion("1.0");
                LocationUploadBean.ParamsBean paramsBean = new LocationUploadBean.ParamsBean();
                LocationUploadBean.ParamsBean.$GeoLocationBean $GeoLocationBean = new LocationUploadBean.ParamsBean.$GeoLocationBean();
                LocationUploadBean.ParamsBean.$GeoLocationBean.ValueBean valueBean = new LocationUploadBean.ParamsBean.$GeoLocationBean.ValueBean();
                valueBean.setLatitude(event.getLatitude());
                valueBean.setLongitude(event.getLongitude());
                $GeoLocationBean.setValue(valueBean);
                paramsBean.set$GeoLocation($GeoLocationBean);
                locationUploadBean.setParams(paramsBean);
                if (null == mqttClient) {
                    return;
                }
                mqttClient.pubDesired(new Gson().toJson(locationUploadBean));
                isPowerOn = false;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 任务是否正在运行?
     *
     * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        //若还没有取消订阅, 就说明任务仍在运行.
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Override
    public IBinder onBind(Intent intent, Void v) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
        System.out.println("保存数据到磁盘。");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        try {
            mqttClient.disConnect();
            mqttClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
