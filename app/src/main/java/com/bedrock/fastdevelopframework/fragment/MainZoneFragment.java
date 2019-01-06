package com.bedrock.fastdevelopframework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bedrock.fastdevelopframework.R;
import com.bedrock.fastdevelopframework.fragment.zone_sub_fragment.ZoneOneFragment;
import com.bedrock.fastdevelopframework.fragment.zone_sub_fragment.ZoneThreeFragment;
import com.bedrock.fastdevelopframework.fragment.zone_sub_fragment.ZoneTwoFragment;
import com.bedrock.modulelib.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainZoneFragment extends BaseFragment {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private String[] titles = {"页面1","页面2","页面3"};
    private MyPageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_zone,container,false);
        initView(view);
        return  view;
    }

    private void initView(View view){
        slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_realm);

        mFragmentList.add(new ZoneOneFragment());
        mFragmentList.add(new ZoneTwoFragment());
        mFragmentList.add(new ZoneThreeFragment());

        adapter = new MyPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        slidingTabLayout.setViewPager(viewPager,titles);
    }


    private class MyPageAdapter extends FragmentPagerAdapter{

        public MyPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}

















