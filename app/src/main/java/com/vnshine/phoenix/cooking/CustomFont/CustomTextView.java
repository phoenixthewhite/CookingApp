package com.vnshine.phoenix.cooking.CustomFont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by phoenix on 18/10/17.
 */

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }
    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Uvf_1.ttf", context);
        setTypeface(customFont);
    }
}
