package com.anim.button.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.anim.button.R;
import com.anim.button.widget.provider.ProviderObjectView;
import com.anim.button.widget.provider.view.ItemButtonProvider;
import com.anim.button.widget.provider.view.ItemGroupProvider;
import com.anim.button.widget.provider.view.ObjectItemProvider;

/**
 * Created by jiangyue on 16/7/7.
 */
public class MainAdapter extends BaseAdapter {

    public static final String TAG = MainAdapter.class.getName();

    /* variables */
    protected Context context;
    protected LayoutInflater inflater;

    /* 各个元素的provider */
    protected ItemGroupProvider itemGroupProvider = new ItemGroupProvider();
    protected ItemButtonProvider itemButtonProvider = new ItemButtonProvider();

    public MainAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /* 临时变量 */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_main, null);
            holder = initViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setViewHolderValue(holder, convertView, position);
        return convertView;
    }

    /* 初始化holder */
    private ViewHolder initViewHolder(View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.provider = (ProviderObjectView) convertView.findViewById(R.id.prov_main_content);
        return holder;
    }

    /* 设置Value的值 */
    private void setViewHolderValue(ViewHolder holder, View convertView, final int position) {
        //此处根据后台返回的类型定义样式
        final ObjectItemProvider provider = getProvider(position);
        View view = null;
        //展示ui
        if (provider != null) {
            view = holder.provider.inflate(provider);
            provider.bindView(view, position, null);
        }
    }

    /******************************************************/
    /* 初始化provider */
    private ObjectItemProvider getProvider(int type) {
        switch (type) {
            case 0: {
                return itemGroupProvider;
            }
            case 1: {
                return itemButtonProvider;
            }
        }
        return null;
    }

    /******************************************************/

    class ViewHolder {
        ProviderObjectView provider;
    }
}
