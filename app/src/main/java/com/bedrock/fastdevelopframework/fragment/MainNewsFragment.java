package com.bedrock.fastdevelopframework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bedrock.fastdevelopframework.R;
import com.bedrock.modulelib.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainNewsFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_news,container,false);
        return  view;
    }
}
