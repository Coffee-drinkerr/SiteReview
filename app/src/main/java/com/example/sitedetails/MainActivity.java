package com.example.sitedetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private String site, notes;
    private Spinner spinner;
    private EditText notesET;
    private Button picBtn, goBtn;
    private ImageView imageView;
    private Bitmap bitmap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        notesET=findViewById(R.id.notes);
        picBtn=findViewById(R.id.picBtn);
        goBtn=findViewById(R.id.goBtnid);
        imageView=findViewById(R.id.pic);

        String[] sites = {
                "Store",
                "Mall",
                "Supermarket",
                "Restaurant",
                "Cafe",
                "Cinema",
                "Gym",
                "Park",
                "School",
                "Hospital"
        };
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sites);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                site = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cam, 0);

            }
        });
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes=notesET.getText().toString();
                if(notes.isEmpty()||bitmap==null){
                    Toast.makeText(getApplicationContext(), "Notes is empty or no picture", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, Details.class);
                    intent.putExtra("site", site);
                    intent.putExtra("notes", notes);
                    intent.putExtra("bitmap", bitmap);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0)
            if(resultCode==RESULT_OK){
                bitmap=(Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
    }


}
