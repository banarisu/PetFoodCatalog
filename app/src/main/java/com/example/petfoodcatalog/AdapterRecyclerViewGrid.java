package com.example.petfoodcatalog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerViewGrid extends RecyclerView.Adapter<AdapterRecyclerViewGrid.ViewHolder> {

    private ArrayList<ItemModel> dataItem;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;
        ImageView imageName;

        LinearLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            imageName = itemView.findViewById(R.id.imageName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

    AdapterRecyclerViewGrid(Context context, ArrayList<ItemModel> data){
        this.context = context;
        this.dataItem = data;
    }

    @Override
    public AdapterRecyclerViewGrid.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

        return new AdapterRecyclerViewGrid.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViewGrid.ViewHolder holder, int position) {
        TextView textName = holder.textName;
        TextView textPrice = holder.textPrice;
        ImageView imageName = holder.imageName;

        textName.setText(dataItem.get(position).getName());
        textPrice.setText(dataItem.get(position).getPrice());
        imageName.setImageResource(dataItem.get(position).getImage());

        //Catalog items on click listener
        holder.parentLayout.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("Id", dataItem.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }
}
