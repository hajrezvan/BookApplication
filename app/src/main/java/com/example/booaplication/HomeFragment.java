package com.example.booaplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.example.booaplication.Adapter.HomeAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private RtlViewPager viewPager;
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        setupView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_id);
        viewPager = (RtlViewPager) view.findViewById(R.id.view_pager_id);
        adapter = new HomeAdapter(getChildFragmentManager());
        IranFragment fragment = new IranFragment();
        KharejFragment fragment1 = new KharejFragment();
        adapter.addFragment(fragment,"ایرانی");
        adapter.addFragment(fragment1, "خارجی");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
