package sabinabaghiu.plannerzen.ui.lists;

import java.util.ArrayList;
import java.util.Calendar;

public class TaskList {
    private String listId;
    private String title;
    private String description;
    private Calendar date;
    private ArrayList<String> taskIds;
    private String userId;

    public TaskList() {
    }

    public TaskList(String userId, String listId, String title, String description, Calendar date, ArrayList<String> taskIds) {
        this.listId = listId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.taskIds = taskIds;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public ArrayList<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(ArrayList<String> taskIds) {
        this.taskIds = taskIds;
    }
}
