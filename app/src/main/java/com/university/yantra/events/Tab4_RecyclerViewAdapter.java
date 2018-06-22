package com.university.yantra.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.university.yantra.R;

import java.util.ArrayList;

/**
 * Created by rahul on 18/2/18.
 */

public class Tab4_RecyclerViewAdapter extends RecyclerView.Adapter<Tab4_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> months;

    public Tab4_RecyclerViewAdapter(Context context, ArrayList<String> months) {
        this.context = context;
        this.months = months;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.tab4_recyclerview1_adapter,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(months.get(position));

    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tv_month_tab4);
        }
    }

}
