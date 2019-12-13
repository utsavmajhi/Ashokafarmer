package com.example.ashokafarmer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mylandAdapter extends RecyclerView.Adapter<mylandAdapter.poolViewHolder> {

    private Context mContext;
    private ArrayList<mylanditem> mlanditems;




    public mylandAdapter(Context context, ArrayList<mylanditem> poollist)
    {
        mContext=context;
        mlanditems=poollist;

    }

    @NonNull
    @Override
    public poolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.pendinglistitem,parent,false);
        return new poolViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull poolViewHolder holder, int position) {

        mylanditem currentitem=mlanditems.get(position);
        String lname=currentitem.getLandname();
        String larea=currentitem.getLandarea();
        String llocation=currentitem.getLocation();


        holder.landname.setText(lname);
        holder.landlocation.setText(llocation);
        holder.landarea.setText(larea);
        //Picasso.with(mContext).load(imageurl).into(holder.mimageView);




    }

    @Override
    public int getItemCount() {
        return mlanditems.size();
    }

    public class poolViewHolder extends RecyclerView.ViewHolder{
        private TextView landname;
        private TextView landarea;
        private TextView landlocation;
        public poolViewHolder(@NonNull View itemView) {
            super(itemView);
            landname=itemView.findViewById(R.id.mylandname);
            landarea=itemView.findViewById(R.id.mylandarea);
            landlocation=itemView.findViewById(R.id.mylandlocationtxt);




        }
    }

}
