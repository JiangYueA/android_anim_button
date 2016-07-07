package com.anim.button.widget.listview.loadmore;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by jiangyue on 15/7/8.
 */
public class PtrClassicFrameLayout extends PtrFrameLayout {

    private PtrClassicHeader mPtrClassicHeader;
    private boolean canTouch = true;//是否触摸顶部可以滑动
    private int topViewPosY = 0;//顶部滑动当前位置
    private int topViewHeight = 0;//顶部滑动高度
    private PointF touchPoint;

    public PtrClassicFrameLayout(Context context) {
        super(context);
        this.initViews();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initViews();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initViews();
    }

    private void initViews() {
        this.mPtrClassicHeader = new PtrClassicHeader(this.getContext());
        this.setHeaderView(this.mPtrClassicHeader);
        this.addPtrUIHandler(this.mPtrClassicHeader);
    }

    public PtrClassicHeader getHeader() {
        return this.mPtrClassicHeader;
    }

    public void setLastUpdateTimeKey(String key) {
        if (this.mPtrClassicHeader != null) {
            this.mPtrClassicHeader.setLastUpdateTimeKey(key);
        }

    }

    public void setLastUpdateTimeRelateObject(Object object) {
        if (this.mPtrClassicHeader != null) {
            this.mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }

    public void setCanTouch(boolean canTouch) {
        this.canTouch = canTouch;
    }

    public void setTopViewPosY(int topViewPosY) {
        this.topViewPosY = topViewPosY;
    }

    public void setTopViewHeight(int topViewHeight) {
        this.topViewHeight = topViewHeight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (event.getY() < topViewPosY + topViewHeight) {
                    touchPoint = new PointF(event.getX(), event.getY());
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                touchPoint = null;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //计算触摸区域
                if (canTouch && touchPoint != null && touchPoint.y < topViewPosY + topViewHeight) {
                    event.setLocation(event.getX(), touchPoint.y);
                }
                break;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
