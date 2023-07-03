package sg.edu.rp.c346.id21038060.musiclibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    //Start version with 1
    //increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "Song.db";

    private static final String TABLE_SONG = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";

        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);
    }

    public void insertSong(String title, String singers, int year, int stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_SONG
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();
    }

    //Get Database Content via ArrayList directly
    public ArrayList<String> getSongContent() {
        // Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                // Add the song content to the ArrayList object
                //  getString(0) retrieves first column data
                //  getString(1) return second column data
                //  getInt(0) if data is an integer value
                //songs.add(cursor.getString(0));
                songs.add(cursor.getString(1));
                songs.add(cursor.getString(2));
                songs.add(cursor.getString(3));
                songs.add(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return songs;
    }

    //Get Database Content via Song object
    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, COLUMN_TITLE+" ASC", null);

        if (cursor.moveToFirst()) {
            do {
                //int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song obj = new Song(description, date, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}
