package sabinabaghiu.plannerzen.ui.lists;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "reminder_table")
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String date;
    private boolean isOn;
    private String repeat;

    public Reminder(String title, String date, boolean isOn, String repeat) {
        this.title = title;
        this.date = date;
        this.isOn = isOn;
        this.repeat = repeat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
}
