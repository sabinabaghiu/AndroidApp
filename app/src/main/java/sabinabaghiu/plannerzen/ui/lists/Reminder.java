package sabinabaghiu.plannerzen.ui.lists;

import java.util.Date;

public class Reminder {
    private String title;
    private Date date;
    private boolean isOn;
    private String repeat;

    public Reminder(String title, Date date, boolean isOn, String repeat) {
        this.title = title;
        this.date = date;
        this.isOn = isOn;
        this.repeat = repeat;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
