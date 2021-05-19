package sabinabaghiu.plannerzen.ui.lists;

import java.util.Calendar;

import sabinabaghiu.plannerzen.ui.today.Task;

public class DateOrTask {
    private Task task;
    private Calendar date;
    private boolean isTask;

    public static DateOrTask createDate(Calendar date){
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

    public Calendar getDate() {
        return date;
    }

    public boolean isTask() {
        return isTask;
    }
}
