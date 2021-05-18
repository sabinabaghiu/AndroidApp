package sabinabaghiu.plannerzen.ui.lists;

import sabinabaghiu.plannerzen.ui.today.Task;

public class DateOrTask {
    private Task task;
    private String date;
    private boolean isTask;

    public static DateOrTask createDate(String date){
        DateOrTask created = new DateOrTask();
        created.date = date;
        created.isTask = false;
        return  created;
    }

    public static DateOrTask createTask(Task task){
        DateOrTask created = new DateOrTask();
        created.task = task;
        created.isTask = true;
        return  created;
    }

    public Task getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public boolean isTask() {
        return isTask;
    }
}
