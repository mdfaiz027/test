package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    Context context;
    List<ParentModelClass> parentModelClassArrayList;
    OnItemClickListener onItemClickListener;

    public ParentAdapter(Context context, ArrayList<ParentModelClass> parentModelClassArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.parentModelClassArrayList = parentModelClassArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.parent_rv_layout, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleName.setText(parentModelClassArrayList.get(position).title);

        ChildAdapter childAdapter;
        childAdapter = new ChildAdapter(context,parentModelClassArrayList.get(position).childModelClassList);
        holder.secondRV.setLayoutManager(new GridLayoutManager(context, 4));
        holder.secondRV.setAdapter(childAdapter);
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return parentModelClassArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleName;
        ImageView pickImage;
        RecyclerView secondRV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleName = itemView.findViewById(R.id.titleName);
            pickImage = itemView.findViewById(R.id.pick);
            secondRV = itemView.findViewById(R.id.idSecondRV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
