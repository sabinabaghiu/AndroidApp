package sabinabaghiu.plannerzen.ui.lists;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import sabinabaghiu.plannerzen.ui.lists.Reminder;

@Dao
public interface ReminderDAO {

    @Insert
    void insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Query("DELETE FROM reminder_table")
    void deleteAllReminders();

    @Query("SELECT * FROM reminder_table")
    LiveData<List<Reminder>> getAllReminders();
}
