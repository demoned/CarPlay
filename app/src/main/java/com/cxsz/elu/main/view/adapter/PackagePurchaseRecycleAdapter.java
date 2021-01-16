package com.cxsz.elu.main.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.elu.main.model.bean.MealGoodsBean;

import java.util.List;

/**
 * 套餐购买
 */
public class PackagePurchaseRecycleAdapter extends RecyclerView.Adapter<PackagePurchaseRecycleAdapter.ViewHolder> {
    private List<MealGoodsBean.MealGoodsBodyBean> chatListBeanList;
    private Context context;
    private OnItemClickListener mItemClickListener;

    public PackagePurchaseRecycleAdapter(Context context, List<MealGoodsBean.MealGoodsBodyBean> chatListBeanList) {
        this.context = context;
        this.chatListBeanList = chatListBeanList;
    }

    public void refreshStatus(List<MealGoodsBean.MealGoodsBodyBean> requestMsgBean) {
        chatListBeanList = requestMsgBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_purchase_item, parent, false);
        ViewHolder chatHolder = new ViewHolder(view);
        return chatHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean = chatListBeanList.get(position);
        holder.packagePurchaseName.setText(mealGoodsBodyBean.getShortName());
        if ((int) mealGoodsBodyBean.getPackageTraffic() == -1) {
            holder.packagePurchaseNameDetails.setText("无限流量+" + (int) mealGoodsBodyBean.getCallDuration() + "分钟/月");
        } else {
            holder.packagePurchaseNameDetails.setText((int) mealGoodsBodyBean.getPackageTraffic() + "M+" + (int) mealGoodsBodyBean.getCallDuration() + "分钟/月");
        }
        holder.packagePurchaseMonthlyAverage.setText("月均¥" + (double) Math.round((mealGoodsBodyBean.getUnitPrice() / mealGoodsBodyBean.getValidityDuration()) * 100) / 100);
        if (mealGoodsBodyBean.getGoodsDesc() != null) {
            holder.packagePurchaseCoupons.setText(mealGoodsBodyBean.getGoodsDesc());
        } else {
            holder.packagePurchaseCoupons.setText("有限期" + (int) mealGoodsBodyBean.getValidityDuration() + "个月");
        }
        holder.originalPrice.setText("原价:¥" + (int) mealGoodsBodyBean.getOfficialPrice());
        holder.presentPrice.setText(mealGoodsBodyBean.getUnitPrice() + "");
        holder.packagePurchaseItemArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatListBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout packagePurchaseItemArea;
        private TextView packagePurchaseName;
        private TextView packagePurchaseNameDetails;
        private TextView packagePurchaseMonthlyAverage;
        private TextView packagePurchaseCoupons;
        private TextView originalPrice;
        private TextView presentPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            packagePurchaseItemArea = itemView.findViewById(R.id.package_purchase_item_area);
            packagePurchaseName = itemView.findViewById(R.id.package_purchase_name);
            packagePurchaseNameDetails = itemView.findViewById(R.id.package_purchase_name_details);
            packagePurchaseMonthlyAverage = itemView.findViewById(R.id.package_purchase_monthly_average);
            packagePurchaseCoupons = itemView.findViewById(R.id.package_purchase_coupons);
            originalPrice = itemView.findViewById(R.id.original_price);
            presentPrice = itemView.findViewById(R.id.present_price);


        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
