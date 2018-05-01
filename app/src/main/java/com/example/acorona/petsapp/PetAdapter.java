package com.example.acorona.petsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    private Context context;
    private  ArrayList<Pet> petList;
    private OnPetClickListener onPetClickListener;

    public interface OnPetClickListener{
        void onPetClick(Pet pet);
    }
    public void setOnPetClickListener(OnPetClickListener onPetClickListener){
        this.onPetClickListener = onPetClickListener;
    }

    public PetAdapter(Context context, ArrayList<Pet> petList){
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pet_list_item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull PetAdapter.ViewHolder holder, int position) {
        final Pet pet = petList.get(position);

        holder.nameTextView.setText(pet.getNombre());
        holder.ownerTextView.setText(pet.getOwnerName());
        holder.petImageView.setImageDrawable(ContextCompat.getDrawable(context, pet.getImageID()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPetClickListener.onPetClick(pet);
            }
        });

    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView ownerTextView;
        public ImageView petImageView;

        public ViewHolder(View itemView){
            super(itemView);
            petImageView = itemView.findViewById(R.id.pet_list_item_image);
            nameTextView = itemView.findViewById(R.id.pet_list_item_name);
            ownerTextView = itemView.findViewById(R.id.pet_list_item_owner);
        }
    }
}
