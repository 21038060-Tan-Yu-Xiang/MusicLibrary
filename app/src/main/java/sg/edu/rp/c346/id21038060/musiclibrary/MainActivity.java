package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnView;
    EditText etSongTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Add New NDP Song");

        btnAdd = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnDelete);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);

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
                Toast.makeText(MainActivity.this, "Song added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}