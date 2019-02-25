package com.example.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.mycustomview
 * @ClassName: MyCustomView
 * @Author: DashingQI
 * @CreateDate: 2019/2/25 11:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/25 11:11 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyCustomView extends View {

    private Paint paint;

    public MyCustomView(Context context) {
        this(context, null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
        if (width < height)
            height = width;
        else
            width = height;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取到圆心
        int r = getMeasuredWidth() / 2;

        int centerX = getLeft() + r;
        int centerY = getTop() + r;

        canvas.drawCircle(centerX, centerY, r, paint);


    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                //如果测量模式是最大值为size （wrap_content）
                mySize = size;

                break;
            case MeasureSpec.EXACTLY:
                //如果是固定大小，那就不要去改变它  （match_parent ;指定固定的值）
                mySize = size;
                break;
        }

        return mySize;

    }
}
