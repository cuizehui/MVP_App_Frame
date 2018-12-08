package com.nela.mvpdemo.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.data.AppData;

public class CircleDrawables {

    private static GradientDrawable sDefaultDrawable;

    public static Drawable getDefault() {
        if (sDefaultDrawable == null) {
            sDefaultDrawable = new GradientDrawable();
            sDefaultDrawable.setShape(GradientDrawable.OVAL);
            sDefaultDrawable.setColor(AppData.getContext().getColor(R.color.colorPrimary));
        }
        return sDefaultDrawable;
    }

}
