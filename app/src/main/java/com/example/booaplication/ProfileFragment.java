package com.example.booaplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booaplication.Adapter.FavAdapter;
import com.example.booaplication.Adapter.PersonAdapter;
import com.example.booaplication.DataBase.Database;
import com.example.booaplication.Model.Person;

import java.io.IOException;
import java.util.List;

public class ProfileFragment extends Fragment {

    private View view;
    private TextView name;
    private String username;
    private RecyclerView recyclerView;
    private FavAdapter favAdapter;
    private LinearLayout linearLayout;
    public static final String LOGIN = "Login";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        setupViews();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("CutPasteId")
    public void setupViews() {
        name = view.findViewById(R.id.username_profile_id);
        recyclerView = view.findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        linearLayout = (LinearLayout) view.findViewById(R.id.profile_bg_id);
        Database database = null;
        try {
            database = new Database(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert database != null;
        List<Person> personList = database.getFavPerson();
        if (personList.isEmpty()) {
            showBackground();
        } else {
            removeBackground();
        }
        favAdapter = new FavAdapter(getActivity(), personList);
        recyclerView.setAdapter(favAdapter);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("NAME", null);
        name.setText(username);
    }

    public void showBackground() {
        recyclerView.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void removeBackground() {
        recyclerView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

}
