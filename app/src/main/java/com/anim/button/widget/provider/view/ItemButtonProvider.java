package com.anim.button.widget.provider.view;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anim.button.R;
import com.anim.button.util.AndroidUtil;
import com.anim.button.widget.button.AnimImageView;
import com.anim.button.widget.button.FadePopImageView;
import com.anim.button.widget.button.FillPopImageView;
import com.anim.button.widget.button.RippleImageView;
import com.anim.button.widget.button.TransitionImageView;

/**
 * Created by jiangyue on 16/7/7.
 */
public class ItemButtonProvider implements ObjectItemProvider {
    @Override
    public View newView(Context context, ViewGroup var2) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_btn, null);
        ItemButtonProvider.ViewHolder holder = new ItemButtonProvider.ViewHolder();
        holder.animImageView = (AnimImageView) view.findViewById(R.id.img_anim_button);
        holder.rippleImageView = (RippleImageView) view.findViewById(R.id.img_ripple_button);
        holder.fillPopImageView = (FillPopImageView) view.findViewById(R.id.img_fill_button);
        holder.fadeImageView = (FadePopImageView) view.findViewById(R.id.img_fade_button);
        holder.transitionImageView = (TransitionImageView) view.findViewById(R.id.img_transition_button);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, int var2, Parcelable var3) {
        final ItemButtonProvider.ViewHolder holder = (ItemButtonProvider.ViewHolder) view.getTag();
        // TODO 不设置tag id会覆盖holder的tag
        if (AndroidUtil.shouldRedraw(holder.animImageView, R.id.id_tag, "tag id")) {
            //缩放btn
            holder.animImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.animImageView.startAnimShow();
                }
            });
            //波纹btn
            holder.transitionImageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.rippleImageView.startRippleAnimation();
                }
            }, 500);
            //渐引btn
            holder.fillPopImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.fillPopImageView.startAnimShow();
                }
            });
            //渐变btn
            holder.fadeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.fadeImageView.startFadeAnimationClick();
                }
            });
            //路径btn
            holder.transitionImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.transitionImageView.simulateProgress();
                }
            });
            holder.transitionImageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.transitionImageView.simulateProgress();
                }
            }, 500);
            //运动btn
        }
    }

    @Override
    public void onItemClick(View var1, int var2, Parcelable var4) {

    }

    class ViewHolder {
        private AnimImageView animImageView;
        private FillPopImageView fillPopImageView;
        private RippleImageView rippleImageView;
        private FadePopImageView fadeImageView;
        private TransitionImageView transitionImageView;
    }

}
