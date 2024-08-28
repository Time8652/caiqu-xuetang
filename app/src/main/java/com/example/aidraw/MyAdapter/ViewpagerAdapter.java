package com.example.aidraw.MyAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aidraw.GuideFragmentFour;
import com.example.aidraw.GuideFragmentOne;
import com.example.aidraw.GuideFragmentThree;
import com.example.aidraw.GuideFragmentTwo;

public class ViewpagerAdapter extends FragmentStateAdapter {

    public ViewpagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new GuideFragmentOne();
            case 1:
                return new GuideFragmentTwo();
            case 2:
                return new GuideFragmentThree();
            case 3:
                return new GuideFragmentFour();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
