package com.harshraj.dsproject;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Model_Adapter extends RecyclerView.Adapter<Model_Adapter.MyViewholder> {
    Context context;
    ArrayList<Model> post;
    private PopupWindow popupWindow;
    private String poi;

    public Model_Adapter(Context c, ArrayList<Model> p) {
        context = c;
        post = p;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int i) {
        holder.name.setText(post.get(i).getWord());
        holder.desc.setText(post.get(i).getMeaning());
    }

    @Override
    public int getItemCount() {

        return post.size();
    }
    public void filterList(ArrayList<Model> filtered)
    {
        post=filtered;
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position)

    {
        return position;
    }
    @Override
    public int getItemViewType(int pos)
    {

        return pos;
    }
    class MyViewholder extends RecyclerView.ViewHolder {

        TextView name, desc;
        ImageView imageView;
        public MyViewholder(View itemview) {

            super(itemview);
            name = (TextView) itemview.findViewById(R.id.list_title);
            desc = (TextView) itemview.findViewById(R.id.list_desc);

        }
    }
}

