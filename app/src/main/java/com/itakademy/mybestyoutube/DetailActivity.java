package com.itakademy.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.itakademy.mybestyoutube.pojos.YoutubeVideo;

public class DetailActivity extends AppCompatActivity {

    private TextView titre;
    private TextView description;
    private TextView url;
    private TextView categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // récupere la action bar et set a true
        //ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);

        titre = findViewById(R.id.tvdDetailTitre);
        description = findViewById(R.id.tvDetailDescription);
        url = findViewById(R.id.tvDetailUrl);
        categorie = findViewById(R.id.tvDetailCategorie);

        Intent intent = getIntent();
        YoutubeVideo youtubeVideo = (YoutubeVideo) intent.getSerializableExtra(MainActivity.KEY_ITEM);

        titre.setText(String.format("%s : %s", youtubeVideo.getTitre()));
        description.setText(String.format("%s : %s", youtubeVideo.getDescription()));
        url.setText(String.format("%s : %s", youtubeVideo.getUrl()));
        categorie.setText(String.format("%s : %s", youtubeVideo.getCategorie()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        // ca termine l'activité
        finish();
        return true;
    }
}