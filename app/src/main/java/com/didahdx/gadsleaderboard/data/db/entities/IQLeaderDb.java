package com.didahdx.gadsleaderboard.data.db.entities;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;


@Entity
public class IQLeaderDb {

    @PrimaryKey
    private int id ;
    private String name;
    private int score;
    private String country;
    private String badgeUrl;


    public IQLeaderDb(int id, String name, int score, String country, String badgeUrl) {
        this.id = id;
        this.name = name;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }


    @BindingAdapter({"android:badgeUrl"})
    public static void iqLoadBadge(ImageView view, String badgeUrl) {
        Glide.with(view.getContext())
                .load(badgeUrl)
                .into(view);
    }
}