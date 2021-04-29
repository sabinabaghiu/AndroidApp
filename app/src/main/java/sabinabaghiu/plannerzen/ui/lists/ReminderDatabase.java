package sabinabaghiu.plannerzen.ui.lists;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reminder.class}, version = 1)
public abstract class ReminderDatabase extends RoomDatabase {
    private static ReminderDatabase instance;

    public abstract ReminderDAO reminderDAO();

    public static synchronized ReminderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ReminderDatabase.class, "reminder_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
