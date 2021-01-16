package com.cxsz.elu.main.component;


import com.cxsz.framework.net.WeChatInfoBean;

public interface OnGetWeChatInfoListener {

    void showUiErrorInfo(String info);

    void ResponseWeChatInfo(WeChatInfoBean weChatInfoBean);
}
