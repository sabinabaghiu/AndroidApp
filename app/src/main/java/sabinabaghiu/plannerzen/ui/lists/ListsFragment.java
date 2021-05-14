package sabinabaghiu.plannerzen.ui.lists;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


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

        adapter.addFragment(new TasksFragment(), "TASKS");
        adapter.addFragment(new HabitsFragment(), "HABITS");
        viewPager.setAdapter(adapter);
    }

}