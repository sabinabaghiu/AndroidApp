package sabinabaghiu.plannerzen.ui.today;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Habit.class}, version = 1)
public abstract class HabitDatabase extends RoomDatabase {
    private static HabitDatabase instance;

    public abstract HabitDAO habitDAO();

    public static synchronized HabitDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, HabitDatabase.class, "habit_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
