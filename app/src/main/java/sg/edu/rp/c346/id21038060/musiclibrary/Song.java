package sg.edu.rp.c346.id21038060.musiclibrary;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars){
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() { return id; }
    public String getTitle() { return title;}
    public String getSingers() { return singers;}
    public int getYear() { return year; }
    public int getStar() { return stars; }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSingers(String singers) {
        this.singers = singers;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setStar(int stars) {
        this.stars = stars;
    }



    @NonNull
    @Override
    public String toString() {
        String strstars = "";
        for(int i = 0; i < stars; i++){
            strstars = strstars + "*";
        }

        return title + "\n" + singers + " - " + year + "\n" + strstars;
    }
}
