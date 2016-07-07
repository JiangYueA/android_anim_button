package com.anim.button.widget.listview.gesture;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.anim.button.widget.listview.loadmore.PtrClassicFrameLayout;


/**
 * Created by jiangyue on 16/7/6.
 */
public class GestureListView extends ListView {

    private PtrClassicFrameLayout ptrlyt;
    private int topViewPosY = 0;//顶部滑动当前位置

    public GestureListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPtrClassicFrameLayout(PtrClassicFrameLayout ptrlyt) {
        this.ptrlyt = ptrlyt;
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (ptrlyt != null && null != getChildAt(0)) {
            if (getFirstVisiblePosition() == 0) {
                topViewPosY = getChildAt(0).getTop();
                ptrlyt.setCanTouch(true);
                ptrlyt.setTopViewPosY(topViewPosY);
            } else {
                ptrlyt.setCanTouch(false);
                ptrlyt.setTopViewPosY(topViewPosY);
            }
        }
    }
}
