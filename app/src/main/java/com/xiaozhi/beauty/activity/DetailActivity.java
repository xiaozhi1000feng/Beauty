package com.xiaozhi.beauty.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaozhi.beauty.R;

import java.util.ArrayList;

/**
 * Author: caizhi
 * Date:2016/3/30
 */
public class DetailActivity extends Activity{
    private ViewPager viewPager;
    private ImageView []images;
    private ImageView iv;
    private LinearLayout group;
    private ViewGroup vg;
    private ArrayList<View> pageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViewPager();
    }

    private void initViewPager() {
        viewPager=(ViewPager) vg.findViewById(R.id.viewpager);
        group=(LinearLayout) vg.findViewById(R.id.group);
        pageview =new ArrayList<View>();
        LayoutInflater inflater =getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item_details_viewpager, null);
    }
}
