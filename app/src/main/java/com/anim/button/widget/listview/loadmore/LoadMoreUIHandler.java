package com.anim.button.widget.listview.loadmore;

/**
 * Created by jiangyue on 15/7/8.
 */
public interface LoadMoreUIHandler {
    void onLoading(LoadMoreContainer var1);

    void onLoadFinish(LoadMoreContainer var1, boolean var2, boolean var3);

    void onWaitToLoadMore(LoadMoreContainer var1);

    void onLoadError(LoadMoreContainer var1, int var2, String var3);
}
