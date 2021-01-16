package com.cxsz.elu.main.component;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cxsz.elu.main.model.bean.LocationInfoBean;
import com.cxsz.framework.tool.LoggerUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * @author demons
 * @version v1.0
 * @Title: LocationUtil
 * @Package com.bojun.utils
 * @Description: 高德定位辅助工具类
 * @date 12/3/20 2:35 PM
 */
public class LocationUtil implements AMapLocationListener {
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private volatile static LocationUtil instance = null;

    public static LocationUtil getInstance() {
        if (instance == null) {
            synchronized (LocationUtil.class) {
                instance = new LocationUtil();
            }
        }
        return instance;
    }

    //初始化定位服务相关信息
    public void initLocationInfo(Context context) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setGpsFirst(false);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(false);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        // 设置是否开启缓存
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    //位置信息获取
    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            LocationInfoBean locationInfoBean = new LocationInfoBean(aMapLocation.getCityCode(), aMapLocation.getCity(), aMapLocation.getAddress(), aMapLocation.getLongitude(),
                    aMapLocation.getLatitude(), aMapLocation.getBearing(), aMapLocation.getSpeed(), aMapLocation.getStreet(), aMapLocation.getTime());
            LoggerUtil.d("定位信息：" +locationInfoBean.toString());
            EventBus.getDefault().post(locationInfoBean);
        }
    }

    /**
     * 启动定位
     */
    public void startLocation() {
        mLocationClient.startLocation();
    }

    /**
     * 关闭定位
     */
    public void onCloseLocationService() {
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }
}
