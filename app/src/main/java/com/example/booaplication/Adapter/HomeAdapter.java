package com.example.booaplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends FragmentPagerAdapter {

    private List<String> listTitle;
    private List<Fragment> listFragment;

    public HomeAdapter(@NonNull FragmentManager fm) {
        super(fm);
        listTitle = new ArrayList<>();
        listFragment = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        listTitle.add(title);
        listFragment.add(fragment);
    }
}
