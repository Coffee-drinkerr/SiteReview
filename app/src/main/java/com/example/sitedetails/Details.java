package com.example.sitedetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Details extends AppCompatActivity {
    private TextView sitetv, notestv;
    private String site, notes;
    private Bitmap bitmap;
    private ImageView pic;
    private Button goBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        sitetv = findViewById(R.id.sitetv);
        notestv = findViewById(R.id.notestv);
        pic = findViewById(R.id.pic);
        goBtn = findViewById(R.id.goBtn);



        site = getIntent().getStringExtra("site");
        notes = getIntent().getStringExtra("notes");
        bitmap = getIntent().getParcelableExtra("bitmap");



        sitetv.setText(site);
        notestv.setText(notes);
        pic.setImageBitmap(bitmap);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}