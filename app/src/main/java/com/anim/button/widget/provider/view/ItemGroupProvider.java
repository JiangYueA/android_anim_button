package com.anim.button.widget.provider.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anim.button.R;
import com.anim.button.util.AndroidUtil;
import com.anim.button.util.DisplayUtil;
import com.anim.button.widget.gallery.GalleryRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyue on 16/6/14.
 */
public class ItemGroupProvider implements ObjectItemProvider {

    @Override
    public View newView(Context context, ViewGroup contain) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, null);
        ItemGroupProvider.ViewHolder holder = new ItemGroupProvider.ViewHolder();
        holder.galleryView = (GalleryRecyclerView) view.findViewById(R.id.gallery_content);
        view.setTag(holder);

        LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.galleryView.setLayoutManager(layout);
        holder.galleryView.setAdapter(new LayoutAdapter(context, holder.galleryView));

        return view;
    }

    @Override
    public void bindView(View view, final int position, Parcelable value) {
        final ItemGroupProvider.ViewHolder holder = (ItemGroupProvider.ViewHolder) view.getTag();
        // TODO 不设置tag id会覆盖holder的tag
        if (AndroidUtil.shouldRedraw(holder.galleryView, R.id.id_tag, "tag id")) {
            holder.galleryView.setTag(R.id.id_tag, "tag id");
            holder.galleryView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    setCellAnimation(holder, holder.galleryView);
                }
            });
            //添加数据
            for (int i = 0, size = 8; i < size; i++) {
                ((LayoutAdapter) holder.galleryView.getAdapter()).addItem(i, i);
            }
        }
    }

    @Override
    public void onItemClick(View view, int groupId, Parcelable value) {
    }

    /* 设置cell缩放动画 */
    private void setCellAnimation(ViewHolder holder, RecyclerView recyclerView) {
        //mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
        int childCount = holder.galleryView.getChildCount();
        int width = holder.galleryView.getChildAt(0).getWidth();
        int padding = (holder.galleryView.getWidth() - width) / 2;

        for (int j = 0; j < childCount; j++) {
            View v = recyclerView.getChildAt(j);
            //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
            float rate = 0;
            if (v.getLeft() <= padding) {
                if (v.getLeft() >= padding - v.getWidth()) {
                    rate = (padding - v.getLeft()) * 1f / v.getWidth();
                } else {
                    rate = 1;
                }
                v.setScaleY(1 - rate * 0.1f);
            } else {
                //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                if (v.getLeft() <= recyclerView.getWidth() - padding) {
                    rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                }
                v.setScaleY(0.9f + rate * 0.1f);
            }
        }
    }

    /********************************************************************************/

    class ViewHolder {
        private GalleryRecyclerView galleryView;
    }

    /********************************************************************************/
    class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {

        private final Context mContext;
        private final RecyclerView mRecyclerView;
        private final List<Integer> mItems;
        private int mCurrentItemId = 0;

        public class SimpleViewHolder extends RecyclerView.ViewHolder {

            private final LinearLayout fundcell;
            private final TextView fundTv;
            private final TextView fundRateTv;
            private final TextView fundDescribeTv;
            private final TextView fundPurchase;

            public SimpleViewHolder(View view) {
                super(view);
                fundcell = (LinearLayout) view.findViewById(R.id.llyt_fund_cell);
                fundTv = (TextView) view.findViewById(R.id.tv_fund_content);
                fundRateTv = (TextView) view.findViewById(R.id.tv_fund_rate);
                fundDescribeTv = (TextView) view.findViewById(R.id.tv_fund_describe);
                fundPurchase = (TextView) view.findViewById(R.id.tv_fundhome_purchase);
            }
        }

        public LayoutAdapter(Context context, RecyclerView recyclerView) {
            mContext = context;
            mRecyclerView = recyclerView;
            mItems = new ArrayList<>();
        }

        public void addItem(int position, int portfolio) {
            final int id = mCurrentItemId++;
            mItems.add(position, portfolio);
            notifyItemInserted(position);
        }

        public void removeItem(int position) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }

        public void removeAllItem() {
            for (int i = 0, size = mItems.size(); i < size; ) {
                removeItem(i);
                size = mItems.size();
            }
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.item_group_cell, parent, false);

            //设置content宽度
            TextView textView = (TextView) view.findViewById(R.id.tv_fund_content);
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = DisplayUtil.getWidthMin() / 3 - DisplayUtil.dp2px(10);
            textView.setLayoutParams(layoutParams);

            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, final int position) {
            holder.fundPurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            //设置参数值
//            holder.fundTv.setText("sdasdasd");
//            holder.fundRateTv.setText("15.7%");
//            holder.fundDescribeTv.setText("黏糊");
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}