package sabinabaghiu.plannerzen.ui.today;


public class Todo {
    private int taskId;
    private String title;
    private int time;
    private boolean isImportant;
    private String category;
    private String userId;

    public Todo() {
    }

    public Todo(String userId, int taskId, String title, int time, boolean isImportant, String category) {
        this.taskId = taskId;
        this.title = title;
        this.time = time;
        this.isImportant = isImportant;
        this.category = category;
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
