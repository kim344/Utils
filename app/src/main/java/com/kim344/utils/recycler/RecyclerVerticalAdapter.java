package com.kim344.utils.recycler;

import android.annotation.SuppressLint;
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

public class RecyclerVerticalAdapter extends RecyclerView.Adapter<RecyclerVerticalAdapter.ViewHolder> {

    private Context mContext;
    private List<RecyclerVerticalModel> mDogList;
    private int mItemLayout;

    public RecyclerVerticalAdapter(Context mContext, List<RecyclerVerticalModel> mDogList, int mItemLayout){
        this.mContext = mContext;
        this.mDogList = mDogList;
        this.mItemLayout = mItemLayout;
    }

    @NonNull
    @Override
    public RecyclerVerticalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mItemLayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final RecyclerVerticalModel item = mDogList.get(position);
        viewHolder.dogImage.setImageResource(item.getDogImage());
        viewHolder.dogName.setText("이름 : " + item.getDogName());
        viewHolder.dogGender.setText(" / 성별 : " + item.getDogGender());
        viewHolder.dogAge.setText("나이 : " + String.valueOf(item.getDogAge()+"살"));

        viewHolder.itemVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RecyclerDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("img",item.getDogImage());
                intent.putExtra("name",item.getDogName());
                intent.putExtra("age",item.getDogAge());
                intent.putExtra("gender",item.getDogGender());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDogList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout itemVertical;
        ImageView dogImage;
        TextView dogName;
        TextView dogAge;
        TextView dogGender;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemVertical = itemView.findViewById(R.id.item_vertical);
            dogImage = itemView.findViewById(R.id.img_dog_image);
            dogName = itemView.findViewById(R.id.txt_dog_name);
            dogAge = itemView.findViewById(R.id.txt_dog_age);
            dogGender = itemView.findViewById(R.id.txt_dog_gender);
        }
    }
}