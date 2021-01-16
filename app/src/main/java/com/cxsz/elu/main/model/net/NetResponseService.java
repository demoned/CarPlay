package com.cxsz.elu.main.model.net;

import com.cxsz.elu.main.App;
import com.cxsz.elu.main.component.IntelligentDiagnosisListener;
import com.cxsz.elu.main.component.NetDetectUtils;
import com.cxsz.elu.main.component.OnGetMealInfoListener;
import com.cxsz.elu.main.component.OnGetWeChatInfoListener;
import com.cxsz.elu.main.model.bean.EventBean;
import com.cxsz.elu.main.model.bean.RegisterDeviceBean;
import com.cxsz.elu.main.model.bean.SimDetailInfo;
import com.cxsz.elu.main.model.bean.SimPackageBean;
import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.net.CodeData;
import com.cxsz.framework.net.IntelligentDiagnosisResponseResult;
import com.cxsz.framework.net.OnSuccessAndFaultListener;
import com.cxsz.framework.net.OnSuccessAndFaultSub;
import com.cxsz.framework.net.OneNetCodeData;
import com.cxsz.framework.net.WeChatInfoBean;
import com.cxsz.framework.tool.CommonUtils;
import com.cxsz.framework.tool.LoggerUtil;
import com.cxsz.framework.tool.SpUtil;
import com.cxsz.framework.tool.SystemUtils;
import com.cxsz.framework.tool.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.greenrobot.eventbus.EventBus;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * 应用接口回调数据处理类
 */
public class NetResponseService {
    private IntelligentDiagnosisListener intelligentDiagnosisListener;


    private List<SimPackageBean.BodyBean> bodyBeans = new ArrayList<>();

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final NetResponseService INSTANCE = new NetResponseService();
    }

    //获取单例
    public static NetResponseService getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void setIntelligentDiagnosisListener(IntelligentDiagnosisListener intelligentDiagnosisListener) {
        this.intelligentDiagnosisListener = intelligentDiagnosisListener;
    }

    public SimPackageBean.BodyBean getSimPackageBean(LinkedTreeMap mapData, SimPackageBean.BodyBean bodyBean) {
        if ((mapData.get("status")) != null) {
            Double status = (Double) mapData.get("status");
            bodyBean.setStatus(status);
        }
        if ((mapData.get("packageTraffic")) != null) {
            Double packageTraffic = (Double) mapData.get("packageTraffic");
            bodyBean.setPackageTraffic(packageTraffic);
        }
        if ((mapData.get("useTraffic")) != null) {
            Double useTraffic = (Double) mapData.get("useTraffic");
            bodyBean.setUseTraffic(useTraffic);
        } else {
            bodyBean.setUseTraffic(0);
        }

        String statusStr = (String) mapData.get("statusStr");
        bodyBean.setStatusStr(statusStr);
        String packageName = (String) mapData.get("packageName");
        bodyBean.setPackageName(packageName);
        String packageActiveTime = (String) mapData.get("packageActiveTime");
        bodyBean.setPackageActiveTime(packageActiveTime);
        String displayPackageName = (String) mapData.get("displayPackageName");
        bodyBean.setDisplayPackageName(displayPackageName);
        String packageEndTime = (String) mapData.get("packageEndTime");
        bodyBean.setPackageEndTime(packageEndTime);
        return bodyBean;
    }


    /**
     * 获取套餐详情
     */
    public void getSimByParam(OnGetMealInfoListener onGetMealInfoListener) {
        if (!NetDetectUtils.isNetWorkAvailable()) {
            return;
        }
        onGetMealInfoListener.showLoading();
        NetSubscribe.getInstance().getSimByParam(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object object) {
                CodeData codeData = (CodeData) object;
                if (codeData.getCode() == 0 && null != codeData.getBody()) {
                    if (null != codeData.getBody()) {
                        SimDetailInfo.BodyBean bodyBean = new SimDetailInfo.BodyBean();
                        LinkedTreeMap codeDataBody = ((LinkedTreeMap) ((ArrayList) codeData.getBody()).get(0));
                        bodyBean.getSimPackageInfo(codeDataBody, bodyBean);
                        if (bodyBean.getGoodsName() != null) {
                            EventBus.getDefault().post(new EventBean(KeyConstants.SHOW_CURRENT_MEAL_WINDOW, bodyBean.getGoodsName()));
                        }
                        EventBus.getDefault().post(bodyBean);

                    }
                } else {
                    if (null != codeData.getMessage()) {
                        ToastUtil.show(App.getContext(), codeData.getMessage() + "");
                    }
                    EventBus.getDefault().post(new EventBean(KeyConstants.REQUEST_SIM_DETAIL_INFO_FAILURE));
                }
                onGetMealInfoListener.closeLoading();
            }

            @Override
            public void onFault(String errorMsg) {
                LoggerUtil.e(errorMsg);
                EventBus.getDefault().post(new EventBean(KeyConstants.REQUEST_SIM_DETAIL_INFO_FAILURE));
                onGetMealInfoListener.closeLoading();
            }
        }));
    }

    public void getSimPackage(OnGetMealInfoListener onGetMealInfoListener) {
        if (!NetDetectUtils.isNetWorkAvailable()) {
            return;
        }
        onGetMealInfoListener.showLoading();
        NetSubscribe.getInstance().getSimPackage(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object object) {
                bodyBeans.clear();
                CodeData codeData = (CodeData) object;
                if (codeData.getCode() == 0 && null != codeData.getBody()) {
                    ArrayList o1 = ((ArrayList) ((CodeData) object).getBody());
                    Flowable.just(o1).flatMap(new Function<ArrayList, Publisher<?>>() {
                        @Override
                        public Publisher<?> apply(ArrayList arrayList) throws Exception {
                            return Flowable.fromIterable(arrayList);
                        }
                    }).subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            LinkedTreeMap o2 = (LinkedTreeMap) o;
                            SimPackageBean.BodyBean bodyBean = new SimPackageBean.BodyBean();
                            bodyBeans.add(getSimPackageBean(o2, bodyBean));
                        }
                    });
                    EventBus.getDefault().post(bodyBeans);
                    if (null != bodyBeans && !bodyBeans.isEmpty()) {
                        //为内容
                        if (null != bodyBeans.get(0).getPackageActiveTime()) {
                            int days = SystemUtils.getDays(bodyBeans.get(0).getPackageEndTime(), bodyBeans.get(0).getPackageActiveTime());
                            int remainDays = SystemUtils.getRemainDays(bodyBeans.get(0).getPackageEndTime());
                            if (remainDays <= 5) {
                                EventBus.getDefault().post(new EventBean(KeyConstants.SHOW_MEAL_END_DAY_WINDOW));
                            }
                        }
                        double remainMB = bodyBeans.get(0).getPackageTraffic() - bodyBeans.get(0).getUseTraffic();
                        if (remainMB < 100 && (bodyBeans.get(0).getPackageTraffic() != -1) && (bodyBeans.get(0).getPackageTraffic() > 0)) {
                            EventBus.getDefault().post(new EventBean(KeyConstants.SHOW_MEAL_END_FLOW_WINDOW));
                        }
                        EventBus.getDefault().post(bodyBeans.get(0));
                    }
                } else {
                    if (null != codeData.getMessage()) {
                        ToastUtil.show(App.getContext(), codeData.getMessage() + "");
                    }
                    EventBus.getDefault().post(new EventBean(KeyConstants.REQUEST_SIM_PACKAGE_INFO_FAILURE));
                }
                onGetMealInfoListener.closeLoading();
            }

            @Override
            public void onFault(String errorMsg) {
                LoggerUtil.e(errorMsg);
                EventBus.getDefault().post(new EventBean(KeyConstants.REQUEST_SIM_PACKAGE_INFO_FAILURE));
                onGetMealInfoListener.closeLoading();
            }
        }));
    }

    public void getPackageCanBuy(OnGetMealInfoListener onGetMealInfoListener) {
        if (!NetDetectUtils.isNetWorkAvailable()) {
            return;
        }
        onGetMealInfoListener.showLoading();
        NetSubscribe.getInstance().getPackageCanBuy(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object object) {
                bodyBeans.clear();
                CodeData codeData = (CodeData) object;
                if (codeData.getCode() == 0 && null != codeData.getBody()) {

                } else {
                    if (null != codeData.getMessage()) {
                        ToastUtil.show(App.getContext(), codeData.getMessage() + "");
                    }
                    EventBus.getDefault().post(new EventBean(KeyConstants.REQUEST_SIM_PACKAGE_INFO_FAILURE));
                }
                onGetMealInfoListener.closeLoading();
            }

            @Override
            public void onFault(String errorMsg) {
                LoggerUtil.e(errorMsg);
                EventBus.getDefault().post(new EventBean(KeyConstants.REQUEST_SIM_PACKAGE_INFO_FAILURE));
                onGetMealInfoListener.closeLoading();
            }
        }));
    }

    public void RequestGetWeChatInfo(OnGetWeChatInfoListener onGetWeChatInfoListener) {
        NetSubscribe.getInstance().getWeChatInfo(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object codeData) {
                WeChatInfoBean chatInfoBeanCodeData = (WeChatInfoBean) codeData;
                if (chatInfoBeanCodeData.getCode() == 0 && null != chatInfoBeanCodeData.getBody()) {
                    onGetWeChatInfoListener.ResponseWeChatInfo(chatInfoBeanCodeData);
                } else {
                    if (null != chatInfoBeanCodeData.getMessage()) {
                        onGetWeChatInfoListener.showUiErrorInfo(chatInfoBeanCodeData.getMessage());
                    }
                }
            }

            @Override
            public void onFault(String errorMsg) {
                onGetWeChatInfoListener.showUiErrorInfo(errorMsg + "");
            }
        }));
    }

    public void RequestRealNameDiagnosis() {

        NetSubscribe.getInstance().realNameDiagnosis(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseRealNameDiagnosis(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestCardPackageDiagnosis() {

        NetSubscribe.getInstance().cardPackageDiagnosis(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseCardPackageDiagnosis(bodyBean);
                }

            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestSynchronizationCardStatus() {

        NetSubscribe.getInstance().synchronizationCardStatus(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseSynchronizationCardStatus(bodyBean);
                }

            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestUpdateVoiceData() {

        NetSubscribe.getInstance().updateVoiceData(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseUpdateVoiceData(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestUpdateTrafficData() {

        NetSubscribe.getInstance().updateTrafficData(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseUpdateTrafficData(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestFlowDetection() {

        NetSubscribe.getInstance().flowDetection(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseFlowDetection(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestSpeechDetection() {

        NetSubscribe.getInstance().speechDetection(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseSpeechDetection(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestWhiteListDiagnosis() {

        NetSubscribe.getInstance().whiteListDiagnosis(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseWhiteListDiagnosis(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void RequestReadCardStatus() {

        NetSubscribe.getInstance().readCardStatus(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object response) {
                IntelligentDiagnosisResponseResult bodyBean = (IntelligentDiagnosisResponseResult) response;
                if (null == bodyBean.getData()) {
                    intelligentDiagnosisListener.showUiErrorInfo("当前操作太频繁，请稍后再试！");
                } else {
                    intelligentDiagnosisListener.ResponseReadCardStatus(bodyBean);
                }
            }

            @Override
            public void onFault(String error) {
                if (error != null) {
                    intelligentDiagnosisListener.showUiErrorInfo(error);
                }

            }
        }));
    }

    public void registerDevice(String authorization) {

        NetSubscribe.getInstance().registerDevice(new DisposableObserver<OneNetCodeData>() {
            @Override
            public void onNext(OneNetCodeData oneNetCodeData) {
                if (oneNetCodeData.isSuccess()) {
                    RegisterDeviceBean registerDeviceBean = new Gson().fromJson(new Gson().toJson(oneNetCodeData.getData()), RegisterDeviceBean.class);
                    SpUtil.putObject(App.getContext(), KeyConstants.ONE_NET_DEVICE_INFO, registerDeviceBean);
                    LoggerUtil.e("注册成功：" + registerDeviceBean.toString());
                    EventBus.getDefault().post(new EventBean(KeyConstants.INIT_ONE_NET));
                } else {
                    LoggerUtil.e("注册失败：" + oneNetCodeData.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
                LoggerUtil.e("注册异常：" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }, authorization, CommonUtils.getIMEI(App.getContext()), "iot" + CommonUtils.getIMEI(App.getContext()));
    }
}
