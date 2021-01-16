package com.cxsz.elu.main.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.elu.main.App;
import com.cxsz.elu.main.component.FormatUtils;
import com.cxsz.elu.main.component.OnGetMealInfoListener;
import com.cxsz.elu.main.component.OnGetWeChatInfoListener;
import com.cxsz.elu.main.component.SimCardUtils;
import com.cxsz.elu.main.component.manger.QrCodeManger;
import com.cxsz.elu.main.model.bean.EventBean;
import com.cxsz.elu.main.model.bean.MealGoodsBean;
import com.cxsz.elu.main.model.bean.SimDetailInfo;
import com.cxsz.elu.main.model.bean.SimPackageBean;
import com.cxsz.elu.main.model.net.NetResponseService;
import com.cxsz.elu.main.view.activity.IntelligentDiagnosisActivity;
import com.cxsz.elu.main.view.adapter.PackagePurchaseRecycleAdapter;
import com.cxsz.elu.main.view.adapter.UnActivePackageAdapter;
import com.cxsz.framework.BuildConfig;
import com.cxsz.framework.base.BaseFragment;
import com.cxsz.framework.constant.KeyConstants;
import com.cxsz.framework.net.WeChatInfoBean;
import com.cxsz.framework.tool.LoggerUtil;
import com.cxsz.framework.tool.SystemUtils;
import com.cxsz.framework.tool.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 简  述  套餐信息显示界面
 * 作  者  demons
 * 类  名  PackageInformationFragment
 * 时  间  2020/5/25 11:01 PM
 */

public class PackageInformationFragment extends BaseFragment implements QrCodeManger.OnShowCodeListener, OnGetWeChatInfoListener, OnGetMealInfoListener {
    private View titleArea;
    private TextView titleName;
    private TextView simId;
    private TextView iccId;
    private TextView cardTypeName;
    private TextView mealDetailInfo;
    private RecyclerView purchaseHistory;
    private View purchaseHistoryNoData;
    private ImageView buyCode;
    private View refreshInfo;
    private TextView endTime;
    private ImageView purchaseNoData;
    private LinearLayout packageIsDue; //套餐即将到期
    private LinearLayout insufficientFlow; //流量不足
    private LinearLayout centerLoading;
    private LinearLayout rightLoading;
    private View intelligentDiagnosis;
    private UnActivePackageAdapter unActivePackageAdapter;
    private TextView phoneNumber;
    private TextView simStatus;
    private ProgressBar flowShow;
    private TextView remainFlow;
    private TextView startTime;
    private RecyclerView packagePurchaseList;

    private List<SimPackageBean.BodyBean> infoLists = new ArrayList<>();
    private List<MealGoodsBean.MealGoodsBodyBean> mealGoodsBodyBeans = new ArrayList<>();
    private double remainMB;
    private SimDetailInfo.BodyBean simDetailInfo;

    public static PackageInformationFragment getInstance() {
        return new PackageInformationFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        titleArea = rootView.findViewById(R.id.title_area);
        titleName = rootView.findViewById(R.id.title_name);
        simId = rootView.findViewById(R.id.sim_id);
        iccId = rootView.findViewById(R.id.icc_id);
        cardTypeName = rootView.findViewById(R.id.card_type_name);
        flowShow = rootView.findViewById(R.id.flow_show);
        remainFlow = rootView.findViewById(R.id.remain_flow);
        mealDetailInfo = rootView.findViewById(R.id.meal_detail_info);
        purchaseHistory = rootView.findViewById(R.id.purchase_history);
        purchaseHistoryNoData = rootView.findViewById(R.id.purchase_history_no_data);
        buyCode = rootView.findViewById(R.id.buy_code);
        refreshInfo = rootView.findViewById(R.id.refresh_info);
        startTime = rootView.findViewById(R.id.start_time);
        endTime = rootView.findViewById(R.id.end_time);
        purchaseNoData = rootView.findViewById(R.id.purchase_no_data);
        packageIsDue = rootView.findViewById(R.id.package_is_due);
        insufficientFlow = rootView.findViewById(R.id.insufficient_flow);
        centerLoading = rootView.findViewById(R.id.center_loading);
        rightLoading = rootView.findViewById(R.id.right_loading);

        packagePurchaseList = rootView.findViewById(R.id.package_purchase_list);
        intelligentDiagnosis = rootView.findViewById(R.id.intelligent_diagnosis);
        intelligentDiagnosis.setOnClickListener(this::onViewClick);
        phoneNumber = rootView.findViewById(R.id.phone_number);
        simStatus = rootView.findViewById(R.id.sim_status);

        titleName.setText("流量管理系统");
        iccId.setSelected(true);
        titleArea.setOnClickListener(this::onViewClick);
        refreshInfo.setOnClickListener(this::onViewClick);
        unActivePackageAdapter = new UnActivePackageAdapter(infoLists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        purchaseHistory.setLayoutManager(linearLayoutManager);
        purchaseHistory.setAdapter(unActivePackageAdapter);
        QrCodeManger.getIntances().setOnShowCodeListener(this);
        String iccIdInfo = SimCardUtils.getIccId(App.getContext());
        if (null != iccIdInfo) {
            iccId.setText(iccIdInfo.toUpperCase());
        }
        NetResponseService.getInstance().getSimByParam(this);
        NetResponseService.getInstance().getSimPackage(this);
        NetResponseService.getInstance().getPackageCanBuy(this);
        NetResponseService.getInstance().RequestGetWeChatInfo(this);
        QrCodeManger.getIntances().getQRCode(BuildConfig.WECHAT_URL + SimCardUtils.getIccId(App.getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        packagePurchaseList.setLayoutManager(gridLayoutManager);
        PackagePurchaseRecycleAdapter packagePurchaseRecycleAdapter = new PackagePurchaseRecycleAdapter(getActivity(), mealGoodsBodyBeans);
        packagePurchaseList.setAdapter(packagePurchaseRecycleAdapter);
    }

    @Override
    protected void lazyFetchData() {

    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.refresh_info:
                NetResponseService.getInstance().getSimByParam(this);
                NetResponseService.getInstance().getSimPackage(this);

                break;
            case R.id.intelligent_diagnosis:
                if (null == SimCardUtils.getIccId(App.getContext())) {
                    ToastUtil.show(App.getContext(), "未能识别到SIM卡，请检查设备后再使用！");
                } else {
                    Intent intent = new Intent(getActivity(), IntelligentDiagnosisActivity.class);
                    SimDetailInfo.BodyBean bodyBean = new SimDetailInfo.BodyBean();
                    bodyBean.setIccid(SimCardUtils.getIccId(App.getContext()));
                    if (null == simDetailInfo) {
                        bodyBean.setCardNumber("");
                    } else {
                        bodyBean.setCardNumber(simDetailInfo.getCardNumber());
                    }
                    intent.putExtra(KeyConstants.MEAL_INFO_BEAN, bodyBean);
                    startActivity(intent);
                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                break;
            default:
                break;
        }
    }

    //购买套餐二维码
    @Override
    public void showBuyQrCodeImage(Bitmap bitmap) {
        if (isAdded()) {
            buyCode.setImageBitmap(bitmap);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void responseEventBean(EventBean eventBean) {
        switch (eventBean.getCode()) {
            case KeyConstants.REQUEST_SIM_DETAIL_INFO_FAILURE:
                centerLoading.setVisibility(View.GONE);
                purchaseNoData.setVisibility(View.VISIBLE);
                rightLoading.setVisibility(View.GONE);
                purchaseHistory.setVisibility(View.GONE);
                purchaseHistoryNoData.setVisibility(View.VISIBLE);
                break;
            case KeyConstants.REQUEST_SIM_PACKAGE_INFO_FAILURE:
                purchaseHistoryNoData.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 卡信息详情
     *
     * @param simDetailInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displaySimDetailInfo(SimDetailInfo.BodyBean simDetailInfo) {
        this.simDetailInfo = simDetailInfo;
        LoggerUtil.e("SimDetailInfo数据为:", simDetailInfo.toString());
        if (simDetailInfo != null) {
            purchaseHistory.setVisibility(View.VISIBLE);
            SimDetailInfo.BodyBean something = simDetailInfo;
            if (null != simDetailInfo.getStatusStr()) {
                simStatus.setText(simDetailInfo.getStatusStr());
            }
            if (!TextUtils.isEmpty(something.getCardNumber())) {//卡号
                simId.setText(something.getCardNumber());
            }
            if (!TextUtils.isEmpty(SimCardUtils.getIccId(App.getContext()))) {//ICCID
                iccId.setText(SimCardUtils.getIccId(App.getContext()).toUpperCase());
            }

            if (!TextUtils.isEmpty(something.getGoodsName())) {//当前套餐
                if (something.getGoodsName().contains("-")) {
                    String goodName = something.getGoodsName().replace("-", "");
                    mealDetailInfo.setText(goodName);
                } else {
                    mealDetailInfo.setText(something.getGoodsName());
                }
            }
        } else {
            if (!TextUtils.isEmpty(SimCardUtils.getIccId(App.getContext()))) {//ICCID
                iccId.setText(SimCardUtils.getIccId(App.getContext()).toUpperCase());
            }
            mealDetailInfo.setText(getString(R.string.no));
        }
        centerLoading.setVisibility(View.GONE);
        rightLoading.setVisibility(View.GONE);
    }

    /**
     * 启用未启用的卡套餐详情
     *
     * @param simPackageBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displaySimPackageInfo(List<SimPackageBean.BodyBean> simPackageBean) {
        if (simPackageBean != null) {
            infoLists.clear();
            infoLists.addAll(simPackageBean);
            if (infoLists.size() > 0) {
                purchaseHistoryNoData.setVisibility(View.GONE);
                purchaseNoData.setVisibility(View.GONE);
                Collections.sort(infoLists);
                unActivePackageAdapter.noticeUnActivePackages(infoLists);
            } else {
                purchaseHistory.setVisibility(View.GONE);
                purchaseHistoryNoData.setVisibility(View.VISIBLE);
                purchaseNoData.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 展示套餐详细信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showMealDetailInfo(SimPackageBean.BodyBean bodyBean) {
        if (null != bodyBean) {
            String productTypeName = bodyBean.getDisplayPackageName();
            if (null != productTypeName) {
                cardTypeName.setText(productTypeName + "");
            }
            if (bodyBean.getPackageActiveTime() != null && !bodyBean.getPackageActiveTime().isEmpty()) {
                startTime.setText(bodyBean.getPackageActiveTime());
            }
            if (bodyBean.getPackageEndTime() != null && !bodyBean.getPackageEndTime().isEmpty()) {
                endTime.setText(bodyBean.getPackageEndTime());
            }

            purchaseNoData.setVisibility(View.GONE);
            if (bodyBean.getPackageTraffic() != -1) {
                //流量有限
                remainMB = bodyBean.getPackageTraffic() - bodyBean.getUseTraffic();
                if (bodyBean.getPackageTraffic() != 0) {
                    flowShow.setProgress((int) (bodyBean.getUseTraffic() / bodyBean.getPackageTraffic()));
                }
                remainFlow.setText(FormatUtils.formatFlowSize(remainMB));
            } else {
                //流量无限
                if (null != bodyBean.getPackageActiveTime()) {
                    int days = SystemUtils.getDays(bodyBean.getPackageEndTime(), bodyBean.getPackageActiveTime());
                    int remainDays = SystemUtils.getRemainDays(bodyBean.getPackageEndTime());
                    if (remainDays <= 5) {
                        packageIsDue.setVisibility(View.VISIBLE);
                    } else {
                        packageIsDue.setVisibility(View.GONE);
                    }
                }
            }
            if (remainMB < 100 && (bodyBean.getPackageTraffic() != -1) && (bodyBean.getPackageTraffic() > 0)) {
                insufficientFlow.setVisibility(View.VISIBLE);
            } else {
                insufficientFlow.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showUiErrorInfo(String info) {
        LoggerUtil.e(info);
    }

    @Override
    public void ResponseWeChatInfo(WeChatInfoBean weChatInfoBean) {
        phoneNumber.setText("客服热线：" + weChatInfoBean.getBody().getPhone());
    }

    @Override
    public void showLoading() {
        if (isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    centerLoading.setVisibility(View.VISIBLE);
                    rightLoading.setVisibility(View.VISIBLE);
                    purchaseHistory.setVisibility(View.GONE);
                    purchaseNoData.setVisibility(View.GONE);
                    purchaseHistoryNoData.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void closeLoading() {
        if (isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    centerLoading.setVisibility(View.GONE);
                    rightLoading.setVisibility(View.GONE);
                }
            });
        }
    }
}
