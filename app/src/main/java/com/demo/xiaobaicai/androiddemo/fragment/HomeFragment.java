package com.demo.xiaobaicai.androiddemo.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.xiaobaicai.androiddemo.FragmentContainer;
import com.demo.xiaobaicai.androiddemo.R;

public class HomeFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate){
        View rootView = inflater.inflate(R.layout.fragment_home,null);
        return rootView;
    }
}