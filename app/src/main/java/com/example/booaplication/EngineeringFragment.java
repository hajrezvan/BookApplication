package com.example.booaplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booaplication.Adapter.PersonAdapter;
import com.example.booaplication.DataBase.Database;
import com.example.booaplication.Model.Person;

import java.io.IOException;
import java.util.List;

public class EngineeringFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_home, container, false);
        try {
            setupView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupView() throws IOException {
        recyclerView = view.findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Database database = new Database(getActivity());
        List<Person> personList = database.getIranPerson();
        adapter = new PersonAdapter(getActivity(), personList);
        recyclerView.setAdapter(adapter);
    }
}
