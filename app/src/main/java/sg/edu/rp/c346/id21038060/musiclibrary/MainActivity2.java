package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btnFiveStars;
    ListView lvSongs;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("NDP Songs List");

        btnFiveStars = findViewById(R.id.btnFiveStars);
        lvSongs = findViewById(R.id.lvSongs);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lvSongs.setAdapter(aa);

        DBHelper db = new DBHelper(MainActivity2.this);

        al.clear();
        al.addAll(db.getSongs());
        aa.notifyDataSetChanged();

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(MainActivity2.this,
                        MainActivity3.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}