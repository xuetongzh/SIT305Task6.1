package com.google.trucksharingapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.trucksharingapp.R;
import com.google.trucksharingapp.bean.DeliveryBean;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<DeliveryBean> data = new ArrayList<>();
    private List<DeliveryBean> mFilterList = new ArrayList<>();

    public DeliveryAdapter(Context context, List<DeliveryBean> data) {
        this.context = context;
        this.data = data;
        this.mFilterList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeliveryBean deliveryBean = mFilterList.get(position);

        holder.infoBean = deliveryBean;
        holder.deliveryName.setText(position + "Order name：" + deliveryBean.getName());

        // Add click event to each Item.
        holder.itemView.setOnClickListener(v -> {
            // Get the MenuProgress after it has been clicked
            DeliveryBean item = mFilterList.get(position);
            MessageDialog.show(item.getName(), "Time：" + item.getDateTime() + "\nAddress：" + item.getLocation(), "confirm", "cancel")
                    .setOkButtonClickListener(new OnDialogButtonClickListener<MessageDialog>() {
                        @Override
                        public boolean onClick(MessageDialog dialog, View v) {
                            return false;
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        if (mFilterList == null) {
            return 0;
        }
        return mFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    //If there is no filtered content, use the source data
                    mFilterList = data;
                } else {
                    List<DeliveryBean> filteredList = new ArrayList<>();
                    for (DeliveryBean infoBean : data) {
                        //Add matching rules according to the requirements
                        if (infoBean.getName().contains(charString)) {
                            filteredList.add(infoBean);
                        }
                    }

                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (ArrayList<DeliveryBean>) filterResults.values;
                //refresh data
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView deliveryName;

        public DeliveryBean infoBean;

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            this.deliveryName = itemView.findViewById(R.id.deliveryName);
        }
    }
}