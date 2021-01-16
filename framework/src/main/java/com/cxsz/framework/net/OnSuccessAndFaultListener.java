package com.cxsz.framework.net;


public interface OnSuccessAndFaultListener<T> {
    void onSuccess(T codeData);

    void onFault(String errorMsg);
}
