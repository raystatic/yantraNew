package com.university.yantra.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.university.yantra.R;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.NotificationData;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rahul on 4/3/18.
 */

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.MyViewHolder> {

    PrefManager prefManager;
    Context context;
    LayoutInflater inflater;
    ArrayList<NotificationData> notificationDataArrayList;

    public NotificationRecyclerViewAdapter(PrefManager prefManager, Context context, ArrayList<NotificationData> notificationDataArrayList) {
        this.prefManager = prefManager;
        this.context = context;
        this.notificationDataArrayList=notificationDataArrayList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.e("object",notificationDataArrayList.get(0).getCollege());
        Log.e("object",notificationDataArrayList.get(1).getCollege());
        Log.e("object",notificationDataArrayList.get(2).getCollege());
        Log.e("object",notificationDataArrayList.get(3).getCollege());
        Log.e("object",notificationDataArrayList.get(4).getCollege());
        Log.e("object",notificationDataArrayList.get(5).getCollege());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.notification_adapter_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NotificationData notificationData=notificationDataArrayList.get(position);
        Picasso.with(context)
                .load(notificationData.getImage())
                .fit()
                .into(holder.imageView);
//        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nav_notif));

        holder.textView_college.setText(notificationData.getCollege());
        holder.textView_message.setText(notificationData.getMessage());
        holder.textView_timeNumeric.setText(notificationData.getTime_numeric());
        holder.textView_timeText.setText(notificationData.getTime_text());
        holder.textView_timeAgo.setText(notificationData.getTime_ago());
        holder.textView_apply.setText(notificationData.getApply_button_text());
        holder.cardView_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Applied at "+holder.textView_college.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("object"+position,notificationData.getCollege());

    }

    @Override
    public int getItemCount() {
        return notificationDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_college, textView_message, textView_timeNumeric, textView_timeText;
        TextView textView_timeAgo, textView_apply;
        CardView cardView_apply;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            textView_college=(TextView)itemView.findViewById(R.id.tv_noti_adapter_college);
            textView_message=(TextView)itemView.findViewById(R.id.tv_noti_adapter_message);
            textView_timeNumeric=(TextView)itemView.findViewById(R.id.tv_noti_adapter_timeNumeric);
            textView_timeText=(TextView)itemView.findViewById(R.id.tv_noti_adapter_timeText);
            textView_timeAgo=(TextView)itemView.findViewById(R.id.tv_noti_adapter_timeAgo);
            textView_apply=(TextView)itemView.findViewById(R.id.tv_noti_adapter_apply);
            cardView_apply=(CardView)itemView.findViewById(R.id.card_notif_apply);
            imageView=(ImageView)itemView.findViewById(R.id.img_view_noti_adapter);
        }
    }

}
