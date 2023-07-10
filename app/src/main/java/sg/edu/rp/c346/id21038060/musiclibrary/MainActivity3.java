package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity3 extends AppCompatActivity {

    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    EditText etSongId;
    EditText etSongTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setTitle("Edit/Delete NDP Song");

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etSongId = findViewById(R.id.etSongId);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);

        DBHelper db = new DBHelper(MainActivity3.this);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etSongId.setText(String.valueOf(data.getId()));
        etSongTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));
        int starCount = data.getStar();
        if (starCount == 1){
            rbtn1.setChecked(true);
        }
        else if (starCount == 2){
            rbtn2.setChecked(true);
        }
        else if (starCount == 3){
            rbtn3.setChecked(true);
        }
        else if (starCount == 4){
            rbtn4.setChecked(true);
        }
        else if (starCount == 5){
            rbtn5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                data.setTitle(etSongTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

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
                data.setStar(stars);
                db.updateNote(data);
                db.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db.deleteNote(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}