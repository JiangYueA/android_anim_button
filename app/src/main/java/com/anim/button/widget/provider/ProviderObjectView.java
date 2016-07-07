package com.anim.button.widget.provider;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.anim.button.widget.provider.view.ObjectItemProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiangyue on 16/5/30.
 */
public class ProviderObjectView extends FrameLayout {

    private Map<Class<? extends ObjectItemProvider>, AtomicInteger> mViewCounterMap;
    private Map<Class<? extends ObjectItemProvider>, View> mContentViewMap;

    private View mInflateView;
    private int mMaxContainSize = 3;

    public ProviderObjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!this.isInEditMode()) {
            this.init(attrs);
        }

    }

    private void init(AttributeSet attrs) {
        this.mViewCounterMap = new HashMap();
        this.mContentViewMap = new HashMap();
    }

    public <T extends ObjectItemProvider> View inflate(T t) {
        View result = null;
        if (this.mInflateView != null) {
            this.mInflateView.setVisibility(GONE);
        }

        if (this.mContentViewMap.containsKey(t.getClass())) {
            result = (View) this.mContentViewMap.get(t.getClass());
            this.mInflateView = result;
            ((AtomicInteger) this.mViewCounterMap.get(t.getClass())).incrementAndGet();
        }

        if (result != null) {
            if (result.getVisibility() == GONE) {
                result.setVisibility(VISIBLE);
            }

            return result;
        } else {
            this.recycle();
            result = t.newView(this.getContext(), this);
            if (result != null) {
                super.addView(result);
                this.mContentViewMap.put(t.getClass(), result);
                this.mViewCounterMap.put(t.getClass(), new AtomicInteger());
            }

            this.mInflateView = result;
            return result;
        }
    }

    private void recycle() {
        if (this.mInflateView != null) {
            int count = this.getChildCount();
            if (count >= this.mMaxContainSize) {
                Entry min = null;

                Entry item;
                for (Iterator view = this.mViewCounterMap.entrySet().iterator(); view.hasNext(); min = ((AtomicInteger) min.getValue()).get() > ((AtomicInteger) item.getValue()).get() ? item : min) {
                    item = (Entry) view.next();
                    if (min == null) {
                        min = item;
                    }
                }

                this.mViewCounterMap.remove(min.getKey());
                View view1 = (View) this.mContentViewMap.remove(min.getKey());
                this.removeView(view1);
            }

        }
    }
}
