package com.anim.button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.anim.button.R;
import com.anim.button.adapter.MainAdapter;
import com.anim.button.util.DisplayUtil;
import com.anim.button.widget.listview.gesture.GestureListView;
import com.anim.button.widget.listview.loadmore.PtrClassicFrameLayout;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.spot.SpotManager;

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

        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        // 获取要嵌入广告条的布局
        LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
        // 将广告条加入到布局中
        adLayout.addView(adView);

        //插屏广告
        SpotManager.getInstance(this).showSpotAds(this, null);

        initView();
    }

    @Override
    public void onBackPressed() {
        // 如果有需要，可以点击后退关闭插播广告。
        if (!SpotManager.getInstance(this).disMiss()) {
            // 弹出退出窗口，可以使用自定义退屏弹出和回退动画,参照demo,若不使用动画，传入-1
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        SpotManager.getInstance(this).onDestroy();
        super.onDestroy();
    }

    private void initView() {
        listView = (GestureListView) findViewById(R.id.lv_content);
        ptrly = (PtrClassicFrameLayout) findViewById(R.id.ptrlyt_load);

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
