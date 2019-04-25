package com.example.bloodbank.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.utilities.User;

import java.util.List;

public class UserListViewAdapter extends RecyclerView.Adapter<UserListViewAdapter.ViewHolder> {

    private Context context;
    private List<User> deviceList;
    private LayoutInflater mInflater;
    public ItemClickListener mClickListener;

    // State of the item
    private boolean expanded;

    // Data is passed into the constructor
    public UserListViewAdapter(Context context, List<User> deviceList) {
        this.mInflater = LayoutInflater.from(context);
        this.deviceList  = deviceList;
        this.context = context;

    }
    // Data is passed into the constructor
    public void setDevicesList(List<User> deviceList) {
        this.deviceList  = deviceList;
        this.notifyDataSetChanged();
    }

    // Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_user_list, parent, false);
        return new ViewHolder(view);
    }

    // Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final User device = deviceList.get(position);
        /*holder.idTextView.setText(device._id());
        holder.myTextView.setText(device.name());*/
        holder.bind(device);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = UserListViewAdapter.this.isExpanded();
                UserListViewAdapter.this.setExpanded(!expanded);
                UserListViewAdapter.this.notifyItemChanged(position);
            }
        });

    }

        // Total number of rows
    @Override
    public int getItemCount() {
        return deviceList == null ? 0 : deviceList.size();
    }

    // Stores and recycles views as they are scrolled off screen
    // public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dev_name, dev_status, dev_sigfoxid, dev_location, dev_estate, dev_lastmessage;

        private View subItem;

        ViewHolder(View itemView) {
            super(itemView);
            /*dev_name = itemView.findViewById(R.id.device_name);
            dev_status = itemView.findViewById(R.id.device_status);
            dev_sigfoxid = itemView.findViewById(R.id.device_sigfoxid);
            dev_location = itemView.findViewById(R.id.device_location);
            dev_estate = itemView.findViewById(R.id.device_estate);
            dev_lastmessage = itemView.findViewById(R.id.device_lastmessage);
            subItem = itemView.findViewById(R.id.sub_item);*/
            // nodeRow    = itemView.findViewById(R.id.nodeRow);
            // itemView.setOnClickListener(this);
        }

        public void bind(User device) {
            /*// Get the state
            boolean expanded = isExpanded();
            // Set the visibility based on state
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            dev_name.setText("Name: " + device.name());
            dev_status.setText("Status: " + device.activealert());
            dev_sigfoxid.setText("sigfoxid: " + device.sigfoxid());
            dev_location.setText("Location: " + device.location());
            dev_estate.setText("Estate: " + device.estate());
            dev_lastmessage.setText("Last Message: " + device.lastmessage());*/
        }
    }

    // Convenience method for getting data at click position
    User getItem(int id) {
        return deviceList.get(id);
    }

    // Allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
