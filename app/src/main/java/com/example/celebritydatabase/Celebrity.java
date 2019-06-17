package com.example.celebritydatabase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "celebrities")
public class Celebrity implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "First Name")
    private String firstName;//Primary Key

    @ColumnInfo(name = " Last Name")
    private String lastName;

    @ColumnInfo(name = "Profession")
    private String profession;

    @ColumnInfo(name = "Favorite")
    private boolean favorite = false;

    public Celebrity(@NonNull String firstName, String lastName, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
    }

    protected Celebrity(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        profession = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(profession);
        parcel.writeByte((byte) (favorite ? 1 : 0));
    }
}
