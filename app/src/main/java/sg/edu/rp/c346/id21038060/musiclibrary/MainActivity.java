package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lvSongs;
    ArrayList<Song> data;
    EditText etSongTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        lvSongs = findViewById(R.id.lvSongs);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);

        // Populate database contents to ListView
        DBHelper db = new DBHelper(MainActivity.this);
        data = db.getSongs();

        ArrayAdapter aaSong = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lvSongs.setAdapter(aaSong);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a song

                // Get selected radio button from radioGroup
                int selectedId = rgStars.getCheckedRadioButtonId();
                int stars = 0;
                // Find the radiobutton by returned id
                if (selectedId == R.id.rbtn1){
                    stars = 1;
                }
                else if (selectedId == R.id.rbtn2){
                    stars = 2;
                }
                else if (selectedId == R.id.rbtn3){
                    stars = 3;
                }
                else if (selectedId == R.id.rbtn4){
                    stars = 4;
                }
                else if (selectedId == R.id.rbtn5){
                    stars = 5;
                }

                db.insertSong(etSongTitle.getText().toString(), etSingers.getText().toString(), Integer.parseInt(String.valueOf(etYear.getText())), stars);

                etSongTitle.setText("");
                etSingers.setText("");
                etYear.setText("");
                rgStars.clearCheck();
                data.clear();
                data.addAll(db.getSongs());
                aaSong.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Song added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}