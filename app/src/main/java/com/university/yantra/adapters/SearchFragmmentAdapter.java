package com.university.yantra.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.university.yantra.Fragments.SearchFragment;
import com.university.yantra.R;

import java.util.ArrayList;

/**
 * Created by rahul on 3/5/18.
 */

public class SearchFragmmentAdapter extends RecyclerView.Adapter<SearchFragmmentAdapter.CustomSearchViewHolder> {

    private Context context;
    private int previousPosition=0;
    private LayoutInflater inflater;

    private int visibleThreshold = 5;
    boolean isLoading = false, isMoreDataAvailable = true;
    int x;
    ArrayList<String> stringArrayList = new ArrayList<>();
    private int lastVisibleItem, totalItemCount;

    SearchFragment obj;

    public SearchFragmmentAdapter(Context context, ArrayList<String> stringArrayList, SearchFragment obj) {
        this.context = context;
        this.stringArrayList = stringArrayList;
        this.obj = obj;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public CustomSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_item,parent,false);


        return new CustomSearchViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    @Override
    public void onBindViewHolder(final CustomSearchViewHolder holder, final int position) {
        if(position==2){

            holder.PopItem.setTextColor(Color.BLACK);
            holder.PopItem.setTextSize(15);

        }

        holder.PopItem.setText(stringArrayList.get(position));
        holder.PopItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position>2){
                    //obj.onQueryTextSubmit(holder.PopItem.getText().toString());
                    obj.searchView.setQuery(holder.PopItem.getText().toString(),true);
                }
            }
        });


    }

    public class CustomSearchViewHolder extends RecommendedUniAdapter.RecyclerViewHolder {
        public TextView PopItem;
        public View parent;

        public CustomSearchViewHolder(View view){
            super(view);
            PopItem =(TextView)view.findViewById(R.id.item);
            this.parent = view;
        }

    }
}
