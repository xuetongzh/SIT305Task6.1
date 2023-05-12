package com.google.trucksharingapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.trucksharingapp.NewDeliveryActivity;
import com.google.trucksharingapp.R;
import com.google.trucksharingapp.bean.TruckBean;
import com.google.trucksharingapp.bean.TruckBean;

import java.util.ArrayList;
import java.util.List;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<TruckBean> data = new ArrayList<>();
    private List<TruckBean> mFilterList = new ArrayList<>();

    public TruckAdapter(Context context, List<TruckBean> data) {
        this.context = context;
        this.data = data;
        this.mFilterList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truck, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TruckBean truckBean = mFilterList.get(position);

        holder.infoBean = truckBean;

        holder.truckInfo.setText(truckBean.getInfo());
        holder.truckNum.setText(truckBean.getNum());
        holder.truckImage.setImageResource(truckBean.getImage());

        // Add click event to each Item
        holder.truckShare.setOnClickListener(v -> {
            // Get the MenuProgress after it has been clicked.
            TruckBean item = mFilterList.get(position);
            context.startActivity(new Intent(context, NewDeliveryActivity.class));
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
                    List<TruckBean> filteredList = new ArrayList<>();
                    for (TruckBean infoBean : data) {
                        //Add matching rules according to the requirements
                        if (infoBean.getInfo().contains(charString)) {
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
                mFilterList = (ArrayList<TruckBean>) filterResults.values;
                //refresh data
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private final ImageView truckImage, truckShare;
        public final TextView truckNum, truckInfo;

        public TruckBean infoBean;

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            this.truckImage = itemView.findViewById(R.id.truckImage);
            this.truckShare = itemView.findViewById(R.id.truckShare);
            this.truckNum = itemView.findViewById(R.id.truckNum);
            this.truckInfo = itemView.findViewById(R.id.truckInfo);
        }
    }
}