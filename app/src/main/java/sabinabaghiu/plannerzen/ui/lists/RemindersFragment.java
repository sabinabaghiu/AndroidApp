package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class RemindersFragment extends Fragment {

    private ListsViewModel listsViewModel;
    private RecyclerView reminderRecyclerView;
    private TextView reminderTextView;
    ReminderAdapter reminderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reminders, container, false);


//        //todo recycler view
//        reminderRecyclerView = root.findViewById(R.id.recyclerViewReminderMyLists);
//        reminderTextView = root.findViewById(R.id.textViewNoRemindersLists);
//        reminderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        listsViewModel.getAllReminders().observe(getViewLifecycleOwner(), reminders -> {
//            if (!reminders.isEmpty()) {
//                for (Reminder t : reminders) {
//                    reminderRecyclerView.setVisibility(View.VISIBLE);
//                    reminderAdapter = new ReminderAdapter((ArrayList<Reminder>) reminders);
//                    reminderRecyclerView.setAdapter(reminderAdapter);
//                    reminderTextView.setVisibility(View.GONE);
//                }
//            } else {
//                reminderRecyclerView.setVisibility(View.GONE);
//                reminderTextView.setVisibility(View.VISIBLE);
//            }
//        });

        return root;
    }
}
