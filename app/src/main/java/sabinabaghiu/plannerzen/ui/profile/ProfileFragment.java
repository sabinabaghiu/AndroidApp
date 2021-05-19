package sabinabaghiu.plannerzen.ui.profile;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.login.LoginViewModel;
import sabinabaghiu.plannerzen.ui.today.Task;
import sabinabaghiu.plannerzen.ui.today.TaskTodayAdapter;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private RecyclerView profileRecyclerView;
    private TextView tasksHeaderTextView, habitsHeaderTextView, noHabitsTextView, headerTextView;
    private HabitProfileAdapter habitProfileAdapter;
    private LoginViewModel loginViewModel;
    private TaskTodayAdapter taskTodayAdapter;
    private PieChart pieChart;
    int countTasksDone = 0, countTasksNotDone = 0;

//    private float[] yData= {64.3f, 35.7f};
//    private String[] xData = {"tasks done", "tasks not done"};

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        profileViewModel.initTask();
        profileViewModel.initHabit();
        tasksHeaderTextView = root.findViewById(R.id.tasks_profile);
        habitsHeaderTextView = root.findViewById(R.id.habits_profile);
        noHabitsTextView = root.findViewById(R.id.textViewNoHabitsProfile);
        headerTextView = root.findViewById(R.id.header_profile);

        AnyChartView pieChartView = root.findViewById(R.id.pieChart);
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        checkIfSignedIn();

        //pie chart

//        pieChart = root.findViewById(R.id.pieChart);
//        setUpPieChart();

        profileViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            Calendar c = new GregorianCalendar();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            Calendar currentDate = new GregorianCalendar(year, month, day);
            Long myDate = currentDate.getTimeInMillis();
            ArrayList<Task> tasksUntilToday = (ArrayList<Task>) tasks.stream().filter(f -> f.getTimestamp() < myDate).collect(Collectors.toList());
            for (Task task : tasksUntilToday) {
                if (task.isDone())
                    countTasksDone++;
                else
                    countTasksNotDone++;

            }
            data.add(new ValueDataEntry("Done", countTasksDone));
            data.add(new ValueDataEntry("Not done", countTasksNotDone));

            pie.data(data);
            pieChartView.setChart(pie);
        });


        //habit recycler view
        profileRecyclerView = root.findViewById(R.id.recyclerViewProfile);
        noHabitsTextView = root.findViewById(R.id.textViewNoHabitsProfile);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        habitProfileAdapter = new HabitProfileAdapter(getContext());
        profileRecyclerView.setAdapter(habitProfileAdapter);
        profileViewModel.getHabits().observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() == 0) {
                profileRecyclerView.setVisibility(View.INVISIBLE);
                noHabitsTextView.setVisibility(View.VISIBLE);
            } else {
                profileRecyclerView.setVisibility(View.VISIBLE);
                noHabitsTextView.setVisibility(View.INVISIBLE);
                habitProfileAdapter.updateList(habits);
            }
        });

        return root;
    }

//    private void setUpPieChart(){
//        pieChart.setHoleRadius(0);
//        pieChart.setRotationEnabled(false);
//        pieChart.setTransparentCircleAlpha(0);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.getLegend().setEnabled(false);
//
//        loadPieChartData();
//    }
//
//    private void loadPieChartData(){
//            //data
//        ArrayList<PieEntry> yEntries = new ArrayList<>();
//        for(int i = 0; i < yData.length; i++) {
//            yEntries.add(new PieEntry(yData[i], i));
//        }
//
//        ArrayList<String> xEntries = new ArrayList<>();
//        for(int i = 1; i < xData.length; i++){
//            xEntries.add(xData[i]);
//        }
//            //data set
//        PieDataSet pieDataSet = new PieDataSet(yEntries, "Was done");
//        ArrayList<Integer> colors = new ArrayList<>();
//        colors.add(ColorTemplate.rgb("C54607"));
//        colors.add(ColorTemplate.rgb("8F0000"));
//        pieDataSet.setColors(colors);
//            //create pie chart
//        PieData pieData = new PieData(pieDataSet);
//        pieChart.setData(pieData);
//        pieChart.invalidate();
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            signOut(getView());
            return true;
        } else return false;
    }

    public void signOut(View view) {
        profileViewModel.signOut();
    }

    private void checkIfSignedIn() {
        loginViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null)
                goToLogIn();
        });
    }

    private void goToLogIn() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_sign_in);
    }

}