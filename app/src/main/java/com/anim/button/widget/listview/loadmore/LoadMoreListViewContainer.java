package com.anim.button.widget.listview.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by jiangyue on 15/7/8.
 */
public class LoadMoreListViewContainer extends LoadMoreContainerBase {
    private ListView mListView;

    public LoadMoreListViewContainer(Context context) {
        super(context);
    }

    public LoadMoreListViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void addFooterView(View view) {
        this.mListView.addFooterView(view);
    }

    protected void removeFooterView(View view) {
        this.mListView.removeFooterView(view);
    }

    protected AbsListView retrieveAbsListView() {
        this.mListView = (ListView)this.getChildAt(0);
        return this.mListView;
    }
}
