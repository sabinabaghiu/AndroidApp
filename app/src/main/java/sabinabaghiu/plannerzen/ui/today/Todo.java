package sabinabaghiu.plannerzen.ui.today;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.GregorianCalendar;

public class Todo {
    private String title;
    private String description;
    private int time;
    private boolean isImportant;
    private String category;
    private LocalDate date;
    private int day;
    private int month;
    private int year;
    private int icon;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Todo(String title, String description, int time, boolean isImportant, String category, LocalDate date, int day, int month, int year) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.isImportant = isImportant;
        this.category = category;
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = LocalDate.of(year, month, day);
    }

    public Todo(String title, int time, boolean isImportant)
    {
        this.title = title;
        this.time = time;
        this.isImportant = isImportant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
