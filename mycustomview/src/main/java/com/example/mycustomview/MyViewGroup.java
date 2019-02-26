package com.example.mycustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.mycustomview
 * @ClassName: MyViewGroup
 * @Author: DashingQI
 * @CreateDate: 2019/2/26 10:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/26 10:06 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 0) {
            //没有子view那么就没有需要ViewGroup的必要
            setMeasuredDimension(0, 0);

        } else {
            //去判断模式
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                //如果宽度和高度都是包裹内容，那么宽度就是最宽那个子view宽度，高度就是所有子View高度的总和
                int height = getTotalHeight();
                int width = getMaxChildWidth();
                setMeasuredDimension(width, height);
            } else if (heightMode == MeasureSpec.EXACTLY) {
                int width = getMaxChildWidth();
                int height = heightSize;
                setMeasuredDimension(width, height);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                int width = widthSize;
                int height = getTotalHeight();
                setMeasuredDimension(width, height);
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //垂直排列
        int childCount = getChildCount();
        int curHeight = t;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            child.layout(l, curHeight, l + width, height + curHeight);
            curHeight += height;
        }

    }

    /**
     * 获取到子view中宽度最宽的宽度数值
     *
     * @return
     */
    private int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth) {
                maxWidth = getMeasuredWidth();
            }
        }

        return maxWidth;
    }

    /**
     * 获取到所有子View的高度和
     *
     * @return
     */
    private int getTotalHeight() {
        int childCount = getChildCount();
        int totalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            totalHeight += childView.getMeasuredHeight();
        }

        return totalHeight;
    }
}
