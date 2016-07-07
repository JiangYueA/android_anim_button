package com.anim.button.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiangyue on 16/6/7.
 */
public class AndroidUtil {

    /**
     * 获取android唯一识别号
     *
     * @param context
     * @return
     */
    public static String getAndroidUniqueId(Context context) {
        String m_szImei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;
        String m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID;
        // compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        byte p_md5Data[] = m.digest();
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            if (b <= 0xF)
                m_szUniqueID += "0";
            m_szUniqueID += Integer.toHexString(b);
        }
        m_szUniqueID = m_szUniqueID.toUpperCase();
        return m_szUniqueID;
    }

    /**
     * 确定View是否需要重绘，根据Tag
     *
     * @param view 绘制的view
     * @param tag  设定的tag
     */
    public static boolean shouldRedraw(View view, String tag) {
        if (null == view.getTag() || !view.getTag().equals(tag)) {
            return true;
        }
        return false;
    }

    /**
     * 确定View是否需要重绘，根据Tag
     *
     * @param view 绘制的view
     * @param tag  设定的key
     * @param tag  设定的tag
     */
    public static boolean shouldRedraw(View view, int key, String tag) {
        if (null == view.getTag(key) || !view.getTag(key).equals(tag)) {
            return true;
        }
        return false;
    }

    /**
     * 处理手机号为中间四位加星号
     * @param phoneNumber
     * @return
     */
    public static String getStarMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }

    /**
     * 自定义显示在中间的无样式的toast信息
     */
    public static void customToast(Context context, String msg, int ls) {
        Toast toast = Toast.makeText(context, msg, ls);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
