package com.kim344.utils.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kim344.utils.R;

import java.util.List;

public class RecyclerHorizontalAdapter extends RecyclerView.Adapter<RecyclerHorizontalAdapter.ViewHolder>{

    private Context mContext;
    private List<RecyclerHorizontalModel> mDogList;
    private int mItemLayout;

    public RecyclerHorizontalAdapter(Context mContext, List<RecyclerHorizontalModel> mDogList, int mItemLayout){
        this.mContext = mContext;
        this.mDogList = mDogList;
        this.mItemLayout = mItemLayout;
    }

    @NonNull
    @Override
    public RecyclerHorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mItemLayout,viewGroup,false);
        return new RecyclerHorizontalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHorizontalAdapter.ViewHolder viewHolder, final int position) {
        final RecyclerHorizontalModel item = mDogList.get(position);
        viewHolder.dogImage.setImageResource(item.getDogImage());
        viewHolder.dogName.setText("이름 : " + item.getDogName());

        viewHolder.itemHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RecyclerDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("img",item.getDogImage());
                intent.putExtra("name",item.getDogName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout itemHorizontal;
        ImageView dogImage;
        TextView dogName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemHorizontal = itemView.findViewById(R.id.item_horizontal);
            dogImage = itemView.findViewById(R.id.img_horizontal_dog_image);
            dogName = itemView.findViewById(R.id.txt_horizontal_dog_name);
        }
    }
}
