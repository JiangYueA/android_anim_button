package com.anim.button.widget.listview.loadmore;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by jiangyue on 15/7/8.
 */
public  interface LoadMoreContainer {
    void setShowLoadingForFirstPage(boolean var1);

    void setAutoLoadMore(boolean var1);

    void setOnScrollListener(AbsListView.OnScrollListener var1);

    void setLoadMoreView(View var1);

    void setLoadMoreUIHandler(LoadMoreUIHandler var1);

    void setLoadMoreHandler(LoadMoreHandler var1);

    void loadMoreFinish(boolean var1, boolean var2);

    void loadMoreError(int var1, String var2);
}
