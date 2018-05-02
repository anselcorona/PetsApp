package com.example.acorona.petsapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.Manifest.permission.CALL_PHONE;

public class DetailActivity extends AppCompatActivity {

    private static final int CALL_PHONE_REQUEST_CODE = 0;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.detail_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView owner = findViewById(R.id.detail_activity_owner_name);
        TextView number = findViewById(R.id.detail_activity_owner_number);
        TextView description = findViewById(R.id.detail_activity_description_text);
        ImageView image = findViewById(R.id.detail_activity_image);

        Bundle extras = getIntent().getExtras();

        final Pet pet = extras.getParcelable("PET");

        if (pet != null) {
            phoneNumber = pet.getPhoneNumber();
            owner.setText("Dueño actual : " + pet.getOwnerName());
            number.setText("Numero del dueño: " + phoneNumber);
            description.setText(pet.getDescription());
            image.setImageDrawable(getDrawable(pet.getImageID()));

            CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitle(pet.getNombre());

            FloatingActionButton fab = findViewById(R.id.detail_activity_floating_action_button);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeCall();
                }
            });
        }
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
       if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
           if(checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
               startActivity(intent);
           }else{
               final String[] permissions = new String[]{CALL_PHONE};
               requestPermissions(permissions, CALL_PHONE_REQUEST_CODE);
           }
       }else{
           startActivity(intent);
       }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CALL_PHONE_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makeCall();
            } else if(shouldShowRequestPermissionRationale(CALL_PHONE)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Hacer Llamadas");
                builder.setMessage("Debes aceptar este permiso para hacer llamadas desde la app");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String[] permissions = new String[]{CALL_PHONE};
                        requestPermissions(permissions, CALL_PHONE_REQUEST_CODE);
                    }
                });
                builder.show();
            }
        }
    }
}
