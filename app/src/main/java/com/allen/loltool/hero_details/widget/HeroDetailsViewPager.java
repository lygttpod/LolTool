package com.allen.loltool.hero_details.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义viewpager重写onMeasure方法，解决viewpager在ScrollView中高度不能自适应的问题
 * Created by Allen on 2015/11/18.
 */
public class HeroDetailsViewPager extends ViewPager {
    public HeroDetailsViewPager(Context context) {
        super(context);
    }

    public HeroDetailsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写此次方法实现高度自适应问题
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
