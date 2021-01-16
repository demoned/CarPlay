package com.cxsz.elu.main.component;


public interface IntelligentDiagnosisListener {

    void showUiErrorInfo(String info);

    <T> void ResponseRealNameDiagnosis(T t);

    <T> void ResponseCardPackageDiagnosis(T t);

    <T> void ResponseSynchronizationCardStatus(T t);

    <T> void ResponseUpdateVoiceData(T t);

    <T> void ResponseUpdateTrafficData(T t);

    <T> void ResponseFlowDetection(T t);

    <T> void ResponseSpeechDetection(T t);

    <T> void ResponseWhiteListDiagnosis(T t);

    <T> void ResponseReadCardStatus(T t);
}
