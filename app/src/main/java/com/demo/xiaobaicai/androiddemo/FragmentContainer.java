package com.demo.xiaobaicai.androiddemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentContainer extends Fragment {
    private int fragmentIndex;

    public FragmentContainer(){}

    @SuppressLint("ValidFragment")
    public FragmentContainer(int fragmentIndex){
        this.fragmentIndex=fragmentIndex;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstancestate){
        View rootView = inflater.inflate(R.layout.fragment_container,null);
        TextView tvContent = (TextView)rootView.findViewById(R.id.tv_content);
        if(fragmentIndex == 1){
            tvContent.setText("第一个Fragment");
            tvContent.setBackgroundResource(android.R.color.holo_red_light);
        }else if(fragmentIndex == 2){
            tvContent.setText("第二个Fragment");
            tvContent.setBackgroundResource(android.R.color.holo_orange_light);
        }else if(fragmentIndex == 3){
            tvContent.setText("第三个Fragment");
            tvContent.setBackgroundResource(android.R.color.holo_blue_bright);
        }
        return rootView;
    }
}