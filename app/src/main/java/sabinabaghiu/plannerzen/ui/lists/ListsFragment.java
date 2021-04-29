package sabinabaghiu.plannerzen.ui.lists;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import sabinabaghiu.plannerzen.R;

public class ListsFragment extends Fragment  {

    private ListsViewModel listsViewModel;
    TabLayout tabLayout;
    ViewPager viewPager;
    View fragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_lists, container, false);

        viewPager = fragment.findViewById(R.id.viewPager);
        tabLayout = fragment.findViewById(R.id.tabLayout);

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new TodosFragment(), "TO DO LISTS");
        adapter.addFragment(new HabitsFragment(), "HABITS");
        adapter.addFragment(new RemindersFragment(), "REMINDERS");
        viewPager.setAdapter(adapter);
    }


//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        listsViewModel = new ViewModelProvider(this).get(ListsViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_lists, container, false);
//
//
//        //maybe fab implementation here
//
//        FloatingActionButton fab = root.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        viewPager = root.findViewById(R.id.viewpager);
//        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
//        viewPager.setAdapter(viewPagerAdapter);
//        tabLayout = root.findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        return root;
}