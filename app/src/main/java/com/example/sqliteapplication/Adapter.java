package com.example.sqliteapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Model model = arrayList.get(position);

        String name = model.getName();
        String price = model.getPrice();
        String desc = model.getDesc();
        //String image = model.getDesc();

       // holder.image.setImageURI(Uri.parse(image));
        holder.name.setText(name);
        holder.price.setText(price);
        holder.desc.setText(desc);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        //ImageView image;
        TextView name, price,desc;

        public Holder(@NonNull View itemView) {
            super(itemView);

            //image = itemView.findViewById(R.id.viewimage);
            name = itemView.findViewById(R.id.viewname);
            price = itemView.findViewById(R.id.viewprice);
            desc = itemView.findViewById(R.id.viewdesc);

        }
    }
}
