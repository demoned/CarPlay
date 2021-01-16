package com.cxsz.elu.main.component;

import android.annotation.SuppressLint;

import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.tool.LoggerUtil;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 * @author demons
 * @Title: MQTTClientImpl
 * @Package com.cxsz.elu.main.component
 * @Description: 管理mqtt的连接, 发布, 订阅, 断开连接, 断开重连等操作
 * @date 1/13/21 12:11 AM
 */
@SuppressLint("NewApi")
public class MQTTClientImpl {
    public static final String LOG_TAG = "OneNet";
    private String broker;
    private String userName;
    private String password;
    private String clientId;
    private MqttCallback mqttCallback;
    private MqttConnectOptions options;
    private MqttClient client;

    public MQTTClientImpl(String broker
            , String deviceName, String deviceKey
            , MqttCallback mqttCallback) {
        this.broker = broker;
        this.clientId = deviceName;
        this.userName = KeyConstants.PRODUCT_ID;
        String expirationTime = System.currentTimeMillis() + 10 + "";
        String signatureMethod = MQTTUtils.SignatureMethod.SHA1.name().toLowerCase();
        String res = "products/" + KeyConstants.PRODUCT_ID + "/devices/" + deviceName;
        String version = "2018-10-31";
        try {
            this.password = MQTTUtils.assembleToken(version, res, expirationTime, signatureMethod, deviceKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mqttCallback = mqttCallback;
        initOptions();
        subscribe();
    }

    private void initOptions() {
        boolean flag = false;
        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        try {
            options = new MqttConnectOptions();
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            if (password != null) {
                options.setPassword(password.toCharArray());
            }
            if (userName != null) {
                options.setUserName(userName);
            }
            client = new MqttClient(broker, clientId, dataStore);
            // 配置回调
            client.setCallback(mqttCallback);
            flag = doConnect();
        } catch (MqttException e) {
            LoggerUtil.e(LOG_TAG, e.getMessage());
        }
        LoggerUtil.d(LOG_TAG, "isConnected: " + flag);
    }

    /**
     * 建立连接
     *
     * @return
     */
    public boolean doConnect() {
        boolean flag = false;
        if (client != null) {
            try {
                client.connect(options);
                LoggerUtil.d(LOG_TAG, "Connected to " + client.getServerURI() + " with client ID " + client.getClientId());
                flag = true;
            } catch (Exception e) {
            }
        }
        return flag;
    }

    /**
     * 取消连接
     *
     * @throws MqttException
     */
    public void disConnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
        }
    }

    public void subscribe() {
        try {
            // 设备上行topic（包括设备生命周期状态变更、属性、事件功能点及命令下发响应）
            String upTopics = "$sys/" + KeyConstants.PRODUCT_ID + "/" + clientId + "/thing/property/set";
            client.subscribe(upTopics);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pubProperty(String value) throws MqttException {
        // 设备属性设置topic
        String propertyTopic = "$sys/" + KeyConstants.PRODUCT_ID + "/" + clientId + "/thing/property/set_reply";
        publish(propertyTopic, value);
    }

    public void pubDesired(String value) throws MqttException {
        // 设备期望值设置
        String desiredTopic = "$sys/" + KeyConstants.PRODUCT_ID + "/" + clientId + "/thing/property/post";
        publish(desiredTopic, value);
    }

    private void publish(String topic, String payload) throws MqttException {
        client.publish(topic, new MqttMessage(payload.getBytes()));
    }

    public void close() throws MqttException {
        client.close();
    }
}