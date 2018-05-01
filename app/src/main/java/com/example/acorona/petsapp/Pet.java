package com.example.acorona.petsapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {
    private String nombre;
    private String description;
    private String ownerName;
    private String phoneNumber;
    private int imageID;

    public Pet(String nombre, String description, String ownerName, String phoneNumber, int imageID) {
        this.nombre = nombre;
        this.description = description;
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.imageID = imageID;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDescription() {
        return description;
    }


    public String getOwnerName() {
        return ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getImageID() {
        return imageID;
    }


    protected Pet(Parcel in) {
        nombre = in.readString();
        description = in.readString();
        ownerName = in.readString();
        phoneNumber = in.readString();
        imageID = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(description);
        dest.writeString(ownerName);
        dest.writeString(phoneNumber);
        dest.writeInt(imageID);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };
}