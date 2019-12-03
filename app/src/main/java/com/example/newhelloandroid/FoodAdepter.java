package com.example.newhelloandroid;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FoodAdepter extends RecyclerView.Adapter<FoodAdepter.MyViewHolder>{
private String[] mfoodName;
private String[] mfoodPrice;

public static class MyViewHolder extends RecyclerView.ViewHolder{
    TextView foodName;
    TextView foodPrice;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.foodName = (TextView) itemView.findViewById(R.id.foodName);
        this.foodPrice = (TextView) itemView.findViewById(R.id.foodPrice);
    }


//            public interface OnCardListener

}

    public FoodAdepter(String[] mfoodName,String[] mfoodPrice) {
        this.mfoodName = mfoodName;
        this.mfoodPrice = mfoodPrice;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlayout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder (final MyViewHolder holder, final int i) {
        holder.foodName.setText(mfoodName[i]);
        holder.foodPrice.setText("price : " + mfoodPrice[i]+" USD");
    }
    @Override
    public int getItemCount() {
        return mfoodName.length;
    }


}