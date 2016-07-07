package com.anim.button.widget.provider.view;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jiangyue on 16/5/30.
 * <p/>
 * 分类item style设置
 */
public interface ObjectItemProvider<T extends Parcelable> {
    public View newView(Context var1, ViewGroup var2);

    public void bindView(View var1, int var2, T var3);

    public void onItemClick(View var1, int var2, T var4);
}
