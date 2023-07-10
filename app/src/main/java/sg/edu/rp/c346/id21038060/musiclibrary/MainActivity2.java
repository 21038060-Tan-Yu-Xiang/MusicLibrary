package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btnFiveStars;
    ListView lvSongs;
    ArrayList<Song> data;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("NDP Songs List");

        btnFiveStars = findViewById(R.id.btnFiveStars);
        lvSongs = findViewById(R.id.lvSongs);

        // Populate database contents to ListView
        db = new DBHelper(MainActivity2.this);
        data = db.getSongs();

        ArrayAdapter aaSong = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lvSongs.setAdapter(aaSong);

        data.clear();
        data.addAll(db.getSongs());
        aaSong.notifyDataSetChanged();

        btnFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}