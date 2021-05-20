package sabinabaghiu.plannerzen.ui.today;


import java.util.Calendar;
import com.google.firebase.database.Exclude;

public class Task implements Comparable<Task>{
    private String title;
    private Calendar date;
    private String time;
    private long timestamp;
    private boolean isImportant;
    private boolean isDone;
    private String id;

    public Task() {
    }

    public Task(String title, String time, boolean isImportant, Calendar date, boolean isDone) {
        this.title = title;
        this.time = time;
        this.isImportant = isImportant;
        this.date = date;
        this.isDone = false;
        timestamp = date.getTimeInMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Exclude
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    @Override
    public int compareTo(Task task) {
        if (getDate() == null || task.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(task.getDate());
    }
}
