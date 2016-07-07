package com.anim.button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.anim.button.R;
import com.anim.button.adapter.MainAdapter;
import com.anim.button.util.DisplayUtil;
import com.anim.button.widget.listview.gesture.GestureListView;
import com.anim.button.widget.listview.loadmore.PtrClassicFrameLayout;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 按钮显示主Activity
 */
public class MainActivity extends Activity {

    private GestureListView listView;
    private PtrClassicFrameLayout ptrly;

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayUtil.init(this);

        initView();
    }

    private void initView() {
        listView  = (GestureListView) findViewById(R.id.lv_content);
        ptrly= (PtrClassicFrameLayout) findViewById(R.id.ptrlyt_load);

        adapter = new MainAdapter(this);
        listView.setAdapter(adapter);
        listView.setPtrClassicFrameLayout(ptrly);
        //顶部滑动的滑块高度
        ptrly.setTopViewHeight(DisplayUtil.dp2px(160));

        //设置下拉刷新
        ptrly.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrly.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, listView, header);
            }
        });
    }
}
