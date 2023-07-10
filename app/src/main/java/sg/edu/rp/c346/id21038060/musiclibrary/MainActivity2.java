package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btnFiveStars;
    Spinner spnYear;
    ListView lvSongs;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    Boolean ignoreCallback = false;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(MainActivity2.this);
        al.clear();
        al.addAll(db.getSongs());
        aa.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("NDP Songs List");

        btnFiveStars = findViewById(R.id.btnFiveStars);
        spnYear = findViewById(R.id.spnYear);
        lvSongs = findViewById(R.id.lvSongs);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
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
                ignoreCallback = true;
                spnYear.setSelection(0);
                ignoreCallback = false;
                al.clear();
                al.addAll(db.getAllSongsFilterByStars(5));
                aa.notifyDataSetChanged();
            }
        });

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!ignoreCallback) {
                    if (i == 0) { //All Years
                        onResume();
                    } else {
                        al.clear();
                        al.addAll(db.getAllSongsFilterByYear(Integer.parseInt(spnYear.getSelectedItem().toString())));
                        aa.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}