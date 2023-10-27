package com.itakademy.mybestyoutube.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.io.Serializable;
import java.util.List;

@Entity
public class YoutubeVideo implements Serializable {
    // declare id (long), titre (String), description
    //(String), url (String), categorie (String), favori (int)

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "titre")
    private String titre;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "categorie")
    private String categorie;
    @ColumnInfo(name = "favori")
    private int favori;

    public YoutubeVideo(String titre, String description, String url, String categorie, int favori) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
        this.favori = favori;
    }

    public YoutubeVideo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }

    @Override
    public String toString() {
        return "YoutubeVideo{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", categorie='" + categorie + '\'' +
                ", favori=" + favori +
                '}';
    }
}
