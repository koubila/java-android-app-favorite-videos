package com.itakademy.mybestyoutube;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.itakademy.mybestyoutube.data.YoutubeVideoDatabase;
import com.itakademy.mybestyoutube.pojos.YoutubeVideo;
public class AddYoutubeVideoActivity extends AppCompatActivity{
    private Spinner spinner;
    private Button addButton;
    private Button cancelButton;
    private EditText edtTitre;
    private EditText edtDescription;
    private EditText edtUrl;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtubevideo);

        // récupère la toolbar et l'affecte comme ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // récupere la action bar et set a true
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        // récupérer les views
        spinner = findViewById(R.id.spinner);
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        edtTitre = findViewById(R.id.edtTitre);
        edtDescription = findViewById(R.id.edtDescription);
        edtUrl = findViewById(R.id.edtUrl);
        // recupere context application
        context = getApplicationContext();

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.array_options,
                // use spinner native or can personalize in a new layout
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        // au clic de ADD alors créer un objet todolist et transmet le texte a main
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // condition sur edittext
                if (edtTitre.getText().length() <= 2 || edtDescription.getText().length() <= 3 || edtUrl.getText().length() <= 3) {
                    // affiche un toast
                    CharSequence text = "Attention caractères minimum insuffisant !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else{

                    // créer l'objet via DataAccessObjet
                    YoutubeVideo youtubeVideo = new YoutubeVideo();
                    youtubeVideo.setTitre(edtTitre.getText().toString());
                    youtubeVideo.setDescription(edtDescription.getText().toString());
                    youtubeVideo.setUrl(edtUrl.getText().toString());
                    youtubeVideo.setCategorie(spinner.getSelectedItem().toString());

                    // use DAO
                    YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().add(youtubeVideo);

                    // affiche un toast
                    CharSequence text = "Votre vidéo est bien ajoutée !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    finish();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onSupportNavigateUp() {
        // ca termine l'activité
        finish();
        return true;
    }
}
