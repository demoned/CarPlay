package com.cxsz.elu.main.view.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.elu.main.component.IntelligentDiagnosisListener;
import com.cxsz.elu.main.model.bean.SimDetailInfo;
import com.cxsz.elu.main.model.net.NetResponseService;
import com.cxsz.framework.base.BaseActivity;
import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.net.IntelligentDiagnosisResponseResult;
import com.cxsz.framework.tool.LoggerUtil;
import com.cxsz.framework.tool.ToastUtil;

/**
 * 智能诊断界面
 */
public class IntelligentDiagnosisActivity extends BaseActivity implements IntelligentDiagnosisListener {
    TextView cardNumber;
    TextView iccIdNumber;
    ImageView searchAnim;
    TextView status;
    TextView reDiagnosis;
    NestedScrollView diagnosis_scroll_area;
    TextView realNameAuthenticationNotice;
    ImageView realNameAuthentication;
    TextView diagnosisOfCardPackageNotice;
    ImageView diagnosisOfCardPackage;
    TextView activationStateNotice;
    ImageView activationState;
    TextView updateVoiceDataNotice;
    ImageView updateVoiceData;
    TextView speechUsageDetectionNotice;
    ImageView speechUsageDetection;
    TextView updateTrafficDataNotice;
    ImageView updateTrafficData;
    TextView flowUsageDetectionNotice;
    ImageView flowUsageDetection;
    TextView whiteListDiagnosisNotice;
    ImageView whiteListDiagnosis;
    TextView cardDiagnosisNotice;
    ImageView cardDiagnosis;
    View real_name_authentication_result_area;
    TextView real_name_authentication_result;
    View diagnosis_of_card_package_result_area;
    TextView diagnosis_of_card_package_result;
    View activation_state_result_area;
    TextView activation_state_result;
    View update_voice_data_result_area;
    TextView update_voice_data_result;
    View speech_usage_detection_result_area;
    TextView speech_usage_detection_result;
    View update_traffic_data_result_area;
    TextView update_traffic_data_result;
    View flow_usage_detection_result_area;
    TextView flow_usage_detection_result;
    View white_list_diagnosis_result_area;
    TextView white_list_diagnosis_result;
    View card_diagnosis_notice_result_area;
    TextView card_diagnosis_notice_result;
    View result_normal_area;
    View result_unusual_area;

    private SimDetailInfo.BodyBean bodyBean;

    //记录所有接口返回值
    private StringBuffer states = new StringBuffer();

    @Override
    protected void initViewShowStyle() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_intelligent_diagnosis;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        cardNumber = findViewById(R.id.card_number);
        iccIdNumber = findViewById(R.id.icc_id_number);
        searchAnim = findViewById(R.id.search_anim);
        status = findViewById(R.id.status);
        reDiagnosis = findViewById(R.id.re_diagnosis);
        reDiagnosis.setOnClickListener(this::onViewClick);
        diagnosis_scroll_area = findViewById(R.id.diagnosis_scroll_area);
        realNameAuthenticationNotice = findViewById(R.id.real_name_authentication_notice);
        realNameAuthentication = findViewById(R.id.real_name_authentication);
        diagnosisOfCardPackageNotice = findViewById(R.id.diagnosis_of_card_package_notice);
        diagnosisOfCardPackage = findViewById(R.id.diagnosis_of_card_package);
        activationStateNotice = findViewById(R.id.activation_state_notice);
        activationState = findViewById(R.id.activation_state);
        updateVoiceDataNotice = findViewById(R.id.update_voice_data_notice);
        updateVoiceData = findViewById(R.id.update_voice_data);
        speechUsageDetectionNotice = findViewById(R.id.speech_usage_detection_notice);
        speechUsageDetection = findViewById(R.id.speech_usage_detection);
        updateTrafficDataNotice = findViewById(R.id.update_traffic_data_notice);
        updateTrafficData = findViewById(R.id.update_traffic_data);
        flowUsageDetectionNotice = findViewById(R.id.flow_usage_detection_notice);
        flowUsageDetection = findViewById(R.id.flow_usage_detection);
        whiteListDiagnosisNotice = findViewById(R.id.white_list_diagnosis_notice);
        whiteListDiagnosis = findViewById(R.id.white_list_diagnosis);
        cardDiagnosisNotice = findViewById(R.id.card_diagnosis_notice);
        cardDiagnosis = findViewById(R.id.card_diagnosis);
        real_name_authentication_result_area = findViewById(R.id.real_name_authentication_result_area);
        real_name_authentication_result = findViewById(R.id.real_name_authentication_result);
        diagnosis_of_card_package_result_area = findViewById(R.id.diagnosis_of_card_package_result_area);
        diagnosis_of_card_package_result = findViewById(R.id.diagnosis_of_card_package_result);
        activation_state_result_area = findViewById(R.id.activation_state_result_area);
        activation_state_result = findViewById(R.id.activation_state_result);
        update_voice_data_result_area = findViewById(R.id.update_voice_data_result_area);
        update_voice_data_result = findViewById(R.id.update_voice_data_result);
        speech_usage_detection_result_area = findViewById(R.id.speech_usage_detection_result_area);
        speech_usage_detection_result = findViewById(R.id.speech_usage_detection_result);
        update_traffic_data_result_area = findViewById(R.id.update_traffic_data_result_area);
        update_traffic_data_result = findViewById(R.id.update_traffic_data_result);
        flow_usage_detection_result_area = findViewById(R.id.flow_usage_detection_result_area);
        flow_usage_detection_result = findViewById(R.id.flow_usage_detection_result);
        white_list_diagnosis_result_area = findViewById(R.id.white_list_diagnosis_result_area);
        white_list_diagnosis_result = findViewById(R.id.white_list_diagnosis_result);
        card_diagnosis_notice_result_area = findViewById(R.id.card_diagnosis_notice_result_area);
        card_diagnosis_notice_result = findViewById(R.id.card_diagnosis_notice_result);
        result_normal_area = findViewById(R.id.result_normal_area);
        result_unusual_area = findViewById(R.id.result_unusual_area);
        findViewById(R.id.back_to_main_view).setOnClickListener(this::onViewClick);
        NetResponseService.getInstance().setIntelligentDiagnosisListener(this);
        initData();
    }

    private void initData() {
        bodyBean = (SimDetailInfo.BodyBean) getIntent().getSerializableExtra(KeyConstants.MEAL_INFO_BEAN);
        if (null != bodyBean && null != cardNumber) {
            cardNumber.setText(bodyBean.getCardNumber());
        }
        if (null != iccIdNumber) {
            iccIdNumber.setText("ICCID : " + bodyBean.getIccid());
        }
        reDiagnosis.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        reDiagnosis.getPaint().setAntiAlias(true);
        diagnosisAnim();
        NetResponseService.getInstance().RequestRealNameDiagnosis();
    }


    public void onViewClick(View view) {
        if (view.getId() == R.id.re_diagnosis) {
            diagnosis();
        } else if (view.getId() == R.id.back_to_main_view) {
            finish();
        }
    }

    private void diagnosis() {
        result_normal_area.setVisibility(View.GONE);
        result_unusual_area.setVisibility(View.GONE);
        realNameAuthenticationNotice.setVisibility(View.VISIBLE);
        realNameAuthentication.setVisibility(View.GONE);

        diagnosisOfCardPackageNotice.setVisibility(View.VISIBLE);
        diagnosisOfCardPackage.setVisibility(View.GONE);

        activationStateNotice.setVisibility(View.VISIBLE);
        activationState.setVisibility(View.GONE);

        updateVoiceDataNotice.setVisibility(View.VISIBLE);
        updateVoiceData.setVisibility(View.GONE);

        speechUsageDetectionNotice.setVisibility(View.VISIBLE);
        speechUsageDetection.setVisibility(View.GONE);

        updateTrafficDataNotice.setVisibility(View.VISIBLE);
        updateTrafficData.setVisibility(View.GONE);

        flowUsageDetectionNotice.setVisibility(View.VISIBLE);
        flowUsageDetection.setVisibility(View.GONE);

        whiteListDiagnosisNotice.setVisibility(View.VISIBLE);
        whiteListDiagnosis.setVisibility(View.GONE);

        cardDiagnosisNotice.setVisibility(View.VISIBLE);
        cardDiagnosis.setVisibility(View.GONE);

        diagnosisAnim();
        NetResponseService.getInstance().RequestRealNameDiagnosis();
    }

    private void diagnosisAnim() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.search_anim);
        if (null != searchAnim) {
            searchAnim.startAnimation(animation);
        }
        if (null != status) {
            status.setText("智能诊断中…");
        }
        reDiagnosis.setVisibility(View.GONE);
    }

    private void finishDiagnosis(int errorLevel) {
        if (states.toString().contains("0") || states.toString().contains("-1")) {
            result_normal_area.setVisibility(View.GONE);
            result_unusual_area.setVisibility(View.VISIBLE);
        } else {
            result_normal_area.setVisibility(View.VISIBLE);
            result_unusual_area.setVisibility(View.GONE);
        }
        if (errorLevel == 0) {
            diagnosisOfCardPackageNotice.setVisibility(View.GONE);
            diagnosisOfCardPackage.setVisibility(View.VISIBLE);
            diagnosisOfCardPackage.setImageResource(R.mipmap.problem_icon);

            activationStateNotice.setVisibility(View.GONE);
            activationState.setVisibility(View.VISIBLE);
            activationState.setImageResource(R.mipmap.problem_icon);

            updateVoiceDataNotice.setVisibility(View.GONE);
            updateVoiceData.setVisibility(View.VISIBLE);
            updateVoiceData.setImageResource(R.mipmap.problem_icon);

            speechUsageDetectionNotice.setVisibility(View.GONE);
            speechUsageDetection.setVisibility(View.VISIBLE);
            speechUsageDetection.setImageResource(R.mipmap.problem_icon);

            updateTrafficDataNotice.setVisibility(View.GONE);
            updateTrafficData.setVisibility(View.VISIBLE);
            updateTrafficData.setImageResource(R.mipmap.problem_icon);

            flowUsageDetectionNotice.setVisibility(View.GONE);
            flowUsageDetection.setVisibility(View.VISIBLE);
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);

            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 1) {
            activationStateNotice.setVisibility(View.GONE);
            activationState.setVisibility(View.VISIBLE);
            activationState.setImageResource(R.mipmap.problem_icon);

            updateVoiceDataNotice.setVisibility(View.GONE);
            updateVoiceData.setVisibility(View.VISIBLE);
            updateVoiceData.setImageResource(R.mipmap.problem_icon);

            speechUsageDetectionNotice.setVisibility(View.GONE);
            speechUsageDetection.setVisibility(View.VISIBLE);
            speechUsageDetection.setImageResource(R.mipmap.problem_icon);

            updateTrafficDataNotice.setVisibility(View.GONE);
            updateTrafficData.setVisibility(View.VISIBLE);
            updateTrafficData.setImageResource(R.mipmap.problem_icon);

            flowUsageDetectionNotice.setVisibility(View.GONE);
            flowUsageDetection.setVisibility(View.VISIBLE);
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);

            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 2) {
            updateVoiceDataNotice.setVisibility(View.GONE);
            updateVoiceData.setVisibility(View.VISIBLE);
            updateVoiceData.setImageResource(R.mipmap.problem_icon);

            speechUsageDetectionNotice.setVisibility(View.GONE);
            speechUsageDetection.setVisibility(View.VISIBLE);
            speechUsageDetection.setImageResource(R.mipmap.problem_icon);

            updateTrafficDataNotice.setVisibility(View.GONE);
            updateTrafficData.setVisibility(View.VISIBLE);
            updateTrafficData.setImageResource(R.mipmap.problem_icon);

            flowUsageDetectionNotice.setVisibility(View.GONE);
            flowUsageDetection.setVisibility(View.VISIBLE);
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);

            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 3) {
            speechUsageDetectionNotice.setVisibility(View.GONE);
            speechUsageDetection.setVisibility(View.VISIBLE);
            speechUsageDetection.setImageResource(R.mipmap.problem_icon);

            updateTrafficDataNotice.setVisibility(View.GONE);
            updateTrafficData.setVisibility(View.VISIBLE);
            updateTrafficData.setImageResource(R.mipmap.problem_icon);

            flowUsageDetectionNotice.setVisibility(View.GONE);
            flowUsageDetection.setVisibility(View.VISIBLE);
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);

            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 4) {
            updateTrafficDataNotice.setVisibility(View.GONE);
            updateTrafficData.setVisibility(View.VISIBLE);
            updateTrafficData.setImageResource(R.mipmap.problem_icon);

            flowUsageDetectionNotice.setVisibility(View.GONE);
            flowUsageDetection.setVisibility(View.VISIBLE);
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);

            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 5) {
            flowUsageDetectionNotice.setVisibility(View.GONE);
            flowUsageDetection.setVisibility(View.VISIBLE);
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);

            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 6) {
            whiteListDiagnosisNotice.setVisibility(View.GONE);
            whiteListDiagnosis.setVisibility(View.VISIBLE);
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);

            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        } else if (errorLevel == 7) {
            cardDiagnosisNotice.setVisibility(View.GONE);
            cardDiagnosis.setVisibility(View.VISIBLE);
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        }
        refreshDiagnosis();
    }

    private void refreshDiagnosis() {
        if (null != searchAnim) {
            searchAnim.clearAnimation();
        }
        if (null != status) {
            status.setText("诊断完成！");
        }
        if (null != reDiagnosis) {
            reDiagnosis.setVisibility(View.VISIBLE);
        }
        diagnosis_scroll_area.scrollTo(0, NestedScrollView.FOCUS_DOWN);
    }

    @Override
    public <T> void ResponseRealNameDiagnosis(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == realNameAuthenticationNotice || null == realNameAuthentication
                || null == real_name_authentication_result_area || null == real_name_authentication_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        realNameAuthenticationNotice.setVisibility(View.GONE);
        realNameAuthentication.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            real_name_authentication_result_area.setVisibility(View.GONE);
            realNameAuthentication.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestCardPackageDiagnosis();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(0);
            real_name_authentication_result_area.setVisibility(View.VISIBLE);
            real_name_authentication_result.setText(codeData.getData().getInfo() + "");
            realNameAuthentication.setImageResource(R.mipmap.failure_icon);
        } else {
            real_name_authentication_result_area.setVisibility(View.VISIBLE);
            real_name_authentication_result.setText(codeData.getData().getInfo() + "");
            realNameAuthentication.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestCardPackageDiagnosis();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseCardPackageDiagnosis(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == diagnosisOfCardPackageNotice || null == diagnosisOfCardPackage
                || null == diagnosis_of_card_package_result_area || null == diagnosis_of_card_package_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        diagnosisOfCardPackageNotice.setVisibility(View.GONE);
        diagnosisOfCardPackage.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            diagnosis_of_card_package_result_area.setVisibility(View.GONE);
            diagnosisOfCardPackage.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestSynchronizationCardStatus();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(1);
            diagnosis_of_card_package_result_area.setVisibility(View.VISIBLE);
            diagnosis_of_card_package_result.setText(codeData.getData().getInfo() + "");
            diagnosisOfCardPackage.setImageResource(R.mipmap.failure_icon);
        } else {
            diagnosis_of_card_package_result_area.setVisibility(View.VISIBLE);
            diagnosis_of_card_package_result.setText(codeData.getData().getInfo() + "");
            diagnosisOfCardPackage.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestSynchronizationCardStatus();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseSynchronizationCardStatus(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == activationStateNotice || null == activationState
                || null == activation_state_result_area || null == activation_state_result || null == NetResponseService.getInstance()) {
            return;
        }
        activationStateNotice.setVisibility(View.GONE);
        activationState.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            activation_state_result_area.setVisibility(View.GONE);
            activationState.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestUpdateVoiceData();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(2);
            activation_state_result_area.setVisibility(View.VISIBLE);
            activation_state_result.setText(codeData.getData().getInfo() + "");
            activationState.setImageResource(R.mipmap.failure_icon);
        } else {
            activation_state_result_area.setVisibility(View.VISIBLE);
            activation_state_result.setText(codeData.getData().getInfo() + "");
            activationState.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestUpdateVoiceData();
        }
        states.append(codeData.getData().getResult());
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseUpdateVoiceData(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == updateVoiceDataNotice || null == updateVoiceData
                || null == update_voice_data_result_area || null == update_voice_data_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        updateVoiceDataNotice.setVisibility(View.GONE);
        updateVoiceData.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            update_voice_data_result_area.setVisibility(View.GONE);
            updateVoiceData.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestUpdateTrafficData();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(3);
            update_voice_data_result_area.setVisibility(View.VISIBLE);
            update_voice_data_result.setText(codeData.getData().getInfo() + "");
            updateVoiceData.setImageResource(R.mipmap.failure_icon);
        } else {
            update_voice_data_result_area.setVisibility(View.VISIBLE);
            update_voice_data_result.setText(codeData.getData().getInfo() + "");
            updateVoiceData.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestUpdateTrafficData();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseUpdateTrafficData(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == speechUsageDetectionNotice || null == speechUsageDetection
                || null == speech_usage_detection_result_area || null == speech_usage_detection_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        speechUsageDetectionNotice.setVisibility(View.GONE);
        speechUsageDetection.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            speech_usage_detection_result_area.setVisibility(View.GONE);
            speechUsageDetection.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestFlowDetection();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(4);
            speech_usage_detection_result_area.setVisibility(View.VISIBLE);
            speech_usage_detection_result.setText(codeData.getData().getInfo() + "");
            speechUsageDetection.setImageResource(R.mipmap.failure_icon);
        } else {
            speech_usage_detection_result_area.setVisibility(View.VISIBLE);
            speech_usage_detection_result.setText(codeData.getData().getInfo() + "");
            speechUsageDetection.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestFlowDetection();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseFlowDetection(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == updateTrafficDataNotice || null == updateTrafficData
                || null == update_traffic_data_result_area || null == update_traffic_data_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        updateTrafficDataNotice.setVisibility(View.GONE);
        updateTrafficData.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            update_traffic_data_result_area.setVisibility(View.GONE);
            updateTrafficData.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestSpeechDetection();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(5);
            update_traffic_data_result_area.setVisibility(View.VISIBLE);
            update_traffic_data_result.setText(codeData.getData().getInfo() + "");
            updateTrafficData.setImageResource(R.mipmap.failure_icon);
        } else {
            update_traffic_data_result_area.setVisibility(View.VISIBLE);
            update_traffic_data_result.setText(codeData.getData().getInfo() + "");
            updateTrafficData.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestSpeechDetection();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseSpeechDetection(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == flowUsageDetectionNotice || null == flowUsageDetection
                || null == flow_usage_detection_result_area || null == flow_usage_detection_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        flowUsageDetectionNotice.setVisibility(View.GONE);
        flowUsageDetection.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            flow_usage_detection_result_area.setVisibility(View.GONE);
            flowUsageDetection.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestWhiteListDiagnosis();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(6);
            flow_usage_detection_result_area.setVisibility(View.VISIBLE);
            flow_usage_detection_result.setText(codeData.getData().getInfo() + "");
            flowUsageDetection.setImageResource(R.mipmap.failure_icon);
        } else {
            flow_usage_detection_result_area.setVisibility(View.VISIBLE);
            flow_usage_detection_result.setText(codeData.getData().getInfo() + "");
            flowUsageDetection.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestWhiteListDiagnosis();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseWhiteListDiagnosis(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == whiteListDiagnosisNotice || null == whiteListDiagnosis
                || null == white_list_diagnosis_result_area || null == white_list_diagnosis_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        whiteListDiagnosisNotice.setVisibility(View.GONE);
        whiteListDiagnosis.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            white_list_diagnosis_result_area.setVisibility(View.GONE);
            whiteListDiagnosis.setImageResource(R.mipmap.success_icon);
            NetResponseService.getInstance().RequestReadCardStatus();
        } else if (codeData.getData().getResult() == 0) {
            finishDiagnosis(7);
            white_list_diagnosis_result_area.setVisibility(View.VISIBLE);
            white_list_diagnosis_result.setText(codeData.getData().getInfo() + "");
            whiteListDiagnosis.setImageResource(R.mipmap.failure_icon);
        } else {
            white_list_diagnosis_result_area.setVisibility(View.VISIBLE);
            white_list_diagnosis_result.setText(codeData.getData().getInfo() + "");
            whiteListDiagnosis.setImageResource(R.mipmap.problem_icon);
            NetResponseService.getInstance().RequestReadCardStatus();
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
    }

    @Override
    public <T> void ResponseReadCardStatus(T t) {
        IntelligentDiagnosisResponseResult codeData = (IntelligentDiagnosisResponseResult) t;
        if (null == states || null == cardDiagnosisNotice || null == cardDiagnosis
                || null == card_diagnosis_notice_result_area || null == card_diagnosis_notice_result || null == NetResponseService.getInstance()) {
            return;
        }
        states.append(codeData.getData().getResult());
        cardDiagnosisNotice.setVisibility(View.GONE);
        cardDiagnosis.setVisibility(View.VISIBLE);
        if (codeData.getData().getResult() == 1) {
            card_diagnosis_notice_result_area.setVisibility(View.GONE);
            cardDiagnosis.setImageResource(R.mipmap.success_icon);
        } else if (codeData.getData().getResult() == 0) {
            card_diagnosis_notice_result_area.setVisibility(View.VISIBLE);
            card_diagnosis_notice_result.setText(codeData.getData().getInfo() + "");
            cardDiagnosis.setImageResource(R.mipmap.failure_icon);
        } else {
            card_diagnosis_notice_result_area.setVisibility(View.VISIBLE);
            card_diagnosis_notice_result.setText(codeData.getData().getInfo() + "");
            cardDiagnosis.setImageResource(R.mipmap.problem_icon);
        }
        LoggerUtil.e("Diagnosis", codeData.toString());
        finishDiagnosis(8);
    }

    @Override
    public void showUiErrorInfo(String info) {
        refreshDiagnosis();
        ToastUtil.show(this, info);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
