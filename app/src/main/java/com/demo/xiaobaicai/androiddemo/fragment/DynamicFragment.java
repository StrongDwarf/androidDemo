package com.demo.xiaobaicai.androiddemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.xiaobaicai.androiddemo.FragmentContainer;
import com.demo.xiaobaicai.androiddemo.R;

public class DynamicFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate){
        View rootView = inflater.inflate(R.layout.fragment_dynamic,null);
        return rootView;
    }
}