package com.quest.organdonor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.quest.organdonor.R;
import com.quest.organdonor.ui.home.pojo.Message;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Message productsModel;
    public List<Message> productsModelList;
    private Context mCtx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textDescription;
        public TextView textUsernames;
        public CircleImageView profile_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            textDescription=itemView.findViewById(R.id.textDescription);
            textUsernames=itemView.findViewById(R.id.textUsernames);
            profile_image=itemView.findViewById(R.id.profile_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context mCtx,List<Message> productsModelList) {
        this.mCtx = mCtx;
        this.productsModelList = productsModelList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        productsModel = productsModelList.get(position);

        holder.textUsernames.setText(productsModel.getRandom());
        holder.textDescription.setText(productsModel.getTitle());
        //https://skytoptrial.000webhostapp.com/images/raashin__5d303d.jpg
        Glide
            .with(mCtx)
            .load("https://skytoptrial.000webhostapp.com/images/"+productsModel.getImage())
            .centerCrop()
            .placeholder(R.drawable.loading_spinner)
            .into(holder.profile_image);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return productsModelList.size();
    }
}
