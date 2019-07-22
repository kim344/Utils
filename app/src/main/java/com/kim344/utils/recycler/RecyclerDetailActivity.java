package com.kim344.utils.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kim344.utils.R;

public class RecyclerDetailActivity extends AppCompatActivity {

    ImageView mImgDetailDog;
    TextView mTxtDetailDogName;
    TextView mTxtDetailDogAge;
    TextView mTxtDetailDogGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_detail);

        mImgDetailDog = findViewById(R.id.img_detail_dog);
        mTxtDetailDogName = findViewById(R.id.txt_detail_dog_name);
        mTxtDetailDogAge = findViewById(R.id.txt_detail_dog_age);
        mTxtDetailDogGender = findViewById(R.id.txt_detail_dog_gender);

        Intent intent = getIntent();

        int position = intent.getIntExtra("position", 0);
        int image = intent.getIntExtra("img", 0);
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        String gender = intent.getStringExtra("gender");

        mImgDetailDog.setImageResource(image);
        mTxtDetailDogName.setText(name);
        mTxtDetailDogAge.setText(age + "살");
        mTxtDetailDogGender.setText(gender);
        Toast.makeText(RecyclerDetailActivity.this, position + "번째", Toast.LENGTH_SHORT).show();
    }

}
