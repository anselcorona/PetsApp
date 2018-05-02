package com.example.acorona.petsapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.activity_main_toolbar);

        toolbar.setTitle(getString(R.string.app_name));
        RecyclerView petRecycler = findViewById(R.id.activity_main_recyclerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        petRecycler.setLayoutManager(gridLayoutManager);

        ArrayList<Pet> petList = new ArrayList<>();

        petList.add(new Pet("Capitano" , getString(R.string.description), "Susan"        , "800-222-1211", R.drawable.capitano));
        petList.add(new Pet("Catrina"  , getString(R.string.description), "Bad Bunny"    , "800-222-1121", R.drawable.catrina));
        petList.add(new Pet("Husk"     , getString(R.string.description), "Ozuna"        , "800-222-1112", R.drawable.husk));
        petList.add(new Pet("Noodles"  , getString(R.string.description), "Arcangel"     , "800-222-2111", R.drawable.noodles));
        petList.add(new Pet("Pal"      , getString(R.string.description), "Amenazzy"     , "800-222-1311", R.drawable.pal));
        petList.add(new Pet("Pun"      , getString(R.string.description), "Bryant Myers" , "800-222-1131", R.drawable.pun));
        petList.add(new Pet("Sia"      , getString(R.string.description), "Brytiago"     , "800-222-1113", R.drawable.sia));
        petList.add(new Pet("Tut"      , getString(R.string.description), "Farruko"      , "800-222-3111", R.drawable.tut));

        PetAdapter petAdapter = new PetAdapter(this, petList);
        petRecycler.setAdapter(petAdapter);

        petAdapter.setOnPetClickListener(new PetAdapter.OnPetClickListener() {
            @Override
            public void onPetClick(Pet pet) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("PET", pet);
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                startActivity(intent);
            }else{
                startActivity(intent);
            }

            }
        });
    }


}
