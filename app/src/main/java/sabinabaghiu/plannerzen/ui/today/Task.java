package sabinabaghiu.plannerzen.ui.today;


public class Task {
    private String title;
    private String date;
    private int time;
    private boolean isImportant;
    private boolean isDone;

    public Task() {
    }

    public Task(String title, int time, boolean isImportant, String date, boolean isDone) {
        this.title = title;
        this.time = time;
        this.isImportant = isImportant;
        this.date = date;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
