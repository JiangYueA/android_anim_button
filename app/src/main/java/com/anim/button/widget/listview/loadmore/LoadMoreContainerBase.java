package com.anim.button.widget.listview.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

/**
 * Created by jiangyue on 15/7/8.
 */
public abstract class LoadMoreContainerBase extends LinearLayout implements LoadMoreContainer {
    private AbsListView.OnScrollListener mOnScrollListener;
    private LoadMoreUIHandler mLoadMoreUIHandler;
    private LoadMoreHandler mLoadMoreHandler;
    private boolean mIsLoading;
    private boolean mHasMore = false;
    private boolean mAutoLoadMore = true;
    private boolean mLoadError = false;
    private boolean mListEmpty = true;
    private boolean mShowLoadingForFirstPage = false;
    private View mFooterView;
    private AbsListView mAbsListView;

    public LoadMoreContainerBase(Context context) {
        super(context);
    }

    public LoadMoreContainerBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAbsListView = this.retrieveAbsListView();
        this.init();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void useDefaultHeader() {
        this.useDefaultFooter();
    }

    public void useDefaultFooter() {
        LoadMoreDefaultFooterView footerView = new LoadMoreDefaultFooterView(this.getContext());
        footerView.setVisibility(View.GONE);
        this.setLoadMoreView(footerView);
        this.setLoadMoreUIHandler(footerView);
    }

    private void init() {
        if (this.mFooterView != null) {
            this.addFooterView(this.mFooterView);
        }

        this.mAbsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean mIsEnd = false;

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (null != LoadMoreContainerBase.this.mOnScrollListener) {
                    LoadMoreContainerBase.this.mOnScrollListener.onScrollStateChanged(view, scrollState);
                }

                if (scrollState == 0 && this.mIsEnd) {
                    LoadMoreContainerBase.this.onReachBottom();
                }

            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (null != LoadMoreContainerBase.this.mOnScrollListener) {
                    LoadMoreContainerBase.this.mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }

                if (firstVisibleItem + visibleItemCount >= totalItemCount - 1) {
                    this.mIsEnd = true;
                } else {
                    this.mIsEnd = false;
                }

            }
        });
    }

    private void tryToPerformLoadMore() {
        if (!this.mIsLoading) {
            if (this.mHasMore || this.mListEmpty && this.mShowLoadingForFirstPage) {
                this.mIsLoading = true;
                if (this.mLoadMoreUIHandler != null) {
                    this.mLoadMoreUIHandler.onLoading(this);
                }

                if (null != this.mLoadMoreHandler) {
                    this.mLoadMoreHandler.onLoadMore(this);
                }

            }
        }
    }

    private void onReachBottom() {
        if (!this.mLoadError) {
            if (this.mAutoLoadMore) {
                this.tryToPerformLoadMore();
            } else if (this.mHasMore) {
                this.mLoadMoreUIHandler.onWaitToLoadMore(this);
            }

        }
    }

    public void setShowLoadingForFirstPage(boolean showLoading) {
        this.mShowLoadingForFirstPage = showLoading;
    }

    public void setAutoLoadMore(boolean autoLoadMore) {
        this.mAutoLoadMore = autoLoadMore;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        this.mOnScrollListener = l;
    }

    public void setLoadMoreView(View view) {
        if (this.mAbsListView == null) {
            this.mFooterView = view;
        } else {
            if (this.mFooterView != null && this.mFooterView != view) {
                this.removeFooterView(view);
            }

            this.mFooterView = view;
            this.mFooterView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LoadMoreContainerBase.this.tryToPerformLoadMore();
                }
            });
            this.addFooterView(view);
        }
    }

    public void setLoadMoreInVisible() {
        this.mFooterView.setVisibility(View.INVISIBLE);
    }

    public void setLoadMoreUIHandler(LoadMoreUIHandler handler) {
        this.mLoadMoreUIHandler = handler;
    }

    public void setLoadMoreHandler(LoadMoreHandler handler) {
        this.mLoadMoreHandler = handler;
    }

    public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
        this.mLoadError = false;
        this.mListEmpty = emptyResult;
        this.mIsLoading = false;
        this.mHasMore = hasMore;
        if (this.mLoadMoreUIHandler != null) {
            this.mLoadMoreUIHandler.onLoadFinish(this, emptyResult, hasMore);
        }

    }

    public void loadMoreError(int errorCode, String errorMessage) {
        this.mIsLoading = false;
        this.mLoadError = true;
        if (this.mLoadMoreUIHandler != null) {
            this.mLoadMoreUIHandler.onLoadError(this, errorCode, errorMessage);
        }

    }

    protected abstract void addFooterView(View var1);

    protected abstract void removeFooterView(View var1);

    protected abstract AbsListView retrieveAbsListView();
}
