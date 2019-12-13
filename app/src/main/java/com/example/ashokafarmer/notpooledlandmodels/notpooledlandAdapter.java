package com.example.ashokafarmer.notpooledlandmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashokafarmer.R;

import java.util.ArrayList;

public class notpooledlandAdapter extends RecyclerView.Adapter<notpooledlandAdapter.poolViewHolder> {

    private Context mContext;
    private ArrayList<notpooleditem> mnotpoollandlist;
    private onitemclicklistener mListener;

    public interface onitemclicklistener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(onitemclicklistener listener){
        mListener=listener;
    }

    public notpooledlandAdapter(Context context, ArrayList<notpooleditem> poollist)
    {
        mContext=context;
        mnotpoollandlist=poollist;

    }

    @NonNull
    @Override
    public poolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.pendinglistitem,parent,false);
        return new poolViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull poolViewHolder holder, int position) {

        notpooleditem currentitem=mnotpoollandlist.get(position);
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
        return mnotpoollandlist.size();
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }

}
