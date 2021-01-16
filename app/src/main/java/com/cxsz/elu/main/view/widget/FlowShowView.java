package com.cxsz.elu.main.view.widget;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


/**
 * 流量显示视图
 */
public class FlowShowView extends View {

    //控件宽高
    protected int mWidth;
    protected int mHeight;
    //边距
    protected float mPadding;
    //仪表盘盘半径
    protected int mRadius;
    //圆环最小值和最大值
    private int mMin;
    private int mMax;
    //圆环角度
    public float mArcStartAngle;
    protected float mArcSweepAngle;
    //大小刻度的数量
    protected int mLargeCalibrationNumber;
    protected int mLargeBetweenCalibrationNumber;
    //刻度总数
    protected int mCalibrationTotalNumber;
    //每个刻度之间的角度
    protected float mLargeCalibrationBetweenAngle;
    protected float mSmallCalibrationBetweenAngle;
    //刻度文本
    protected int[] mCalibrationNumberText;
    protected String[] mCalibrationBetweenText;
    //当前值
    private int mValue;
    private String unitInfo;
    //当前值所在区域
    private String mValueLevel;
    //设置的时间
    private String mDateStr;
    //时间字符串显示模板
    private String mDateStrPattern;
    //数值等级显示模板
    private String mValueLevelPattern;
    //动画时长
    private long mProgressAnimTime;
    //当前进度的角度
    public float mProgressSweepAngle;
    //文字之间的间距
    protected int mTextSpacing;
    //数值画笔
    protected Paint mPaintValue;
    //时间画笔
    protected Paint mPaintDate;

    // 圆环起始角度
    private final static float DEFAULT_ARC_START_ANGLE = 165f;
    // 圆环范围大小
    private final static float DEFAULT_ARC_SWEEP_ANGLE = 210f;
    // 进度动画时长
    private final static long DEFAULT_PROGRESS_ANIM_TIME = 2500;
    // 默认边距
    private final static int DEFAULT_PADDING = 30;
    // 默认刻度数量
    private final static int DEFAULT_LARGE_BETWEEN_CALIBRATION_NUMBER = 3;
    // 默认控件大小
    private final static int DEFAULT_SIZE = 250;
    //中间文字之间的间距
    private static final int DEFAULT_TEXT_SPACING = 7;
    // 数值画笔属性
    private final static float DEFAULT_VALUE_TEXT_SIZE = 18f;
    private final static int DEFAULT_VALUE_TEXT_COLOR = Color.WHITE;
    // 时间画笔属性
    private final static float DEFAULT_DATE_TEXT_SIZE = 12f;
    private final static int DEFAULT_DATE_TEXT_COLOR = Color.WHITE;

    //外环画笔
    private Paint mPaintOuterArc;
    //内环画笔
    private Paint mPaintInnerArc;
    //进度点画笔
    private Paint mPaintProgressPoint;
    //指示器画笔
    private Paint mPaintIndicator;
    //外环区域
    private RectF mRectOuterArc;
    //内环区域
    private RectF mRectInnerArc;
    //圆环画笔颜色
    private int mOuterArcColor;
    private int mProgressOuterArcColor;
    //内外环之间的间距
    private float mArcSpacing;
    //进度条的圆点属性
    private float[] mProgressPointPosition;
    private float mProgressPointRadius;
    //指标器的Path
    private Path mIndicatorPath;
    //指示器的起始位置
    private float mIndicatorStart;

    //默认圆环之间间距
    private static final float DEFAULT_ARC_SPACING = 10;
    //外环的默认属性
    private static final float DEFAULT_OUTER_ARC_WIDTH = 6f;
    private static final int DEFAULT_OUTER_ARC_COLOR = Color.parseColor("#FF3E4D6D");
    //外环进度的默认属性
    private static final int DEFAULT_PROGRESS_OUTER_ARC_COLOR = Color.parseColor("#FFE2AA24");
    //进度点的默认属性
    private static final float DEFAULT_PROGRESS_POINT_RADIUS = 6;
    private static final int DEFAULT_PROGRESS_POINT_COLOR = Color.parseColor("#FFE2AA24");
    //内环默认属性
    private static final int DEFAULT_INNER_ARC_COLOR = Color.parseColor("#FFE2AA24");
    //指示器默认属性
    private static final int DEFAULT_INDICATOR_COLOR = Color.parseColor("#FFE2AA24");

    // 大刻度画笔默认值
    private final static float DEFAULT_LARGE_CALIBRATION_WIDTH = 2f;
    private final static int DEFAULT_LARGE_CALIBRATION_COLOR = Color.argb(200, 255, 255, 255);
    // 小刻度画笔默认值
    private final static float DEFAULT_SMALL_CALIBRATION_WIDTH = 0.5f;
    private final static int DEFAULT_SMALL_CALIBRATION_COLOR = Color.argb(100, 255, 255, 255);
    // 默认刻度文字画笔参数
    private final static float DEFAULT_CALIBRATION_TEXT_TEXT_SIZE = 14f;
    private final static int DEFAULT_CALIBRATION_TEXT_TEXT_COLOR = Color.parseColor("#FFE2AA24");
    //大刻度画笔
    protected Paint mPaintLargeCalibration;
    //小刻度画笔
    protected Paint mPaintSmallCalibration;
    //刻度文字画笔
    protected Paint mPaintCalibrationText;
    //刻度起始位置和结束位置
    private float mCalibrationStart;
    private float mCalibrationEnd;
    //刻度的文本位置
    private float mCalibrationTextStart;
    private float mCalibrationTextEnd;
    private Paint mSweepPaintOuterArc;
    private Paint progressPaint;


    public FlowShowView(Context context) {
        this(context, null);
    }

    public FlowShowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化
        initView();
    }

    /**
     * 初始化界面
     */
    protected void initView() {
        //初始化画笔
        //数值画笔
        mPaintValue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintValue.setTextAlign(Paint.Align.CENTER);
        mPaintValue.setTextSize(sp2px(DEFAULT_VALUE_TEXT_SIZE));
        mPaintValue.setColor(DEFAULT_VALUE_TEXT_COLOR);

        //时间画笔
        mPaintDate = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintDate.setTextAlign(Paint.Align.CENTER);
        mPaintDate.setTextSize(sp2px(DEFAULT_DATE_TEXT_SIZE));
        mPaintDate.setColor(DEFAULT_DATE_TEXT_COLOR);
        //默认数据
        mArcSpacing = dp2px(DEFAULT_ARC_SPACING);
        mOuterArcColor = DEFAULT_OUTER_ARC_COLOR;
        mProgressOuterArcColor = DEFAULT_PROGRESS_OUTER_ARC_COLOR;
        mProgressPointRadius = dp2px(DEFAULT_PROGRESS_POINT_RADIUS);

        //初始化画笔
        //外环画笔
        mPaintOuterArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintOuterArc.setStrokeWidth(dp2px(DEFAULT_OUTER_ARC_WIDTH));
        mPaintOuterArc.setStyle(Paint.Style.STROKE);
        mPaintOuterArc.setStrokeCap(Paint.Cap.ROUND);
        //进度的画笔
        progressPaint = new Paint();
        LinearGradient progresslinearGradient = new LinearGradient(600, 0, 0, 0, new int[]{Color.parseColor("#FFE2AA24"), Color.parseColor("#E6E2AA24"), Color.parseColor("#CCE2AA24"), Color.parseColor("#B3E2AA24"),
                Color.parseColor("#99E2AA24"), Color.parseColor("#80E2AA24"), Color.parseColor("#66E2AA24"), Color.parseColor("#4DE2AA24"), Color.parseColor("#33E2AA24"), Color.parseColor("#1AE2AA24"), Color.parseColor("#00E2AA24")}, null, LinearGradient.TileMode.MIRROR);
        progressPaint.setShader(progresslinearGradient);
        progressPaint.setStrokeWidth(dp2px(DEFAULT_OUTER_ARC_WIDTH));
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        //扫过的画笔
        mSweepPaintOuterArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        LinearGradient linearGradient = new LinearGradient(600, 0, 0, 0, new int[]{Color.parseColor("#33E2AA24"), Color.parseColor("#1AE2AA24"), Color.parseColor("#00E2AA24")}, null, LinearGradient.TileMode.MIRROR);
        mSweepPaintOuterArc.setShader(linearGradient);

        //内环画笔
        mPaintInnerArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintInnerArc.setColor(DEFAULT_INNER_ARC_COLOR);

        //进度点画笔
        mPaintProgressPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintProgressPoint.setStyle(Paint.Style.FILL);
        mPaintProgressPoint.setColor(DEFAULT_PROGRESS_POINT_COLOR);


        //指示器画笔
        mPaintIndicator = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintIndicator.setStrokeCap(Paint.Cap.SQUARE);
        mPaintIndicator.setColor(DEFAULT_INDICATOR_COLOR);
        mPaintIndicator.setStrokeWidth(dp2px(1));


        //大刻度画笔
        mPaintLargeCalibration = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLargeCalibration.setStrokeWidth(dp2px(DEFAULT_LARGE_CALIBRATION_WIDTH));
        mPaintLargeCalibration.setColor(DEFAULT_LARGE_CALIBRATION_COLOR);

        //小刻度画笔
        mPaintSmallCalibration = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSmallCalibration.setStrokeWidth(dp2px(DEFAULT_SMALL_CALIBRATION_WIDTH));
        mPaintSmallCalibration.setColor(DEFAULT_SMALL_CALIBRATION_COLOR);

        //刻度文字画笔
        mPaintCalibrationText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCalibrationText.setTextAlign(Paint.Align.CENTER);
        mPaintCalibrationText.setTextSize(sp2px(DEFAULT_CALIBRATION_TEXT_TEXT_SIZE));
        mPaintCalibrationText.setColor(DEFAULT_CALIBRATION_TEXT_TEXT_COLOR);

        //进度点的图片
        mProgressPointPosition = new float[2];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //屏幕宽高
        mWidth = w;
        mHeight = h;
        //半径
        mRadius = mWidth / 2;

        //初始化圆环
        initArcRect(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = dp2px(DEFAULT_SIZE);
        mPadding = Math.max(Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom()));
        mPadding = Math.max(dp2px(DEFAULT_PADDING), mPadding);

        setMeasuredDimension(measureSize(widthMeasureSpec, size), measureSize(heightMeasureSpec, size));
    }

    /**
     * 判断当前控件宽高类型
     */
    private int measureSize(int measureSpec, int defaultSize) {

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                defaultSize = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        return defaultSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环
        drawArc(canvas, mArcStartAngle, mArcSweepAngle);

        //绘制进度圆环
        drawProgressArc(canvas, mArcStartAngle, mProgressSweepAngle);
        //绘制文本
        drawText(canvas, mValue, mValueLevel, mDateStr);
    }

    /**
     * 设置当前值
     */
    public void setValue(int value, String unitInfo) {
        setUnitInfo(unitInfo);
        setValue(value, false, false);
    }

    /**
     * 设置当前值
     *
     * @param value  当前数字
     * @param isAnim 是否开启动画
     * @param reset  true:从min开始进行动画 / false:从当前值开始绘制
     */
    public void setValue(int value, boolean isAnim, boolean reset) {
        value = value < mMin ? mMin : value > mMax ? mMax : value;
        //计算进度需要转动的角度
        float progressSweepAngle = computeProgressSweepAngle(value);
        mDateStr = getCurrentTime();
        //如果开启动画
        if (isAnim) {
            startProgressAnim(value, progressSweepAngle, reset);
        } else {
            mValue = value;
            mProgressSweepAngle = progressSweepAngle;
            postInvalidate();
        }
    }

    public String getUnitInfo() {
        return unitInfo;
    }

    public void setUnitInfo(String unitInfo) {
        this.unitInfo = unitInfo;
    }

    /**
     * 计算需要进度条需要转动的角度
     */
    private float computeProgressSweepAngle(int value) {
        mValueLevel = "";
        //如果小于最小值
        if (value <= mMin) {
            return 0;
        }
        //如果大于最大值
        if (value >= mMax) {
            return mArcSweepAngle;
        }
        //计算其他情况
        int index = findValueInterval(value);
        //如果不在范围内的
        if (index == -1) {
            return ((mValue - mMin) / (mMax - mMin)) * mArcSweepAngle;
        }
        index--;
        float angle = mLargeCalibrationBetweenAngle * index;
        float intervalMin = mCalibrationNumberText[index];
        float intervalMax = mCalibrationNumberText[index + 1];
        return angle + ((value - intervalMin) / (intervalMax - intervalMin)) * mLargeCalibrationBetweenAngle;
    }

    /**
     * 寻找value所在区间
     */
    private int findValueInterval(int value) {
        int i = -1;
        //是否有区间数据
        if (mCalibrationNumberText != null && mCalibrationNumberText.length > 0) {
            for (int j = 0; j < mCalibrationNumberText.length; j++) {
                if (mCalibrationNumberText[j] > value) {
                    return j;
                }
            }
        }
        return i;
    }

    /**
     * 启动进度动画
     */
    private void startProgressAnim(int value, float progressSweepAngle, boolean reset) {
        //启动角度变动动画
        float angle = reset ? 0 : mProgressSweepAngle;
        ValueAnimator angleAnim = ValueAnimator.ofFloat(angle, progressSweepAngle);
        angleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        angleAnim.setDuration(mProgressAnimTime);
        angleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //设置当进度
                mProgressSweepAngle = (float) valueAnimator.getAnimatedValue();
            }
        });
        angleAnim.start();
        int start = reset ? mMin : mValue;
        //启动文字变动动画
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, value);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(mProgressAnimTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //设置当进度
                mValue = (int) valueAnimator.getAnimatedValue();

                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 重置角度信息
     */
    private void resetCalibrationData() {
        //计算总共有多少个刻度
        mCalibrationTotalNumber = (mLargeCalibrationNumber - 1) * mLargeBetweenCalibrationNumber + mLargeCalibrationNumber;
        //计算每个大刻度之间的角度
        mLargeCalibrationBetweenAngle = mArcSweepAngle / (mLargeCalibrationNumber - 1);
        //计算小刻度之间的角度
        mSmallCalibrationBetweenAngle = mArcSweepAngle / (mCalibrationTotalNumber - 1);
    }

    /**
     * 获取当前时间
     */
    protected String getCurrentTime() {
        return getInfo();
    }

    private String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    /**
     * 设置进度条动画时长
     */
    public void setProgressAnimTime(long time) {
        mProgressAnimTime = time;
    }

    /**
     * 设置圆环角度
     */
    public void setArcAngle(float arcStartAngle, float arcSweepAngle) {
        mArcStartAngle = arcStartAngle;
        mArcSweepAngle = arcSweepAngle;

        resetCalibrationData();

        postInvalidate();
    }

    /**
     * 设置刻度信息
     */
    public void setCalibration(int[] calibrationNumberText, String[] calibrationBetweenText) {
        if (calibrationNumberText == null || calibrationNumberText.length < 2 ||
                calibrationNumberText[0] >= calibrationNumberText[calibrationNumberText.length - 1]) {
            return;
        }
        //设置刻度数量数据
        mLargeCalibrationNumber = calibrationNumberText.length;
        //设置刻度对应的显示数据
        mCalibrationNumberText = calibrationNumberText;
        mCalibrationBetweenText = calibrationBetweenText;
        //默认数值
        mTextSpacing = dp2px(DEFAULT_TEXT_SPACING);
        mMin = mCalibrationNumberText[0];
        mMax = mCalibrationNumberText[mCalibrationNumberText.length - 1];
        mArcStartAngle = DEFAULT_ARC_START_ANGLE;
        mArcSweepAngle = DEFAULT_ARC_SWEEP_ANGLE;
        mProgressAnimTime = DEFAULT_PROGRESS_ANIM_TIME;
        mLargeBetweenCalibrationNumber = DEFAULT_LARGE_BETWEEN_CALIBRATION_NUMBER;
        //计算刻度的相关数据
        resetCalibrationData();
        postInvalidate();
    }

    /**
     * 设置文字之间的间隔
     */
    public void setTextSpacing(int spacingDp) {
        mTextSpacing = dp2px(spacingDp);

        postInvalidate();
    }

    /**
     * 设置数值画笔
     */
    public void setValuePaint(float spSize, @ColorInt int color) {
        mPaintValue.setTextSize(sp2px(spSize));
        mPaintValue.setColor(color);

        postInvalidate();
    }

    /**
     * 设置时间画笔
     */
    public void setDatePaint(float spSize, @ColorInt int color) {
        mPaintDate.setTextSize(sp2px(spSize));
        mPaintDate.setColor(color);

        postInvalidate();
    }

    /**
     * 设置时间的显示格式
     *
     * @param pattern 格式(如: 评估时间：{date}) {date}为占位符
     */
    public void setDateStrPattern(String pattern) {
        mDateStrPattern = pattern;
    }

    /**
     * 设置数值等级的模板
     *
     * @param pattern 格式(如: 信用{level}) {level}为占位符
     */
    public void setValueLevelPattern(String pattern) {
        mValueLevelPattern = pattern;
    }

    /**
     * 获取最大值
     */
    public int getMax() {
        return mMax;
    }

    /**
     * 获取最小值
     */
    public int getMin() {
        return mMin;
    }

    /**
     * 获取当前值
     */
    public int getValue() {
        return mValue;
    }

    /**
     * dp2px
     */
    protected int dp2px(float dpValue) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * sp2px
     */
    protected int sp2px(float spValue) {
        float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 测量画笔高度
     */
    protected float getPaintHeight(Paint paint, String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.height();
    }


    /**
     * 初始化圆环区域
     */
    protected void initArcRect(float left, float top, float right, float bottom) {
        //外环区域
        mRectOuterArc = new RectF(left, top, right, bottom);
        initInnerRect();
    }

    /**
     * 初始化内部的区域
     */
    private void initInnerRect() {
        //内环位置
        mRectInnerArc = new RectF(mRectOuterArc.left + mArcSpacing, mRectOuterArc.top + mArcSpacing,
                mRectOuterArc.right - mArcSpacing, mRectOuterArc.bottom - mArcSpacing);

        //指标器的路径
        mIndicatorStart = mRectInnerArc.top + mArcSpacing / 2;
        mIndicatorPath = new Path();
        mIndicatorPath.moveTo(mRadius, mIndicatorStart + 40);
        mIndicatorPath.rLineTo(-dp2px(6), dp2px(40));
        mIndicatorPath.rLineTo(dp2px(12), 0);
        mIndicatorPath.close();

        //计算刻度位置
        mCalibrationStart = mRectOuterArc.bottom + mArcSpacing - mPaintInnerArc.getStrokeWidth() / 2;
        mCalibrationEnd = mCalibrationStart + mPaintInnerArc.getStrokeWidth();

        //刻度文字位置
        mCalibrationTextStart = mCalibrationEnd - dp2px(75);

        mCalibrationTextEnd = mRectOuterArc.right + mArcSpacing - mPaintInnerArc.getStrokeWidth() / 2 + mPaintInnerArc.getStrokeWidth();

    }

    /**
     * 绘制圆环
     */
    protected void drawArc(Canvas canvas, float arcStartAngle, float arcSweepAngle) {
        //绘制圆环
        mPaintOuterArc.setColor(mOuterArcColor);
        canvas.drawArc(mRectOuterArc, arcStartAngle, arcSweepAngle, false, mPaintOuterArc);

        //绘制刻度
        drawCalibration(canvas, arcStartAngle);
    }

    /**
     * 绘制刻度
     */
    private void drawCalibration(Canvas canvas, float arcStartAngle) {
        if (mLargeCalibrationNumber == 0) {
            return;
        }
        //旋转画布
        canvas.save();
//        canvas.rotate(arcStartAngle - 270, mRadius, mRadius);
//        int mod = mLargeBetweenCalibrationNumber + 1;
        //遍历数量
        for (int i = 0; i < mCalibrationBetweenText.length; i++) {
            //绘制刻度线
            if (i == 0) {
                canvas.drawText(mCalibrationBetweenText[i], dp2px(40), mCalibrationTextStart + dp2px(15), mPaintCalibrationText);
            } else if (i == mCalibrationBetweenText.length - 1) {
                canvas.drawText(mCalibrationBetweenText[i], dp2px(-40) + mRadius * 2, mCalibrationTextStart + dp2px(15), mPaintCalibrationText);
            }
            //旋转
//            canvas.rotate(mSmallCalibrationBetweenAngle, mRadius, mRadius);
        }
        canvas.restore();
    }

    /**
     * 绘制进度圆环
     */
    protected void drawProgressArc(Canvas canvas, float arcStartAngle, float progressSweepAngle) {
        //绘制进度点
        if (progressSweepAngle == 0) {
            return;
        }
        Path path = new Path();
        //添加进度圆环的区域
        path.addArc(mRectOuterArc, arcStartAngle, progressSweepAngle);
        //计算切线值和为重
        PathMeasure pathMeasure = new PathMeasure(path, false);
        pathMeasure.getPosTan(pathMeasure.getLength(), mProgressPointPosition, null);
        //绘制圆环
//        mPaintOuterArc.setColor(mProgressOuterArcColor);
        canvas.drawPath(path, progressPaint);
        //绘制进度点
        if (mProgressPointPosition[0] != 0 && mProgressPointPosition[1] != 0) {
            canvas.drawCircle(mProgressPointPosition[0], mProgressPointPosition[1], mProgressPointRadius, mPaintProgressPoint);
        }

        canvas.drawCircle(mRadius, mRadius, dp2px(42), mPaintInnerArc);


        //绘制指针
        canvas.save();
        canvas.rotate(arcStartAngle + progressSweepAngle - 270, mRadius, mRadius);
        mPaintIndicator.setStyle(Paint.Style.FILL);
        canvas.drawPath(mIndicatorPath, mPaintIndicator);
//        mPaintIndicator.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(mRadius, mIndicatorStart + dp2px(6) + 1, dp2px(2), mPaintIndicator);
        canvas.restore();

        canvas.drawArc(mRectOuterArc, arcStartAngle, progressSweepAngle, true, mSweepPaintOuterArc);
    }

    protected void drawText(Canvas canvas, int value, String valueLevel, String currentTime) {
        //绘制数值
        float marginTop = mRadius + mTextSpacing;
        if(null!=getUnitInfo()){
            canvas.drawText(getUnitInfo(), mRadius, marginTop - 10, mPaintValue);
        }
        //绘制日期
        if (!TextUtils.isEmpty(currentTime)) {
            marginTop = marginTop + getPaintHeight(mPaintDate, currentTime) + mTextSpacing;
            canvas.drawText(currentTime, mRadius, marginTop - 10, mPaintDate);
        }
    }

    /**
     * 设置圆环的距离
     */
    public void setArcSpacing(float dpSize) {
        mArcSpacing = dp2px(dpSize);

        initInnerRect();

        postInvalidate();
    }

    /**
     * 设置外环颜色
     */
    public void setOuterArcPaint(float dpSize, @ColorInt int color) {
        mPaintOuterArc.setStrokeWidth(dp2px(dpSize));
        mOuterArcColor = color;

        postInvalidate();
    }

    /**
     * 设置进度条的颜色
     */
    public void setProgressOuterArcColor(@ColorInt int color) {
        mProgressOuterArcColor = color;

        postInvalidate();
    }

    /**
     * 设置进度圆点的属性
     */
    public void setProgressPointPaint(float dpRadiusSize, @ColorInt int color) {
        mProgressPointRadius = dp2px(dpRadiusSize);
        mPaintProgressPoint.setColor(color);

        postInvalidate();
    }

    /**
     * 设置指示器属性
     */
    public void setIndicatorPaint(@ColorInt int color) {
        mPaintIndicator.setColor(color);

        postInvalidate();
    }
}
