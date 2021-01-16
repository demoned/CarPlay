package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/5/8.
 */

import java.util.List;

/**
 * Zhou keda
 * 2017/5/8
 */
public class HistoryEvent {

    /**
     * errcode : 0
     * errmsg :
     * retobj : {"mobile":"1064690393770","remark":"",
     * "infoItem":[{"type":2,"packageId":128,"activeDate":"2017-04-10","
     * endDate":"2018-04-09","stateText":"使用中","totalDays":364,
     * "packageName":"视频尊享：698元/年","usedDays":28,"amount":575,
     * "categoryName":"内容套餐","buyTime":"2017-04-10","remark":"",
     * "mbtotal":2,"mbuse":0,"state":1,"ActiveDate":"2017-04-10",
     * "EndDate":"2018-04-09","StateText":"使用中","State":1,
     * "PackageId":128,"PackageName":"视频尊享：698元/年",
     * "CategoryName":"内容套餐","Remark":"","MBTotal":2,
     * "MBUse":0,"TotalDays":364,"UsedDays":28,"BuyTime":"2017-04-10",
     * "Amount":575,"isExperience":false},{"type":2,"packageId":119,
     * "activeDate":"2016-09-28","endDate":"2017-04-10","stateText":"已过期",
     * "totalDays":194,"packageName":"音乐畅听：298元/年","usedDays":194,
     * "amount":298,"categoryName":"内容套餐","buyTime":"2016-12-02","remark":"",
     * "mbtotal":1,"mbuse":3079,"state":3,"ActiveDate":"2016-09-28","EndDate":"2017-04-10",
     * "StateText":"已过期","State":3,"PackageId":119,"PackageName":"音乐畅听：298元/年",
     * "CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":3079,"TotalDays":194,
     * "UsedDays":194,"BuyTime":"2016-12-02","Amount":298,"isExperience":false},
     * {"type":2,"packageId":119,"activeDate":"2018-04-09","endDate":"2019-04-08",
     * "stateText":"待使用","totalDays":364,"packageName":"音乐畅听：298元/年","usedDays":0,
     * "amount":298,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":1,
     * "mbuse":0,"state":0,"ActiveDate":"2018-04-09","EndDate":"2019-04-08","StateText":"待使用",
     * "State":0,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐",
     * "Remark":"","MBTotal":1,"MBUse":0,"TotalDays":364,"UsedDays":0,"BuyTime":"2017-04-10",
     * "Amount":298,"isExperience":false},{"type":1,"packageId":95,"activeDate":"2016-04-09",
     * "endDate":"2016-09-15","stateText":"已用完","totalDays":159,"packageName":"2G/年套餐",
     * "usedDays":159,"amount":90,"categoryName":"流量套餐","buyTime":"2016-12-02","remark":"",
     * "mbtotal":2000,"mbuse":2029,"state":2,"ActiveDate":"2016-04-09","EndDate":"2016-09-15",
     * "StateText":"已用完","State":2,"PackageId":95,"PackageName":"2G/年套餐","CategoryName":"流量套餐",
     * "Remark":"","MBTotal":2000,"MBUse":2029,"TotalDays":159,"UsedDays":159,
     * "BuyTime":"2016-12-02","Amount":90,"isExperience":false}],"useDefault":1,
     * "defaultTip":"本机赠送<font color='red'><b>0M<\/b><\/font>流量，
     * <font color='red'><b>2016-11-01<\/b><\/font>号到期，
     * 请在到期前或流量用完前购买套餐","logoUrl":"",
     * "serviceNumber":"4001050488","simActiveDate":"2016-04-08",
     * "simStateText":"激活","url":"http://weixin.qq.com/r/WEz54dHErYzSrZWC9xlK","type":2,
     * "name":"一路沃","Name":"一路沃","Url":"http://weixin.qq.com/r/WEz54dHErYzSrZWC9xlK","Remark":"",
     * "LogoUrl":"","SimStateText":"激活","Mobile":"1064690393770","Type":2,"SimActiveDate":"2016-04-08",
     * "ServiceNumber":"4001050488","UseDefault":1,"DefaultTip":"本机赠送<font color='red'><b>0M<\/b><\/font>流量，
     * <font color='red'><b>2016-11-01<\/b><\/font>号到期，请在到期前或流量用完前购买套餐",
     * "list":[{"type":2,"packageId":128,"activeDate":"2017-04-10","endDate":"2018-04-09","stateText":"使用中","totalDays":364,"packageName":"视频尊享：698元/年","usedDays":28,"amount":575,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":2,"mbuse":0,"state":1,"ActiveDate":"2017-04-10","EndDate":"2018-04-09","StateText":"使用中","State":1,"PackageId":128,"PackageName":"视频尊享：698元/年","CategoryName":"内容套餐","Remark":"","MBTotal":2,"MBUse":0,"TotalDays":364,"UsedDays":28,"BuyTime":"2017-04-10","Amount":575,"isExperience":false},{"type":2,"packageId":119,"activeDate":"2016-09-28","endDate":"2017-04-10","stateText":"已过期","totalDays":194,"packageName":"音乐畅听：298元/年","usedDays":194,"amount":298,"categoryName":"内容套餐","buyTime":"2016-12-02","remark":"","mbtotal":1,"mbuse":3079,"state":3,"ActiveDate":"2016-09-28","EndDate":"2017-04-10","StateText":"已过期","State":3,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":3079,"TotalDays":194,"UsedDays":194,"BuyTime":"2016-12-02","Amount":298,"isExperience":false},{"type":2,"packageId":119,"activeDate":"2018-04-09","endDate":"2019-04-08","stateText":"待使用","totalDays":364,"packageName":"音乐畅听：298元/年","usedDays":0,"amount":298,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":1,"mbuse":0,"state":0,"ActiveDate":"2018-04-09","EndDate":"2019-04-08","StateText":"待使用","State":0,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":0,"TotalDays":364,"UsedDays":0,"BuyTime":"2017-04-10","Amount":298,"isExperience":false},{"type":1,"packageId":95,"activeDate":"2016-04-09","endDate":"2016-09-15","stateText":"已用完","totalDays":159,"packageName":"2G/年套餐","usedDays":159,"amount":90,"categoryName":"流量套餐","buyTime":"2016-12-02","remark":"","mbtotal":2000,"mbuse":2029,"state":2,"ActiveDate":"2016-04-09","EndDate":"2016-09-15","StateText":"已用完","State":2,"PackageId":95,"PackageName":"2G/年套餐","CategoryName":"流量套餐","Remark":"","MBTotal":2000,"MBUse":2029,"TotalDays":159,"UsedDays":159,"BuyTime":"2016-12-02","Amount":90,"isExperience":false}]}
     */

    private int errcode;
    private String errmsg;
    private RetobjBean retobj;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public RetobjBean getRetobj() {
        return retobj;
    }

    public void setRetobj(RetobjBean retobj) {
        this.retobj = retobj;
    }

    public static class RetobjBean {
        /**
         * mobile : 1064690393770
         * remark :
         * infoItem : [{"type":2,"packageId":128,"activeDate":"2017-04-10","endDate":"2018-04-09","stateText":"使用中","totalDays":364,"packageName":"视频尊享：698元/年","usedDays":28,"amount":575,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":2,"mbuse":0,"state":1,"ActiveDate":"2017-04-10","EndDate":"2018-04-09","StateText":"使用中","State":1,"PackageId":128,"PackageName":"视频尊享：698元/年","CategoryName":"内容套餐","Remark":"","MBTotal":2,"MBUse":0,"TotalDays":364,"UsedDays":28,"BuyTime":"2017-04-10","Amount":575,"isExperience":false},{"type":2,"packageId":119,"activeDate":"2016-09-28","endDate":"2017-04-10","stateText":"已过期","totalDays":194,"packageName":"音乐畅听：298元/年","usedDays":194,"amount":298,"categoryName":"内容套餐","buyTime":"2016-12-02","remark":"","mbtotal":1,"mbuse":3079,"state":3,"ActiveDate":"2016-09-28","EndDate":"2017-04-10","StateText":"已过期","State":3,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":3079,"TotalDays":194,"UsedDays":194,"BuyTime":"2016-12-02","Amount":298,"isExperience":false},{"type":2,"packageId":119,"activeDate":"2018-04-09","endDate":"2019-04-08","stateText":"待使用","totalDays":364,"packageName":"音乐畅听：298元/年","usedDays":0,"amount":298,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":1,"mbuse":0,"state":0,"ActiveDate":"2018-04-09","EndDate":"2019-04-08","StateText":"待使用","State":0,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":0,"TotalDays":364,"UsedDays":0,"BuyTime":"2017-04-10","Amount":298,"isExperience":false},{"type":1,"packageId":95,"activeDate":"2016-04-09","endDate":"2016-09-15","stateText":"已用完","totalDays":159,"packageName":"2G/年套餐","usedDays":159,"amount":90,"categoryName":"流量套餐","buyTime":"2016-12-02","remark":"","mbtotal":2000,"mbuse":2029,"state":2,"ActiveDate":"2016-04-09","EndDate":"2016-09-15","StateText":"已用完","State":2,"PackageId":95,"PackageName":"2G/年套餐","CategoryName":"流量套餐","Remark":"","MBTotal":2000,"MBUse":2029,"TotalDays":159,"UsedDays":159,"BuyTime":"2016-12-02","Amount":90,"isExperience":false}]
         * useDefault : 1
         * defaultTip : 本机赠送<font color='red'><b>0M</b></font>流量，<font color='red'><b>2016-11-01</b></font>号到期，请在到期前或流量用完前购买套餐
         * logoUrl :
         * serviceNumber : 4001050488
         * simActiveDate : 2016-04-08
         * simStateText : 激活
         * url : http://weixin.qq.com/r/WEz54dHErYzSrZWC9xlK
         * type : 2
         * name : 一路沃
         * Name : 一路沃
         * Url : http://weixin.qq.com/r/WEz54dHErYzSrZWC9xlK
         * Remark :
         * LogoUrl :
         * SimStateText : 激活
         * Mobile : 1064690393770
         * Type : 2
         * SimActiveDate : 2016-04-08
         * ServiceNumber : 4001050488
         * UseDefault : 1
         * DefaultTip : 本机赠送<font color='red'><b>0M</b></font>流量，<font color='red'><b>2016-11-01</b></font>号到期，请在到期前或流量用完前购买套餐
         * list : [{"type":2,"packageId":128,"activeDate":"2017-04-10","endDate":"2018-04-09","stateText":"使用中","totalDays":364,"packageName":"视频尊享：698元/年","usedDays":28,"amount":575,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":2,"mbuse":0,"state":1,"ActiveDate":"2017-04-10","EndDate":"2018-04-09","StateText":"使用中","State":1,"PackageId":128,"PackageName":"视频尊享：698元/年","CategoryName":"内容套餐","Remark":"","MBTotal":2,"MBUse":0,"TotalDays":364,"UsedDays":28,"BuyTime":"2017-04-10","Amount":575,"isExperience":false},{"type":2,"packageId":119,"activeDate":"2016-09-28","endDate":"2017-04-10","stateText":"已过期","totalDays":194,"packageName":"音乐畅听：298元/年","usedDays":194,"amount":298,"categoryName":"内容套餐","buyTime":"2016-12-02","remark":"","mbtotal":1,"mbuse":3079,"state":3,"ActiveDate":"2016-09-28","EndDate":"2017-04-10","StateText":"已过期","State":3,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":3079,"TotalDays":194,"UsedDays":194,"BuyTime":"2016-12-02","Amount":298,"isExperience":false},{"type":2,"packageId":119,"activeDate":"2018-04-09","endDate":"2019-04-08","stateText":"待使用","totalDays":364,"packageName":"音乐畅听：298元/年","usedDays":0,"amount":298,"categoryName":"内容套餐","buyTime":"2017-04-10","remark":"","mbtotal":1,"mbuse":0,"state":0,"ActiveDate":"2018-04-09","EndDate":"2019-04-08","StateText":"待使用","State":0,"PackageId":119,"PackageName":"音乐畅听：298元/年","CategoryName":"内容套餐","Remark":"","MBTotal":1,"MBUse":0,"TotalDays":364,"UsedDays":0,"BuyTime":"2017-04-10","Amount":298,"isExperience":false},{"type":1,"packageId":95,"activeDate":"2016-04-09","endDate":"2016-09-15","stateText":"已用完","totalDays":159,"packageName":"2G/年套餐","usedDays":159,"amount":90,"categoryName":"流量套餐","buyTime":"2016-12-02","remark":"","mbtotal":2000,"mbuse":2029,"state":2,"ActiveDate":"2016-04-09","EndDate":"2016-09-15","StateText":"已用完","State":2,"PackageId":95,"PackageName":"2G/年套餐","CategoryName":"流量套餐","Remark":"","MBTotal":2000,"MBUse":2029,"TotalDays":159,"UsedDays":159,"BuyTime":"2016-12-02","Amount":90,"isExperience":false}]
         */

        private String mobile;
        private String remark;
        private int useDefault;
        private String defaultTip;
        private String logoUrl;
        private String serviceNumber;
        private String simActiveDate;
        private String simStateText;
        private String url;
        private int type;
        private String name;
        private String Name;
        private String Url;
        private String Remark;
        private String LogoUrl;
        private String SimStateText;
        private String Mobile;
        private int Type;
        private String SimActiveDate;
        private String ServiceNumber;
        private int UseDefault;
        private String DefaultTip;
        private List<InfoItemBean> infoItem;
        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getSimActiveDate() {
            return SimActiveDate;
        }

        public void setSimActiveDate(String SimActiveDate) {
            this.SimActiveDate = SimActiveDate;
        }

        public String getServiceNumber() {
            return ServiceNumber;
        }

        public void setServiceNumber(String ServiceNumber) {
            this.ServiceNumber = ServiceNumber;
        }

        public int getUseDefault() {
            return UseDefault;
        }

        public void setUseDefault(int UseDefault) {
            this.UseDefault = UseDefault;
        }

        public String getDefaultTip() {
            return DefaultTip;
        }

        public void setDefaultTip(String DefaultTip) {
            this.DefaultTip = DefaultTip;
        }

        public List<InfoItemBean> getInfoItem() {
            return infoItem;
        }

        public void setInfoItem(List<InfoItemBean> infoItem) {
            this.infoItem = infoItem;
        }
        public static class InfoItemBean {
            /**
             * type : 2
             * packageId : 128
             * activeDate : 2017-04-10
             * endDate : 2018-04-09
             * stateText : 使用中
             * totalDays : 364
             * packageName : 视频尊享：698元/年
             * usedDays : 28
             * amount : 575.0
             * categoryName : 内容套餐
             * buyTime : 2017-04-10
             * remark :
             * mbtotal : 2.0
             * mbuse : 0.0
             * state : 1
             * ActiveDate : 2017-04-10
             * EndDate : 2018-04-09
             * StateText : 使用中
             * State : 1
             * PackageId : 128
             * PackageName : 视频尊享：698元/年
             * CategoryName : 内容套餐
             * Remark :
             * MBTotal : 2.0
             * MBUse : 0.0
             * TotalDays : 364
             * UsedDays : 28
             * BuyTime : 2017-04-10
             * Amount : 575.0
             * isExperience : false
             */

            private int type;
            private int packageId;
            private String activeDate;
            private String endDate;
            private String stateText;
            private int totalDays;
            private String packageName;
            private int usedDays;
            private double amount;
            private String categoryName;
            private String buyTime;
            private String remark;
            private double mbtotal;
            private double mbuse;
            private int state;
            private boolean isExperience;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPackageId() {
                return packageId;
            }

            public void setPackageId(int packageId) {
                this.packageId = packageId;
            }

            public String getActiveDate() {
                return activeDate;
            }

            public void setActiveDate(String activeDate) {
                this.activeDate = activeDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getStateText() {
                return stateText;
            }

            public void setStateText(String stateText) {
                this.stateText = stateText;
            }

            public int getTotalDays() {
                return totalDays;
            }

            public void setTotalDays(int totalDays) {
                this.totalDays = totalDays;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public int getUsedDays() {
                return usedDays;
            }

            public void setUsedDays(int usedDays) {
                this.usedDays = usedDays;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getBuyTime() {
                return buyTime;
            }

            public void setBuyTime(String buyTime) {
                this.buyTime = buyTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public double getMbtotal() {
                return mbtotal;
            }

            public void setMbtotal(double mbtotal) {
                this.mbtotal = mbtotal;
            }

            public double getMbuse() {
                return mbuse;
            }

            public void setMbuse(double mbuse) {
                this.mbuse = mbuse;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public boolean isExperience() {
                return isExperience;
            }

            public void setExperience(boolean experience) {
                isExperience = experience;
            }
        }
    }
}
