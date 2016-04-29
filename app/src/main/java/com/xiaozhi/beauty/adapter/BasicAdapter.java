package com.xiaozhi.beauty.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * Created by Gao on 2015/11/6.
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected Context context;
    protected Resources res;
    protected LayoutInflater inflater;

    public BasicAdapter(List<T> list, Context context) {
        super();
        this.list = list;
        this.context = context;
        this.res = context.getResources();
        this.inflater = LayoutInflater.from(context);
    }

    public void bindData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null && !list.isEmpty() ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
