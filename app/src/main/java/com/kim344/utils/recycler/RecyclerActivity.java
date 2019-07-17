package com.kim344.utils.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kim344.utils.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewHorizontal;
    RecyclerView mRecyclerViewVertical;
    List<RecyclerVerticalModel> mVerticalDogList;
    List<RecyclerHorizontalModel> mHorizontalDogList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mRecyclerViewVertical = findViewById(R.id.recycler_vertical);
        mRecyclerViewHorizontal = findViewById(R.id.recycler_horizontal);

        mVerticalDogList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mVerticalDogList.add(new RecyclerVerticalModel(R.drawable.dog00, "갈색이", 5, "수컷"));
            mVerticalDogList.add(new RecyclerVerticalModel(R.drawable.dog01, "회색이", 10, "암컷"));
            mVerticalDogList.add(new RecyclerVerticalModel(R.drawable.dog02, "흰둥이", 8, "수컷"));
            mVerticalDogList.add(new RecyclerVerticalModel(R.drawable.dog03, "분홍이", 11, "수컷"));
            mVerticalDogList.add(new RecyclerVerticalModel(R.drawable.dog04, "두더지", 3, "암컷"));
            mVerticalDogList.add(new RecyclerVerticalModel(R.drawable.dog05, "누렁이", 1, "수컷"));
        }

        //세로 Recycler Setting
        mRecyclerViewVertical.setAdapter(new RecyclerVerticalAdapter(RecyclerActivity.this, mVerticalDogList, R.layout.recycler_vertical_item));
        mRecyclerViewVertical.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
        mRecyclerViewVertical.setItemAnimator(new DefaultItemAnimator());

        mHorizontalDogList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mHorizontalDogList.add(new RecyclerHorizontalModel(R.drawable.dog00, "갈색이"));
            mHorizontalDogList.add(new RecyclerHorizontalModel(R.drawable.dog01, "회색이"));
            mHorizontalDogList.add(new RecyclerHorizontalModel(R.drawable.dog02, "흰둥이"));
            mHorizontalDogList.add(new RecyclerHorizontalModel(R.drawable.dog03, "분홍이"));
            mHorizontalDogList.add(new RecyclerHorizontalModel(R.drawable.dog04, "두더지"));
            mHorizontalDogList.add(new RecyclerHorizontalModel(R.drawable.dog05, "누렁이"));
        }

        //가로 Recycler Setting
        mRecyclerViewHorizontal.setAdapter(new RecyclerHorizontalAdapter(RecyclerActivity.this, mHorizontalDogList, R.layout.recycler_horizontal_item));
        mRecyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
    }
}
