package sabinabaghiu.plannerzen.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.login.LoginViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private RecyclerView profileRecyclerView;
    private TextView username, profileTextView;
    HabitProfileAdapter habitProfileAdapter;
    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        profileViewModel.initHabit();
        username = root.findViewById(R.id.username);
        username.setText(profileViewModel.getCurrentUser().getValue().getDisplayName());

        checkIfSignedIn();

            //habit recycler view
        profileRecyclerView = root.findViewById(R.id.recyclerViewProfile);
        profileTextView = root.findViewById(R.id.textViewNoHabitsProfile);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        habitProfileAdapter = new HabitProfileAdapter(getContext());
        profileRecyclerView.setAdapter(habitProfileAdapter);
        profileViewModel.getHabits().observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() == 0) {
                profileRecyclerView.setVisibility(View.INVISIBLE);
                profileTextView.setVisibility(View.VISIBLE);
            }
            else {
                profileRecyclerView.setVisibility(View.VISIBLE);
                profileTextView.setVisibility(View.INVISIBLE);
                habitProfileAdapter.updateList(habits);
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout){
            signOut(getView());
            return true;
        } else return false;
    }

    public void signOut(View view){
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