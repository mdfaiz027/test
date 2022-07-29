package com.example.test;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    Context context;
    List<Uri> childModelClassList;

    public ChildAdapter(Context context, List<Uri> childModelClassList) {
        this.context = context;
        this.childModelClassList = childModelClassList;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout,null,false);
        return new ChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageURI(childModelClassList.get(position));
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
        }
    }
}
