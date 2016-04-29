package com.xiaozhi.beauty.bean;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Gao on 2015/11/6.
 */
public class ViewHolder {

    private View convertView;
    private SparseArray<View> viewMap;

    private ViewHolder(Context context, int layoutId) {
        viewMap = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId, null);
        convertView.setTag(this);
    }

    /**
     * ViewHolder初始化
     */
    public static ViewHolder getHodler(Context context, View convertView, int layoutId) {
        if (convertView == null || convertView.getTag() == null) {
            return new ViewHolder(context, layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 获取view
     */
    public <T extends View> T getView(int viewId) {
        View view = viewMap.get(Integer.valueOf(viewId));
        if (view == null) {
            view = convertView.findViewById(viewId);
            viewMap.put(Integer.valueOf(viewId), view);
        }
        return (T) view;
    }

    /**
     * 获取convertView
     */
    public View getConvertView() {
        return convertView;
    }

}
