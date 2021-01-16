package com.cxsz.elu.main.model.event;

/**
 * Zhou keda
 * 2017/5/5
 */
public class ShowDialogEvent {

    /**
     * 事件类型, 1:短信 2:强制拉起首页的强提醒 3:不强制拉起首页的弱提醒
     */
    public static final int TYPE_SMS = 1;
    public static final int TYPE_STRONG_NOTIFY = 2;
    public static final int TYPE_WEAK_NOTIFY = 3;
    public static final int TYPE_STOP_USED_NOTIFY = 4;

    private String tittle;
    private String body;
    private int type;//短信通知
    private boolean isChange;//是否改变主题

    public ShowDialogEvent(String tittle, String body, int type, boolean isChange) {
        this.tittle = tittle;
        this.body = body;
        this.type = type;
        this.isChange = isChange;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }




    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }


}
