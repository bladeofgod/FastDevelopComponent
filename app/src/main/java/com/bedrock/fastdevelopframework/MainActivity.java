package com.bedrock.fastdevelopframework;


import android.os.Bundle;

import com.bedrock.fastdevelopframework.fragment.MainBookFragment;
import com.bedrock.fastdevelopframework.fragment.MainHomeFragment;
import com.bedrock.fastdevelopframework.fragment.MainNewsFragment;
import com.bedrock.fastdevelopframework.fragment.MainUserFragment;
import com.bedrock.fastdevelopframework.fragment.MainZoneFragment;
import com.bedrock.modulelib.BaseActivity;
import com.hxb.easynavigition.view.EasyNavigitionBar;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


public class MainActivity extends BaseActivity {
    private EasyNavigitionBar navigitionBar;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView(){
        navigitionBar = (EasyNavigitionBar)findViewById(R.id.easy_navigation_bar);
        String titles[] = new String[]{"首页","新闻","专区","电子书","我的"};

        int icons[] = new int[]{
                R.drawable.home_grey,
                R.drawable.news_grey,
                R.drawable.subcribe_grey,
                R.drawable.book_grey,
                R.drawable.center_grey
        };
        int check_icons[] = new int[]{
                R.drawable.home_blue,
                R.drawable.news_blue,
                R.drawable.subcirb_blue,
                R.drawable.book_blue,
                R.drawable.center_blue
        };

        fragmentList.add(new MainHomeFragment());
        fragmentList.add(new MainNewsFragment());
        fragmentList.add(new MainZoneFragment());
        fragmentList.add(new MainBookFragment());
        fragmentList.add(new MainUserFragment());

        navigitionBar.titleItems(titles)
                .normalIconItems(icons)
                .selectIconItems(check_icons)
                .fragmentList(fragmentList)
                .fragmentManager(getSupportFragmentManager())
                .iconSize(30)
                .tabTextSize(14)
                .canScroll(false)
                .build();
    }


}













