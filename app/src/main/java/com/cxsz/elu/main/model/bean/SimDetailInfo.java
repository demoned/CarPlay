package com.cxsz.elu.main.model.bean;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;

public class SimDetailInfo {

    /**
     * body : {"networkStandard":"4G","supplierId":"8a3255ed36f34ace96a9b51b4cb63285","hasAccount":0,"activationMode":"3","goodsId":"119","imsi":"460061040007740","supplierCode":"0000028","settlementMode":"12","orgId":"fb92fe0ca7974337ac183a30d12ccdcd","carrierOperatorName":"联通","iccid":"8986061800002057740N","realActivationDate":"2018-10-22 13:42:09","balance":0,"upstreamId":"Union","simId":"cb29f0af769b46acbc52ed9cba87b879","customerId":"17","goodsName":"音乐畅听：298元/年","productType":"2","simStateName":"正使用","ifRenew":1,"carrierOperator":"2","ifExamine":0,"realnameUrl":"https://rnr.10646.cn/portal/fast/auth","iccidDisplay":"89860618000020577404","simState":"2","goodsType":"1","realname":1,"cashback":0,"cancelPackage":0,"imeiNum":11,"customType":"1","whiteListSwitch":1,"packageEndTime":"2020-12-10 00:17:43","simActivationDate":"2018-10-22 13:42:09","imei":"8654220336686000","cardNumber":"1064601103583","noBusiness":"0","arrearage":"0","needActivated":"0","packageCount":"2","currentSimPackage":3,"simFlow":{"useCallDuration":0,"useMessageHistory":0,"year":2019,"useCallDurationHistory":0,"useMessage":0,"useTraffic":2119.63,"updateTime":"2018-10-22 13:43:58","packageMessage":0,"iccid":"8986061800002057740N","packageTraffic":-1,"packageCallDuration":0,"month":4,"createTime":"2018-10-22 13:43:58","useTrafficHistory":725.17,"createUser":"d798969b9b194354a11ef5f958de74ef","flowId":"2212046989394825b2ad80c7472abb79","cardNumber":"1064601103583","customerId":"e462ec874dcf419fb620af93f2113c0d","goodsId":"119","orgId":"e462ec874dcf419fb620af93f2113c0d"}}
     * status : 0
     */

    private BodyBean body;
    private int status;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class BodyBean implements Serializable {
        /**
         * networkStandard : 4G
         * supplierId : 8a3255ed36f34ace96a9b51b4cb63285
         * hasAccount : 0
         * activationMode : 3
         * goodsId : 119
         * imsi : 460061040007740
         * supplierCode : 0000028
         * settlementMode : 12
         * orgId : fb92fe0ca7974337ac183a30d12ccdcd
         * carrierOperatorName : 联通
         * iccid : 8986061800002057740N
         * realActivationDate : 2018-10-22 13:42:09
         * balance : 0
         * upstreamId : Union
         * simId : cb29f0af769b46acbc52ed9cba87b879
         * customerId : 17
         * goodsName : 音乐畅听：298元/年
         * productType : 2
         * simStateName : 正使用
         * ifRenew : 1
         * carrierOperator : 2
         * ifExamine : 0
         * realnameUrl : https://rnr.10646.cn/portal/fast/auth
         * iccidDisplay : 89860618000020577404
         * simState : 2
         * goodsType : 1
         * realname : 1
         * cashback : 0
         * cancelPackage : 0
         * imeiNum : 11
         * customType : 1
         * whiteListSwitch : 1
         * packageEndTime : 2020-12-10 00:17:43
         * simActivationDate : 2018-10-22 13:42:09
         * imei : 8654220336686000
         * cardNumber : 1064601103583
         * noBusiness : 0
         * arrearage : 0
         * needActivated : 0
         * packageCount : 2
         * currentSimPackage : 3
         * simFlow : {"useCallDuration":0,"useMessageHistory":0,"year":2019,"useCallDurationHistory":0,"useMessage":0,"useTraffic":2119.63,"updateTime":"2018-10-22 13:43:58","packageMessage":0,"iccid":"8986061800002057740N","packageTraffic":-1,"packageCallDuration":0,"month":4,"createTime":"2018-10-22 13:43:58","useTrafficHistory":725.17,"createUser":"d798969b9b194354a11ef5f958de74ef","flowId":"2212046989394825b2ad80c7472abb79","cardNumber":"1064601103583","customerId":"e462ec874dcf419fb620af93f2113c0d","goodsId":"119","orgId":"e462ec874dcf419fb620af93f2113c0d"}
         */

        private int cashback;
        private int realname;
        private int cancelPackage;
        private int imeiNum;
        private int hasAccount;
        private int balance;
        private int ifRenew;
        private int ifExamine;
        private String networkState;
        private int currentSimPackage;
        private int smsCount;

        private String networkStandard;
        private String supplierId;
        private String activationMode;
        private String goodsId;
        private String imsi;
        private String supplierCode;
        private String settlementMode;
        private String orgId;
        private String carrierOperatorName;
        private String iccid;
        private String upstreamId;
        private String simId;
        private String customerId;
        private String goodsName;
        private String productType;
        private String carrierOperator;
        private String productId;
        private String realnameUrl;
        private String simState;
        private String apnType;
        private String goodsType;
        private String customType;
        private String voiceState;
        private String packageEndTime;
        private String simActivationDate;
        private String imei;
        private String cardNumber;
        private String noBusiness;
        private String arrearage;
        private String needActivated;
        private String packageCount;

        private String statusStr;
        private SimFlowBean simFlow;

        public void getSimPackageInfo(LinkedTreeMap mapData, BodyBean bodyBean) {
            if (((Double) mapData.get("cashback")) != null) {
                double cashback = ((Double) mapData.get("cashback")).doubleValue();
                bodyBean.setCashback((int) cashback);
            }
            if (((Double) mapData.get("smsCount")) != null) {
                double smsCount = ((Double) mapData.get("smsCount")).doubleValue();
                bodyBean.setSmsCount((int) smsCount);
            }
            if (((Double) mapData.get("realname")) != null) {
                double realname = ((Double) mapData.get("realname")).doubleValue();
                bodyBean.setRealname((int) realname);
            }
            if (((Double) mapData.get("cancelPackage")) != null) {
                double cancelPackage = ((Double) mapData.get("cancelPackage")).doubleValue();
                bodyBean.setCancelPackage((int) cancelPackage);
            }
            if (((Double) mapData.get("imeiNum")) != null) {
                double imeiNum = ((Double) mapData.get("imeiNum")).doubleValue();
                bodyBean.setImeiNum((int) imeiNum);
            }
            if (((Double) mapData.get("hasAccount")) != null) {
                double hasAccount = ((Double) mapData.get("hasAccount")).doubleValue();
                bodyBean.setHasAccount((int) hasAccount);
            }
            if (((Double) mapData.get("balance")) != null) {
                double balance = ((Double) mapData.get("balance")).doubleValue();
                bodyBean.setBalance((int) balance);
            }
            if (((Double) mapData.get("ifRenew")) != null) {
                double ifRenew = ((Double) mapData.get("ifRenew")).doubleValue();
                bodyBean.setIfRenew((int) ifRenew);
            }
            if (((Double) mapData.get("ifExamine")) != null) {
                double ifExamine = ((Double) mapData.get("ifExamine")).doubleValue();
                bodyBean.setIfExamine((int) ifExamine);
            }
            if (mapData.get("networkState") != null) {
                String networkState = String.valueOf(mapData.get("networkState"));
                bodyBean.setNetworkState(networkState);
            }
            if (((Double) mapData.get("currentSimPackage")) != null) {
                double currentSimPackage = ((Double) mapData.get("currentSimPackage")).doubleValue();
                bodyBean.setCurrentSimPackage((int) currentSimPackage);
            }
            if (((Double) mapData.get("settlementMode")) != null) {
                Double settlementMode = (Double) mapData.get("settlementMode");
                bodyBean.setSettlementMode(String.valueOf(settlementMode));
            }

            if (((Double) mapData.get("productType")) != null) {
                Double productType = (Double) mapData.get("productType");
                bodyBean.setProductType(String.valueOf(productType));
            }
            if (((Double) mapData.get("carrierOperator")) != null) {
                Double carrierOperator = (Double) mapData.get("carrierOperator");
                bodyBean.setCarrierOperator(String.valueOf(carrierOperator));
            }

            String networkStandard = (String) mapData.get("networkStandard");
            bodyBean.setNetworkStandard(networkStandard);
            String supplierId = (String) mapData.get("supplierId");
            bodyBean.setSupplierId(supplierId);
            String activationMode = (String) mapData.get("activationMode");
            bodyBean.setActivationMode(activationMode);
            String goodsId = (String) mapData.get("goodsId");
            bodyBean.setGoodsId(goodsId);
            String imsi = (String) mapData.get("imsi");
            bodyBean.setImsi(imsi);
            String supplierCode = (String) mapData.get("supplierCode");
            bodyBean.setSupplierCode(supplierCode);
            String orgId = (String) mapData.get("orgId");
            bodyBean.setOrgId(orgId);
            String carrierOperatorName = (String) mapData.get("carrierOperatorName");
            bodyBean.setCarrierOperatorName(carrierOperatorName);
            String iccid = (String) mapData.get("iccid");
            bodyBean.setIccid(iccid);
            String upstreamId = (String) mapData.get("upstreamId");
            bodyBean.setUpstreamId(upstreamId);
            String simId = (String) mapData.get("simId");
            bodyBean.setSimId(simId);
            String customerId = (String) mapData.get("customerId");
            bodyBean.setCustomerId(customerId);
            String goodsName = (String) mapData.get("goodsName");
            bodyBean.setGoodsName(goodsName);
            String productId = (String) mapData.get("productId");
            bodyBean.setProductId(productId);
            String realnameUrl = (String) mapData.get("realnameUrl");
            bodyBean.setRealnameUrl(realnameUrl);
            String simState = (String) mapData.get("simState");
            bodyBean.setSimState(simState);
            String apnType = (String) mapData.get("apnType");
            bodyBean.setApnType(apnType);
            String goodsType = (String) mapData.get("goodsType");
            bodyBean.setGoodsType(goodsType);
            String customType = (String) mapData.get("customType");
            bodyBean.setCustomType(customType);
            String voiceState = (String) mapData.get("voiceState");
            bodyBean.setVoiceState(voiceState);
            String packageEndTime = (String) mapData.get("packageEndTime");
            bodyBean.setPackageEndTime(packageEndTime);
            String simActivationDate = (String) mapData.get("simActivationDate");
            bodyBean.setSimActivationDate(simActivationDate);
            String imei = (String) mapData.get("imei");
            bodyBean.setImei(imei);
            String cardNumber = (String) mapData.get("cardNumber");
            bodyBean.setCardNumber(cardNumber);
            String noBusiness = (String) mapData.get("noBusiness");
            bodyBean.setNoBusiness(noBusiness);
            String arrearage = (String) mapData.get("arrearage");
            bodyBean.setArrearage(arrearage);
            String needActivated = (String) mapData.get("needActivated");
            bodyBean.setNeedActivated(needActivated);
            String packageCount = (String) mapData.get("packageCount");
            bodyBean.setPackageCount(packageCount);
            String statusStr = (String) mapData.get("statusStr");
            bodyBean.setStatusStr(statusStr);

//            LinkedTreeMap simFlow = (LinkedTreeMap) mapData.get("simFlow");
//            if (simFlow != null) {
//                SimFlowBean simFlowBean = new SimFlowBean();
//                simFlowBean.setSimFlow(simFlow, simFlowBean);
//                bodyBean.setSimFlow(simFlowBean);
//            }
        }


        public int getSmsCount() {
            return smsCount;
        }

        public void setSmsCount(int smsCount) {
            this.smsCount = smsCount;
        }

        public String getNetworkStandard() {
            return networkStandard;
        }

        public void setNetworkStandard(String networkStandard) {
            this.networkStandard = networkStandard;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public int getHasAccount() {
            return hasAccount;
        }

        public void setHasAccount(int hasAccount) {
            this.hasAccount = hasAccount;
        }

        public String getActivationMode() {
            return activationMode;
        }

        public void setActivationMode(String activationMode) {
            this.activationMode = activationMode;
        }

        public String getNetworkState() {
            return networkState;
        }

        public void setNetworkState(String networkState) {
            this.networkState = networkState;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getImsi() {
            return imsi;
        }

        public void setImsi(String imsi) {
            this.imsi = imsi;
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public String getSettlementMode() {
            return settlementMode;
        }

        public void setSettlementMode(String settlementMode) {
            this.settlementMode = settlementMode;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getCarrierOperatorName() {
            return carrierOperatorName;
        }

        public void setCarrierOperatorName(String carrierOperatorName) {
            this.carrierOperatorName = carrierOperatorName;
        }

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getUpstreamId() {
            return upstreamId;
        }

        public void setUpstreamId(String upstreamId) {
            this.upstreamId = upstreamId;
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

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public int getIfRenew() {
            return ifRenew;
        }

        public void setIfRenew(int ifRenew) {
            this.ifRenew = ifRenew;
        }

        public String getCarrierOperator() {
            return carrierOperator;
        }

        public void setCarrierOperator(String carrierOperator) {
            this.carrierOperator = carrierOperator;
        }

        public int getIfExamine() {
            return ifExamine;
        }

        public void setIfExamine(int ifExamine) {
            this.ifExamine = ifExamine;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getRealnameUrl() {
            return realnameUrl;
        }

        public void setRealnameUrl(String realnameUrl) {
            this.realnameUrl = realnameUrl;
        }

        public String getSimState() {
            return simState;
        }

        public void setSimState(String simState) {
            this.simState = simState;
        }

        public String getApnType() {
            return apnType;
        }

        public void setApnType(String apnType) {
            this.apnType = apnType;
        }


        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public int getCashback() {
            return cashback;
        }

        public void setCashback(int cashback) {
            this.cashback = cashback;
        }

        public int getRealname() {
            return realname;
        }

        public void setRealname(int realname) {
            this.realname = realname;
        }

        public int getCancelPackage() {
            return cancelPackage;
        }

        public void setCancelPackage(int cancelPackage) {
            this.cancelPackage = cancelPackage;
        }

        public int getImeiNum() {
            return imeiNum;
        }

        public void setImeiNum(int imeiNum) {
            this.imeiNum = imeiNum;
        }

        public String getCustomType() {
            return customType;
        }

        public void setCustomType(String customType) {
            this.customType = customType;
        }

        public String getVoiceState() {
            return voiceState;
        }

        public void setVoiceState(String voiceState) {
            this.voiceState = voiceState;
        }

        public String getPackageEndTime() {
            return packageEndTime;
        }

        public void setPackageEndTime(String packageEndTime) {
            this.packageEndTime = packageEndTime;
        }

        public String getSimActivationDate() {
            return simActivationDate;
        }

        public void setSimActivationDate(String simActivationDate) {
            this.simActivationDate = simActivationDate;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getNoBusiness() {
            return noBusiness;
        }

        public void setNoBusiness(String noBusiness) {
            this.noBusiness = noBusiness;
        }

        public String getArrearage() {
            return arrearage;
        }

        public void setArrearage(String arrearage) {
            this.arrearage = arrearage;
        }

        public String getNeedActivated() {
            return needActivated;
        }

        public void setNeedActivated(String needActivated) {
            this.needActivated = needActivated;
        }

        public String getPackageCount() {
            return packageCount;
        }

        public void setPackageCount(String packageCount) {
            this.packageCount = packageCount;
        }

        public int getCurrentSimPackage() {
            return currentSimPackage;
        }

        public void setCurrentSimPackage(int currentSimPackage) {
            this.currentSimPackage = currentSimPackage;
        }

        public SimFlowBean getSimFlow() {
            return simFlow;
        }

        public void setSimFlow(SimFlowBean simFlow) {
            this.simFlow = simFlow;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "cashback=" + cashback +
                    ", realname=" + realname +
                    ", cancelPackage=" + cancelPackage +
                    ", imeiNum=" + imeiNum +
                    ", hasAccount=" + hasAccount +
                    ", balance=" + balance +
                    ", ifRenew=" + ifRenew +
                    ", ifExamine=" + ifExamine +
                    ", networkState='" + networkState + '\'' +
                    ", currentSimPackage=" + currentSimPackage +
                    ", smsCount=" + smsCount +
                    ", networkStandard='" + networkStandard + '\'' +
                    ", supplierId='" + supplierId + '\'' +
                    ", activationMode='" + activationMode + '\'' +
                    ", goodsId='" + goodsId + '\'' +
                    ", imsi='" + imsi + '\'' +
                    ", supplierCode='" + supplierCode + '\'' +
                    ", settlementMode='" + settlementMode + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", carrierOperatorName='" + carrierOperatorName + '\'' +
                    ", iccid='" + iccid + '\'' +
                    ", upstreamId='" + upstreamId + '\'' +
                    ", simId='" + simId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", productType='" + productType + '\'' +
                    ", carrierOperator='" + carrierOperator + '\'' +
                    ", productId='" + productId + '\'' +
                    ", realnameUrl='" + realnameUrl + '\'' +
                    ", simState='" + simState + '\'' +
                    ", apnType='" + apnType + '\'' +
                    ", goodsType='" + goodsType + '\'' +
                    ", customType='" + customType + '\'' +
                    ", voiceState='" + voiceState + '\'' +
                    ", packageEndTime='" + packageEndTime + '\'' +
                    ", simActivationDate='" + simActivationDate + '\'' +
                    ", imei='" + imei + '\'' +
                    ", cardNumber='" + cardNumber + '\'' +
                    ", noBusiness='" + noBusiness + '\'' +
                    ", arrearage='" + arrearage + '\'' +
                    ", needActivated='" + needActivated + '\'' +
                    ", packageCount='" + packageCount + '\'' +
                    ", simFlow=" + simFlow +
                    '}';
        }

        public static class SimFlowBean implements Serializable {
            /**
             * useCallDuration : 0
             * useMessageHistory : 0
             * year : 2019
             * useCallDurationHistory : 0
             * useMessage : 0
             * useTraffic : 5.48
             * updateTime : 2019-03-27 10:53:12
             * packageMessage : 0
             * iccid : 898602F31318C0378248
             * packageTraffic : -1
             * packageCallDuration : 100
             * month : 4
             * createTime : 2019-03-27 10:53:12
             * useTrafficHistory : 0
             * createUser : d798969b9b194354a11ef5f958de74ef
             * flowId : cfb05dee47d94de1b9be44801a52f541
             * cardNumber : 17234248248
             * customerId : fb92fe0ca7974337ac183a30d12ccdcd
             * goodsId : 6df71430b18b0b49e0535c111eac826d
             * orgId : fb92fe0ca7974337ac183a30d12ccdcd
             */

            private double useCallDuration;
            private double useMessageHistory;
            private double year;
            private double useCallDurationHistory;
            private double useMessage;
            private double useTraffic;
            private double packageMessage;
            private double packageTraffic;
            private double packageCallDuration;
            private double month;
            private double useTrafficHistory;

            private String updateTime;
            private String iccid;
            private String createTime;
            private String createUser;
            private String flowId;
            private String cardNumber;
            private String customerId;
            private String goodsId;
            private String orgId;
            private String statusStr;

            @Override
            public String toString() {
                return "SimFlowBean{" +
                        "useCallDuration=" + useCallDuration +
                        ", useMessageHistory=" + useMessageHistory +
                        ", year=" + year +
                        ", useCallDurationHistory=" + useCallDurationHistory +
                        ", useMessage=" + useMessage +
                        ", useTraffic=" + useTraffic +
                        ", packageMessage=" + packageMessage +
                        ", packageTraffic=" + packageTraffic +
                        ", packageCallDuration=" + packageCallDuration +
                        ", month=" + month +
                        ", useTrafficHistory=" + useTrafficHistory +
                        ", updateTime='" + updateTime + '\'' +
                        ", iccid='" + iccid + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", createUser='" + createUser + '\'' +
                        ", flowId='" + flowId + '\'' +
                        ", cardNumber='" + cardNumber + '\'' +
                        ", customerId='" + customerId + '\'' +
                        ", goodsId='" + goodsId + '\'' +
                        ", orgId='" + orgId + '\'' +
                        '}';
            }

            public void setSimFlow(LinkedTreeMap mapData, SimFlowBean bodyBean) {
                if (((Double) mapData.get("useCallDuration")) != null) {
                    double useCallDuration = ((Double) mapData.get("useCallDuration")).doubleValue();
                    bodyBean.setUseCallDuration(useCallDuration);
                }
                if (((Double) mapData.get("useMessageHistory")) != null) {
                    double useMessageHistory = ((Double) mapData.get("useMessageHistory")).doubleValue();
                    bodyBean.setUseMessageHistory(useMessageHistory);
                }
                if (((Double) mapData.get("year")) != null) {
                    double year = ((Double) mapData.get("year")).doubleValue();
                    bodyBean.setYear(year);
                }
                if (((Double) mapData.get("useCallDurationHistory")) != null) {
                    double useCallDurationHistory = ((Double) mapData.get("useCallDurationHistory")).doubleValue();
                    bodyBean.setUseCallDurationHistory(useCallDurationHistory);
                }
                if (((Double) mapData.get("useMessage")) != null) {
                    double useMessage = ((Double) mapData.get("useMessage")).doubleValue();
                    bodyBean.setUseMessage(useMessage);
                }
                if (((Double) mapData.get("useTraffic")) != null) {
                    double useTraffic = ((Double) mapData.get("useTraffic")).doubleValue();
                    bodyBean.setUseTraffic(useTraffic);
                }
                if (((Double) mapData.get("packageMessage")) != null) {
                    double packageMessage = ((Double) mapData.get("packageMessage")).doubleValue();
                    bodyBean.setPackageMessage(packageMessage);
                }
                if (((Double) mapData.get("packageTraffic")) != null) {
                    double packageTraffic = ((Double) mapData.get("packageTraffic")).doubleValue();
                    bodyBean.setPackageTraffic(packageTraffic);
                }
                if (((Double) mapData.get("packageCallDuration")) != null) {
                    double packageCallDuration = ((Double) mapData.get("packageCallDuration")).doubleValue();
                    bodyBean.setPackageCallDuration(packageCallDuration);
                }
                if (((Double) mapData.get("month")) != null) {
                    double month = ((Double) mapData.get("month")).doubleValue();
                    bodyBean.setMonth(month);
                }
                if (((Double) mapData.get("useTrafficHistory")) != null) {
                    double useTrafficHistory = ((Double) mapData.get("useTrafficHistory")).doubleValue();
                    bodyBean.setUseTrafficHistory((int) useTrafficHistory);
                }

                String updateTime = (String) mapData.get("updateTime");
                bodyBean.setUpdateTime(updateTime);
                String iccid = (String) mapData.get("iccid");
                bodyBean.setIccid(iccid);
                String createTime = (String) mapData.get("createTime");
                bodyBean.setCreateTime(createTime);
                String createUser = (String) mapData.get("createUser");
                bodyBean.setCreateUser(createUser);
                String flowId = (String) mapData.get("flowId");
                bodyBean.setFlowId(flowId);
                String cardNumber = (String) mapData.get("cardNumber");
                bodyBean.setCardNumber(cardNumber);
                String customerId = (String) mapData.get("customerId");
                bodyBean.setCustomerId(customerId);
                String goodsId = (String) mapData.get("goodsId");
                bodyBean.setGoodsId(goodsId);
                String orgId = (String) mapData.get("orgId");
                bodyBean.setOrgId(orgId);
            }

            public double getUseCallDuration() {
                return useCallDuration;
            }

            public void setUseCallDuration(double useCallDuration) {
                this.useCallDuration = useCallDuration;
            }

            public double getUseMessageHistory() {
                return useMessageHistory;
            }

            public void setUseMessageHistory(double useMessageHistory) {
                this.useMessageHistory = useMessageHistory;
            }

            public double getYear() {
                return year;
            }

            public void setYear(double year) {
                this.year = year;
            }

            public double getUseCallDurationHistory() {
                return useCallDurationHistory;
            }

            public void setUseCallDurationHistory(double useCallDurationHistory) {
                this.useCallDurationHistory = useCallDurationHistory;
            }

            public double getUseMessage() {
                return useMessage;
            }

            public void setUseMessage(double useMessage) {
                this.useMessage = useMessage;
            }

            public double getUseTraffic() {
                return useTraffic;
            }

            public void setUseTraffic(double useTraffic) {
                this.useTraffic = useTraffic;
            }

            public double getPackageMessage() {
                return packageMessage;
            }

            public void setPackageMessage(double packageMessage) {
                this.packageMessage = packageMessage;
            }

            public double getPackageTraffic() {
                return packageTraffic;
            }

            public void setPackageTraffic(double packageTraffic) {
                this.packageTraffic = packageTraffic;
            }

            public double getPackageCallDuration() {
                return packageCallDuration;
            }

            public void setPackageCallDuration(double packageCallDuration) {
                this.packageCallDuration = packageCallDuration;
            }

            public double getMonth() {
                return month;
            }

            public void setMonth(double month) {
                this.month = month;
            }

            public double getUseTrafficHistory() {
                return useTrafficHistory;
            }

            public void setUseTrafficHistory(double useTrafficHistory) {
                this.useTrafficHistory = useTrafficHistory;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getIccid() {
                return iccid;
            }

            public void setIccid(String iccid) {
                this.iccid = iccid;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getFlowId() {
                return flowId;
            }

            public void setFlowId(String flowId) {
                this.flowId = flowId;
            }

            public String getCardNumber() {
                return cardNumber;
            }

            public void setCardNumber(String cardNumber) {
                this.cardNumber = cardNumber;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
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

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }
        }
    }

}
