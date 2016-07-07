package com.anim.button.widget.listview.loadmore;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anim.button.R;

/**
 * Created by jiangyue on 15/7/8.
 */
public class LoadMoreDefaultFooterView extends RelativeLayout implements LoadMoreUIHandler {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private ImageView mImageView;

    public LoadMoreDefaultFooterView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoadMoreDefaultFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreDefaultFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(this.getContext()).inflate(R.layout.loadmore_default_footer_view, this);
        this.mTextView = (TextView) this.findViewById(R.id.tv_footer_default);
        this.mProgressBar = (ProgressBar) this.findViewById(R.id.pbar_footer_default);
        this.mImageView = (ImageView) this.findViewById(R.id.img_footer_default);
    }

    public void onLoading(LoadMoreContainer container) {
        this.setVisibility(View.VISIBLE);
        this.mTextView.setText(R.string.listview_load_more_loading);
        visibleProgress();
    }

    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
        if (!hasMore) {
            this.setVisibility(View.VISIBLE);
            if (empty) {
                this.mTextView.setText(R.string.listview_load_more_loaded_empty);
            } else {
                this.mTextView.setText(R.string.listview_load_more_loaded_no_more);
            }
        } else {
            this.setVisibility(View.INVISIBLE);
        }
        goneProgress();
    }

    public void onWaitToLoadMore(LoadMoreContainer container) {
        this.setVisibility(View.VISIBLE);
        this.mTextView.setText(R.string.listview_load_more_click_to_load_more);
        goneProgress();
    }

    public void onLoadError(LoadMoreContainer container, int errorCode, String errorMessage) {
        if (TextUtils.isEmpty(errorMessage)) {
            this.mTextView.setText(R.string.listview_load_more_error_refresh);
        } else {
            this.mTextView.setText(errorMessage);
        }
        goneProgress();
    }

    public void visibleProgress() {
        this.mTextView.setVisibility(View.INVISIBLE);
        this.mImageView.setVisibility(View.INVISIBLE);
        this.mProgressBar.setVisibility(View.VISIBLE);
    }

    public void goneProgress() {
        this.mTextView.setVisibility(View.VISIBLE);
        this.mImageView.setVisibility(View.VISIBLE);
        this.mProgressBar.setVisibility(View.INVISIBLE);
    }
}
