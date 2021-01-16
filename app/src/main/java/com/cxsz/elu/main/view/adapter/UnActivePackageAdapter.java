package com.cxsz.elu.main.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.elu.main.model.bean.SimPackageBean;

import java.util.List;

public class UnActivePackageAdapter extends RecyclerView.Adapter<UnActivePackageAdapter.UnActivePackageHolder> {
    private List<SimPackageBean.BodyBean> mSimPackageBean;

    public UnActivePackageAdapter(List<SimPackageBean.BodyBean> simPackageBean) {
        this.mSimPackageBean = simPackageBean;
    }

    public void noticeUnActivePackages(List<SimPackageBean.BodyBean> simPackageBean) {
        this.mSimPackageBean = simPackageBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UnActivePackageAdapter.UnActivePackageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false);
        UnActivePackageAdapter.UnActivePackageHolder chatHolder = new UnActivePackageHolder(view);
        return chatHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UnActivePackageAdapter.UnActivePackageHolder holder, final int position) {
        final SimPackageBean.BodyBean item = mSimPackageBean.get(position);
        holder.statusNoticeInfo.setText(item.getStatusStr());
        holder.packageName.setText(item.getPackageName() + "");
        holder.dateStartTime.setText(item.getPackageActiveTime() + "");
    }

    @Override
    public int getItemCount() {
        return mSimPackageBean.size();
    }

    public class UnActivePackageHolder extends RecyclerView.ViewHolder {
        public TextView statusNoticeInfo;
        public TextView packageName;
        public TextView dateStartTime;


        public UnActivePackageHolder(View itemView) {
            super(itemView);
            statusNoticeInfo = (TextView) itemView.findViewById(R.id.status_notice_info);
            packageName = (TextView) itemView.findViewById(R.id.list_package);
            dateStartTime = (TextView) itemView.findViewById(R.id.list_start_time);
        }
    }
}
