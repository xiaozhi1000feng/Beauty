package com.xiaozhi.beauty.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.okhttp.Cache;
import com.xiaozhi.beauty.R;
import com.xiaozhi.beauty.bean.SexyGirlsDataBean;
import com.xiaozhi.beauty.bean.ViewHolder;
import com.xiaozhi.beauty.utils.AppUrl;

import java.util.List;

/**
 * Author: caizhi
 * Date:2016/3/30
 */
public class SexyGirlsAdapter extends BaseAdapter{
    private Context context;
    private List<SexyGirlsDataBean> list;
    private DisplayImageOptions options;
    public SexyGirlsAdapter(List<SexyGirlsDataBean> list,Context context){
        this.list=list;
        this.context=context;
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);
        ImageLoader.getInstance().init(configuration);
//         options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.main_background)
//                .showImageOnFail(R.drawable.main_background)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_sexy_girls, null);
            holder.title_tv = (TextView) convertView.findViewById(R.id.item_sexy_girls_title_tv);
            holder.head_iv = (ImageView) convertView.findViewById(R.id.item_sexy_girls_head_iv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title_tv.setText(list.get(position).getTitle());
        String url=list.get(position).getImg();
        String imgUrl= AppUrl.BASE_IMG_URL+url;
        ImageLoader.getInstance().displayImage(imgUrl,holder.head_iv);

        return convertView;
    }
    public class ViewHolder{
        TextView title_tv;
        ImageView head_iv;
    }
    public void bindData(List<SexyGirlsDataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
}
