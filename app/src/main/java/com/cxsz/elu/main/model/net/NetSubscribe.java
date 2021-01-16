package com.cxsz.elu.main.model.net;

import com.cxsz.elu.main.App;
import com.cxsz.elu.main.component.CardSignUtil;
import com.cxsz.elu.main.component.SimCardUtils;
import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.net.CodeData;
import com.cxsz.framework.net.IntelligentDiagnosisResponseResult;
import com.cxsz.framework.net.NetRequestFactory;
import com.cxsz.framework.net.OneNetCodeData;
import com.cxsz.framework.net.WeChatInfoBean;
import com.cxsz.framework.tool.CommonUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 建议：把功能模块来分别存放不同的请求方法
 */

public class NetSubscribe {
    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final NetSubscribe INSTANCE = new NetSubscribe();
    }

    //获取单例
    public static NetSubscribe getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getSimByParam(DisposableObserver<CodeData> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("target", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<CodeData> observable = NetRequestFactory.getInstance().getHttpApi().getSimByParam(requestBody);
        NetRequestFactory.getInstance().toSubscribe(observable, subscriber);
    }

    public void getSimPackage(DisposableObserver<CodeData> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<CodeData> observable = NetRequestFactory.getInstance().getHttpApi().getSimPackage(requestBody);
        NetRequestFactory.getInstance().toSubscribe(observable, subscriber);
    }

    public void getPackageCanBuy(DisposableObserver<CodeData> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
//        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        map.put("iccid", "17231490280");
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<CodeData> observable = NetRequestFactory.getInstance().getHttpApi().getPackageCanBuy(requestBody);
        NetRequestFactory.getInstance().toSubscribe(observable, subscriber);
    }

    public void getWeChatInfo(DisposableObserver<WeChatInfoBean> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<WeChatInfoBean> observable = NetRequestFactory.getInstance().getHttpApi().getWechatInfo(requestBody);
        NetRequestFactory.getInstance().toSubscribe(observable, subscriber);
    }

    public void realNameDiagnosis(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().realNameDiagnosis(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void cardPackageDiagnosis(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().cardPackageDiagnosis(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void synchronizationCardStatus(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().synchronizationCardStatus(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void updateVoiceData(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().updateVoiceData(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void updateTrafficData(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().updateTrafficData(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void flowDetection(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().flowDetection(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void speechDetection(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().speechDetection(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void whiteListDiagnosis(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().whiteListDiagnosis(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }

    public void readCardStatus(DisposableObserver<IntelligentDiagnosisResponseResult> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", KeyConstants.APP_ID);
        map.put("timestamp", CommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", KeyConstants.NONCE_STR);
        map.put("iccid", SimCardUtils.getIccId(App.getContext()));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = CardSignUtil.sign(map, ignoreParamNames, KeyConstants.SECRETS);
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<IntelligentDiagnosisResponseResult> observable = NetRequestFactory.getInstance().getHttpApi().readCardStatus(body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }


    public void registerDevice(DisposableObserver<OneNetCodeData> consumer, String authorization, String device_name, String desc) {
        Map<String, String> map = new HashMap<>();
        map.put("product_id", KeyConstants.PRODUCT_ID);
        map.put("device_name", device_name);
        map.put("desc", desc);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Observable<OneNetCodeData> observable = NetRequestFactory.getInstance().getHttpApi().registerDevice(KeyConstants.REGISTER_URL,authorization, body);
        NetRequestFactory.getInstance().toSubscribe(observable, consumer);
    }
}
