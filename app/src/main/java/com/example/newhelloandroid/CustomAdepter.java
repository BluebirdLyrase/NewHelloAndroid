package com.example.newhelloandroid;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
public class CustomAdepter  extends RecyclerView.Adapter<CustomAdepter.MyViewHolder>{
        private Context mContext;
        private String[] mImage;
        private String[] mTitle;
        private String[] msubTitle;
        private OnNoteListener mOnNoteListener;

        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView title;
            TextView subtitle;
            ImageView imgView;
            OnNoteListener onNoteListener;

            public MyViewHolder(View itemView,OnNoteListener onNoteListener) {
                super(itemView);
                this.title = (TextView) itemView.findViewById(R.id.title);
                this.subtitle = (TextView) itemView.findViewById(R.id.subtitle);
                this.imgView = (ImageView) itemView.findViewById(R.id.imgcar);
                this. onNoteListener = onNoteListener ;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                onNoteListener.onNoteClick(getAdapterPosition());
            }

//            public interface OnCardListener

        }

        public CustomAdepter(Context mContext, String[] image,String[] title,String[] subTitle,OnNoteListener onNoteListener) {
            this.mContext = mContext;
            this.mImage = image;
            this.mTitle = title;
            this.msubTitle = subTitle;
            this.mOnNoteListener = onNoteListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view,mOnNoteListener);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder (final MyViewHolder holder, final int i) {
            holder.title.setText(mTitle[i]);
            holder.subtitle.setText(msubTitle[i]);
            Picasso.with(mContext).load(mImage[i]).into(holder.imgView);
        }
        @Override
        public int getItemCount() {
            return mTitle.length;
        }

        public interface OnNoteListener{
        void onNoteClick(int position);

    }

    }



