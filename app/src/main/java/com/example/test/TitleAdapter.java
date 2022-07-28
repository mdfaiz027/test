package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {

    Context context;
    ArrayList<TitleModel> titleModelArrayList;
    OnItemClickListener onItemClickListener;

    public TitleAdapter(Context context, ArrayList<TitleModel> titleModelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.titleModelArrayList = titleModelArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layoutforthetitle, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleName.setText(titleModelArrayList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return titleModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleName;
        ImageView pick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleName = itemView.findViewById(R.id.titleName);
            pick = itemView.findViewById(R.id.pick);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
