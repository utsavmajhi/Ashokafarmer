package com.example.ashokafarmer.pendingrequestsmodels;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashokafarmer.R;

import java.util.ArrayList;

public class pendingrequestAdapter extends RecyclerView.Adapter<pendingrequestAdapter.MyViewHolder>{

    Context mContext;
    private ArrayList<pendingrequestitem> mpendingitemlist;


    public pendingrequestAdapter(Context mContext, ArrayList<pendingrequestitem> mpendingitemlist) {
        this.mContext = mContext;
        this.mpendingitemlist = mpendingitemlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.pendinglistitem,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.pendlandname.setText(mpendingitemlist.get(position).getLandname());
        holder.pendlandarea.setText(mpendingitemlist.get(position).getLandarea());
        holder.pendlandlocation.setText(mpendingitemlist.get(position).getLandlocation());
    }

    @Override
    public int getItemCount()

    {
        return mpendingitemlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private Context context;

        public TextView pendlandname;
        public TextView pendlandarea;
        public TextView pendlandlocation;
        public TextView pendlandlat;
        public TextView pendlandlong;
        //public TextView mextraattribute;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            pendlandname=itemView.findViewById(R.id.mylandname);
            pendlandarea=itemView.findViewById(R.id.mylandarea);
            pendlandlocation=itemView.findViewById(R.id.mylandlocationtxt);


        }
    }

}