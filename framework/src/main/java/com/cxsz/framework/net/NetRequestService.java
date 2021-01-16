package com.cxsz.framework.net;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by yangqian on 2017/7/26.
 */

public interface NetRequestService {
    //卡获取卡套餐信息
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/getCardInfo.json")
    Observable<CodeData> getSimByParam(@Body RequestBody cardInfo);

    //卡查询当前卡正使用以及未启用套餐接口
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/getCardPackage.json")
    Observable<CodeData> getSimPackage(@Body RequestBody cardInfo);

    //查询所有人相关信息
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/getWechatInfo.json")
    Observable<WeChatInfoBean> getWechatInfo(@Body RequestBody requestBody);

    //查询当前卡号可购买套餐
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/getPackageCanBuy.json")
    Observable<CodeData> getPackageCanBuy(@Body RequestBody requestBody);

    //实名状态认证
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/realNameDiagnosis.json")
    Observable<IntelligentDiagnosisResponseResult> realNameDiagnosis(@Body RequestBody body);

    //卡套餐诊断
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/cardPackageDiagnosis.json")
    Observable<IntelligentDiagnosisResponseResult> cardPackageDiagnosis(@Body RequestBody body);

    //同步卡状态
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/cardTateSynchronization.json")
    Observable<IntelligentDiagnosisResponseResult> synchronizationCardStatus(@Body RequestBody body);

    //更新语音数据
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/trafficUpdate.json")
    Observable<IntelligentDiagnosisResponseResult> updateVoiceData(@Body RequestBody body);

    //更新流量数据
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/trafficUpdate.json")
    Observable<IntelligentDiagnosisResponseResult> updateTrafficData(@Body RequestBody body);

    //流量检测
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/detectionTraffic.json")
    Observable<IntelligentDiagnosisResponseResult> flowDetection(@Body RequestBody body);

    //语音检测
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/voiceDetection.json")
    Observable<IntelligentDiagnosisResponseResult> speechDetection(@Body RequestBody body);

    //白名单诊断
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/whiteListDiagnosis.json")
    Observable<IntelligentDiagnosisResponseResult> whiteListDiagnosis(@Body RequestBody body);

    //读取卡状态诊断
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("smartiot/api/v1/diagnosticCardStatus.json")
    Observable<IntelligentDiagnosisResponseResult> readCardStatus(@Body RequestBody body);

    /**
     * 注册设备
     *
     * @param url
     * @param body
     * @return
     */
    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST()
    Observable<OneNetCodeData> registerDevice(@Url String url, @Header("Authorization") String authorization, @Body RequestBody body);

}
