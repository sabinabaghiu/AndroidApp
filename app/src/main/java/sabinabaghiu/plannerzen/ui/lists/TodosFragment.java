package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import sabinabaghiu.plannerzen.R;

public class TodosFragment extends Fragment {

//    public TodosFragment() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.fragment_todos, container, false);
//        return view;
//    }

    private ListsViewModel listsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todos, container, false);
        return root;
    }
}
