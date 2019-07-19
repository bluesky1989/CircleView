package com.dyq.circleview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dyq.circleview.R;

/**
 * author:duyongqiang
 * mail: duyongqiang09@126.com
 * date:2019/7/15
 * Description:继承View的自定义view
 */
public class CircleView extends View {
    //初始化画笔
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mColor=Color.YELLOW;

    public CircleView(Context context) {
        super(context);
        init();
    }


    public CircleView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context,attrs,0);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor=a.getColor(R.styleable.CircleView_circle_color,Color.YELLOW);
        a.recycle();

        init();
    }



    private void init() {
        //给画笔设置颜色
        mPaint.setColor(mColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);//宽的规格模式
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);//宽的规格大小

        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);//高的规格模式
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);//高的规格大小

        //如果宽和高都是wrap_content
        if (widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);

        }else if (widthSpecMode==MeasureSpec.AT_MOST){//只有宽wrap_content
            setMeasuredDimension(200,heightSpecSize);
        }else if (heightSpecMode==MeasureSpec.AT_MOST){//只有高wrap_content
            setMeasuredDimension(widthSpecSize,200);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft=getPaddingLeft();//左内边距
        int paddingRight=getPaddingRight();//右内边距
        int paddingTop=getPaddingTop();//上内边距
        int paddingBottom=getPaddingBottom();//下内边距


        int width=getWidth()-paddingLeft-paddingRight;//圆的宽
        int height=getHeight()-paddingTop-paddingBottom;//圆的高
        int radius=Math.min(width,height)/2;
        //圆形x轴坐标
        int x=paddingLeft+width/2;
        //圆形y轴坐标
        int y=paddingTop+height/2;
        //开始绘画
        canvas.drawCircle(x,y,radius,mPaint);

    }
}
