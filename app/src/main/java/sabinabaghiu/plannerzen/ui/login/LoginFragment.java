package sabinabaghiu.plannerzen.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

import sabinabaghiu.plannerzen.R;

public class LoginFragment extends Fragment {

    private static final int RC_SIGN_IN = 42;
    private static final int RESULT_OK = -1;
    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);

        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.GONE);

        checkIfSignedIn();

        Button signInButton = root.findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });
        return root;
    }

    private void checkIfSignedIn() {
        loginViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                goToMainFragment();
            }
        });
    }

    private void goToMainFragment() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_today);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.VISIBLE);
    }



    public void signIn(View v) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo1)
                .setTheme(R.style.Theme_PlannerZen)
                .build();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();
            goToMainFragment();
        }
        else
            Toast.makeText(getContext(), "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }
}
