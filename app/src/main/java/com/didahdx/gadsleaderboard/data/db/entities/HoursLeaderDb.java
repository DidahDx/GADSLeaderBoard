package com.didahdx.gadsleaderboard.data.db.entities;

import android.widget.ImageView;

import androidx.annotation.Keep;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

@Keep
@Entity
public class HoursLeaderDb {

    @PrimaryKey
    private int id;
    private String name;
    private int hours;
    private String country;
    private String badgeUrl;


    public HoursLeaderDb(int id, String name, int hours, String country, String badgeUrl) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    @BindingAdapter({"android:hourBadgeUrl"})
    public static void hourLoadBadge(ImageView view, String badgeUrl) {
        Glide.with(view.getContext())
                .load(badgeUrl)
                .into(view);
    }
}