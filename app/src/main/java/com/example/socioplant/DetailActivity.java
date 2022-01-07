package com.example.socioplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.socioplant.models.Plant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";
    private ImageButton btnBack, btnNotification;
    private ImageView imgItem;
    private TextView tvName;
    private TextView tvDetail;
    private TextView tvBehavior;
    private String id;
    private String name;
    private String type;
    private String description;
    private String behavior;
    private String photo;
    private String replace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail);

        id = getIntent().getStringExtra("PLANT_ID");
        name = getIntent().getStringExtra("PLANT_NAME");
        type = getIntent().getStringExtra("PLANT_TYPE");
        description = getIntent().getStringExtra("PLANT_DESCRIPTION");
        behavior = getIntent().getStringExtra("PLANT_BEHAVIOR");
        photo = getIntent().getStringExtra("PLANT_PHOTO");
        replace = photo.replace(".png","");

        btnBack = (ImageButton) findViewById(R.id.backButton);
        btnNotification = (ImageButton) findViewById((R.id.notifications));
        imgItem = (ImageView) findViewById(R.id.imageItem);
        tvName = (TextView) findViewById(R.id.nameItem);
        tvDetail = (TextView) findViewById(R.id.detailItem);
        tvBehavior = (TextView) findViewById(R.id.behaviorItem);

        String uri = "@drawable/";
        int imageResource = getResources().getIdentifier(uri + replace, null, getPackageName());
        Drawable myImage = getResources().getDrawable(imageResource);
        imgItem.setImageDrawable(myImage);

        tvName.setText(name);
        tvDetail.setText(description);
        tvBehavior.setText(behavior);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this, "Kamu memilih " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}