package com.example.acorona.petsapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView owner = findViewById(R.id.detail_activity_owner_name);
        TextView number = findViewById(R.id.detail_activity_owner_number);
        TextView description = findViewById(R.id.detail_activity_description_text);
        ImageView image = findViewById(R.id.detail_activity_image);

        Bundle extras = getIntent().getExtras();

        Pet pet=  extras.getParcelable("PET");

        owner.setText("Dueño actual : " + pet.getOwnerName());
        number.setText("Numero del dueño: " + pet.getPhoneNumber());
        description.setText(pet.getDescription());
        image.setImageDrawable(getDrawable(pet.getImageID()));
    }
}
