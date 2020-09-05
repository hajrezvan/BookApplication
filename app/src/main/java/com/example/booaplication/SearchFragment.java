package com.example.booaplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booaplication.Adapter.PersonAdapter;
import com.example.booaplication.DataBase.Database;
import com.example.booaplication.Model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private View view;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private EditText editText;
    private PersonAdapter adapter;
    private List<Person> personList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        setupViews();
        setData();
        showBackground();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupViews() {
        linearLayout = view.findViewById(R.id.search_bg);
        recyclerView = view.findViewById(R.id.recyclerView_id);
        editText = view.findViewById(R.id.search_ed_id);
        editText.addTextChangedListener(new Listener());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setData() {
        try {
            Database database = new Database(getActivity());
            personList = database.getAllPerson();
            adapter = new PersonAdapter(getActivity(), personList);
            recyclerView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBackground() {
        recyclerView.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void removeBackground() {
        recyclerView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    public void filter(String text) {
        List<Person> filterList = new ArrayList<>();
        for (Person person : personList) {
            if (person.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(person);
            }
        }
        adapter.filterList(filterList);
    }

    private class Listener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().trim().isEmpty()) {
                showBackground();
            } else {
                removeBackground();
                filter(editable.toString());
            }
        }
    }
}
