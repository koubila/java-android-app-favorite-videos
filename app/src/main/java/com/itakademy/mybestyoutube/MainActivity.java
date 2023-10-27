package com.itakademy.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.itakademy.mybestyoutube.adapter.YoutubeVideoAdapter;
import com.itakademy.mybestyoutube.data.YoutubeVideoDatabase;
import com.itakademy.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    public static final String KEY_ITEM = "item";

    private int count = 0;
    private RecyclerView rvYoutubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        // récupère la toolbar et l'affecte comme ActionBar
        //Toolbar toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getApplicationContext();

        // recupere le RecyclerView
        rvYoutubeVideo = findViewById(R.id.rvYoutubeVideo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvYoutubeVideo.setHasFixedSize(true);
        rvYoutubeVideo.setLayoutManager(layoutManager);

    }



    @Override
    protected void onStart() {
        super.onStart();

        //utilisation de AsyncTask
        TodoAsyncTask todoAsyncTask = new TodoAsyncTask();
        todoAsyncTask.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu_main);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Intention to start another activity
        if (item.getItemId() == R.id.youtubeVideo){
            // instancier une intention avec parametre
            Intent intent=new Intent(this,AddYoutubeVideoActivity.class);

            // class Question a été serialiser car putExtra prend en argument string
            // on ajoute à intent les donnée souhaiter
            //intent.putExtra(KEY_ITEM, youtubeVideo.get(index));
            // démarre activité
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TodoAsyncTask extends AsyncTask<Nullable, Nullable, List<YoutubeVideo>> {

        // 4 étapes: OnPreExecute(), doInBackground(), onProgressUpdate() et onPostExecute()

        @Override
        protected  List<YoutubeVideo> doInBackground(Nullable... nullables) {

            List<YoutubeVideo> todos = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().list();
            return todos;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> youtubeVideos) {

            /*StringBuilder sb = new StringBuilder();

            for (Todo todo : todos){
                sb.append(String.format("%s // %s\n", todo.getName(), todo.getUrgency()));
            }

            tvText.setText(sb.toString());*/

            // avec le receiverview
            // créé l'adapter en lui passant la liste
            YoutubeVideoAdapter todoAdapter = new YoutubeVideoAdapter(youtubeVideos, new YoutubeVideoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(YoutubeVideo youtubeVideo) {

                    Toast.makeText(context, "Vous avez cliqué sur une vidéo", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, DetailActivity.class);

                    intent.putExtra(KEY_ITEM, youtubeVideo);
                    startActivity(intent);
                }
            });
            rvYoutubeVideo.setAdapter(todoAdapter);
        }

    }
}