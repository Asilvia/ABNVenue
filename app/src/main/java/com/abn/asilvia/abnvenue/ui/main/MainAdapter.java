package com.abn.asilvia.abnvenue.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.util.AppNavigation;
import com.abn.asilvia.abnvenue.vo.VenueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asilvia on 19/05/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    List<VenueObject> listVenues;
    Context context;

    public MainAdapter(Context context) {

        this.listVenues = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VenueObject item= listVenues.get(position);
        holder.title.setText(item.getTitle());
        holder.address.setText(item.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppNavigation.goToDetailsActivity(context, item.getId(), item.getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listVenues.size();
    }

    public void setListData(List<VenueObject> data)
    {
        this.listVenues.clear();
        this.listVenues.addAll(data);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

            TextView title;
            TextView address;

        public ViewHolder(View view) {
            super(view);
            title =(TextView) view.findViewById(R.id.title);
            address = (TextView)view.findViewById(R.id.address);
        }

    }
}
