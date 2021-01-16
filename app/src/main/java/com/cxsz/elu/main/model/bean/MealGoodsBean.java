package com.cxsz.elu.main.model.bean;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.util.List;

public class MealGoodsBean implements Serializable {


    private int code;
    private String message;
    private List<MealGoodsBodyBean> body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MealGoodsBodyBean> getBody() {
        return body;
    }

    public void setBody(List<MealGoodsBodyBean> body) {
        this.body = body;
    }

    public static class MealGoodsBodyBean implements Serializable {
        private double callDuration;
        private String goodsId;
        private String orgId;
        private String iccid;
        private double packageTraffic;
        private double officialPrice;
        private String simId;
        private String customerId;
        private String goodsName;
        private double unitPrice;
        private String goodsDescribe;
        private double validityDuration;
        private String packageEndTime;
        private String shortName;
        private String cardNumber;
        private String goodsType;
        private String customType;
        private String goodsDesc;//多赠送的
        private String groupName;//套餐标识:比如,推荐套餐等等 用来筛选列表

        public double getCallDuration() {
            return callDuration;
        }

        public void setCallDuration(double callDuration) {
            this.callDuration = callDuration;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }

        public double getPackageTraffic() {
            return packageTraffic;
        }

        public void setPackageTraffic(double packageTraffic) {
            this.packageTraffic = packageTraffic;
        }

        public double getOfficialPrice() {
            return officialPrice;
        }

        public void setOfficialPrice(double officialPrice) {
            this.officialPrice = officialPrice;
        }

        public String getSimId() {
            return simId;
        }

        public void setSimId(String simId) {
            this.simId = simId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getGoodsDescribe() {
            return goodsDescribe;
        }

        public void setGoodsDescribe(String goodsDescribe) {
            this.goodsDescribe = goodsDescribe;
        }

        public double getValidityDuration() {
            return validityDuration;
        }

        public void setValidityDuration(double validityDuration) {
            this.validityDuration = validityDuration;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getCustomType() {
            return customType;
        }

        public void setCustomType(String customType) {
            this.customType = customType;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getPackageEndTime() {
            return packageEndTime;
        }

        public void setPackageEndTime(String packageEndTime) {
            this.packageEndTime = packageEndTime;
        }

        public void getMealInfo(LinkedTreeMap mapData, MealGoodsBodyBean bodyBean) {
            if (mapData.get("callDuration") != null) {
                double callDuration = ((Double) mapData.get("callDuration")).doubleValue();
                bodyBean.setCallDuration(callDuration);
            }
            String goodsId = (String) mapData.get("goodsId");
            bodyBean.setGoodsId(goodsId);
            String orgId = (String) mapData.get("orgId");
            bodyBean.setOrgId(orgId);
            String iccid = (String) mapData.get("iccid");
            bodyBean.setIccid(iccid);
            if (mapData.get("packageTraffic") != null) {
                double packageTraffic = ((Double) mapData.get("packageTraffic")).doubleValue();
                bodyBean.setPackageTraffic(packageTraffic);
            }
            if (mapData.get("officialPrice") != null) {
                double officialPrice = ((Double) mapData.get("officialPrice")).doubleValue();
                bodyBean.setOfficialPrice(officialPrice);
            }
            String simId = (String) mapData.get("simId");
            bodyBean.setSimId(simId);
            String customerId = (String) mapData.get("customerId");
            bodyBean.setCustomerId(customerId);
            String goodsName = (String) mapData.get("goodsName");
            bodyBean.setGoodsName(goodsName);
            if (mapData.get("unitPrice") != null) {
                double unitPrice = ((Double) mapData.get("unitPrice")).doubleValue();
                bodyBean.setUnitPrice(unitPrice);
            }
            String goodsDescribe = (String) mapData.get("goodsDescribe");
            bodyBean.setGoodsDescribe(goodsDescribe);
            if (mapData.get("validityDuration") != null) {
                double validityDuration = ((Double) mapData.get("validityDuration")).doubleValue();
                bodyBean.setValidityDuration(validityDuration);
            }
            String packageEndTime = (String) mapData.get("packageEndTime");
            bodyBean.setPackageEndTime(packageEndTime);
            String shortName = (String) mapData.get("shortName");
            bodyBean.setShortName(shortName);
            String cardNumber = (String) mapData.get("cardNumber");
            bodyBean.setCardNumber(cardNumber);
            String goodsType = (String) mapData.get("goodsType");
            bodyBean.setGoodsType(goodsType);
            String customType = (String) mapData.get("customType");
            bodyBean.setCustomType(customType);
            String goodsDesc = (String) mapData.get("goodsDesc");
            bodyBean.setGoodsDesc(goodsDesc);
            String groupName = (String) mapData.get("groupName");
            bodyBean.setGroupName(groupName);
        }
    }
}
