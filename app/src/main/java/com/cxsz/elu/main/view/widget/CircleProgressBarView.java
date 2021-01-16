package com.cxsz.elu.main.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.cxsz.elu.R;
import com.cxsz.framework.tool.CommonUtils;

/**
 * @author Demons
 * @date 2019/6/25
 */
public class CircleProgressBarView extends View {

    private Paint circlePaint;
    private float progressSweepAngle;//进度条圆弧扫过的角度
    private float startAngle;//背景圆弧的起始角度
    private float sweepAngle;//背景圆弧经过的角度
    private CircleProgressBarAnim circleProgressBarAnim;
    private float mProgressValue;
    private Paint circlePaintBg;

    private float maxNum;//进度条最大值
    private int defaultSize;
    private float barWidth;
    private RectF rect;
    private int progressColor;
    private int progressBgColor;

    public CircleProgressBarView(Context context) {
        super(context);
    }

    public CircleProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBarView);
        progressColor = typedArray.getColor(R.styleable.CircleProgressBarView_progress_color, Color.parseColor("#45F0EA"));
        progressBgColor = typedArray.getColor(R.styleable.CircleProgressBarView_progress_bg_color, Color.parseColor("#0A8CFF"));
        startAngle = typedArray.getFloat(R.styleable.CircleProgressBarView_start_angle, 0);
        sweepAngle = typedArray.getFloat(R.styleable.CircleProgressBarView_sweep_angle, 360);
        barWidth = typedArray.getDimension(R.styleable.CircleProgressBarView_bar_width, CommonUtils.dip2px(context, 26));
        typedArray.recycle();
        defaultSize = CommonUtils.dip2px(context, 100);
        rect = new RectF();

        circlePaintBg = new Paint();
        circlePaintBg.setStyle(Paint.Style.STROKE);
        circlePaintBg.setColor(progressBgColor);
        circlePaintBg.setAntiAlias(true);
        circlePaintBg.setStrokeWidth(barWidth);
//        circlePaintBg.setStrokeCap(Paint.Cap.ROUND);

        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(progressColor);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(barWidth);
//        circlePaint.setStrokeCap(Paint.Cap.ROUND);

        circleProgressBarAnim = new CircleProgressBarAnim();

        mProgressValue = 0;
        maxNum = 100;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int with = measureSize(defaultSize, widthMeasureSpec);
        int height = measureSize(defaultSize, heightMeasureSpec);
        int min = Math.min(with, height);// 获取View最短边的长度
        setMeasuredDimension(min, min);// 强制改View为以最短边为长度的正方形

        if (min >= barWidth * 2) {
            rect.set(barWidth / 2, barWidth / 2, min - barWidth / 2, min - barWidth / 2);
        }
    }

    private int measureSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.min(result, size);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, startAngle, sweepAngle, false, circlePaintBg);
        canvas.drawArc(rect, startAngle, progressSweepAngle, false, circlePaint);
    }

    public class CircleProgressBarAnim extends Animation {

        public CircleProgressBarAnim() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            progressSweepAngle = interpolatedTime * sweepAngle * mProgressValue / maxNum; //这里计算进度条的比例
            postInvalidate();
        }
    }

    public void setCircleProgressBarTime(float progressValue, int time) {
        circleProgressBarAnim.setDuration(time);
        this.startAnimation(circleProgressBarAnim);
        this.mProgressValue = progressValue;
    }
}
