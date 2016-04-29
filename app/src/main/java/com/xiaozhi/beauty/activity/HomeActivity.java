package com.xiaozhi.beauty.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xiaozhi.beauty.R;
import com.xiaozhi.beauty.adapter.SexyGirlsAdapter;
import com.xiaozhi.beauty.bean.SexyGirlsBean;
import com.xiaozhi.beauty.bean.SexyGirlsDataBean;
import com.xiaozhi.beauty.utils.AppUrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: caizhi
 * Date:2016/3/29
 */
public class HomeActivity extends Activity {
    private PullToRefreshListView sexy_girl_listView;
    private SexyGirlsAdapter adapter;
    private List<SexyGirlsDataBean> imgList;
    private int page = 1;
    private int rows = 10;
    private int id = 1;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
        setListener();
    }

    private void initView() {
        sexy_girl_listView = (PullToRefreshListView) findViewById(R.id.sexy_girl_listView);
        sexy_girl_listView.setMode(PullToRefreshBase.Mode.BOTH);
        gson = new Gson();

        initLabels();

        if (imgList == null) {
            imgList = new ArrayList<SexyGirlsDataBean>();
            loadData();
        }
        if (adapter == null) {
            adapter = new SexyGirlsAdapter(imgList, HomeActivity.this);
        }
        sexy_girl_listView.setAdapter(adapter);
    }

    private void initLabels() {
        ILoadingLayout startLabels = sexy_girl_listView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");
        startLabels.setRefreshingLabel("正在载入...");
        startLabels.setReleaseLabel("松开后刷新...");

        ILoadingLayout endLabels = sexy_girl_listView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉加载...");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("松开后加载...");
    }

    private void setListener() {
        sexy_girl_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = page + 1;
                loadData();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("result");
            Log.i("result", "请求结果为-->" + result);
            // UI界面的更新等相关操作
            SexyGirlsBean sexyGirlsBean = gson.fromJson(result, SexyGirlsBean.class);
            List<SexyGirlsDataBean> dataBean = sexyGirlsBean.getTngou();
            if (page == 1) {
                imgList.clear();
            }
            imgList.addAll(dataBean);
            adapter.bindData(imgList);
            sexy_girl_listView.onRefreshComplete();
        }
    };

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormEncodingBuilder()
                        .add("page", page + "").add("rows", rows + "")
                        .add("id", id + "").build();

                Request request = new Request.Builder()
                        .url(AppUrl.BASE_URL + AppUrl.IMG_LIST)
                        .post(body).build();
                Response response = null;
                String result = null;
                try {
                    response = client.newCall(request).execute();
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("result", result);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }).start();
    }
}
