package com.example.androidviewadvance;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

//自定义view，只要在onDraw中考虑自己的padding属性
public class CustomView extends View {
    private Paint paint;
    private int color;
    private int width;
    private int height;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;

    public CustomView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.CustomView);
        color=array.getColor(R.styleable.CustomView_backgroundColor, Color.BLUE);
        array.recycle();
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=measureWidth(widthMeasureSpec);
        height=measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }


    private int measureWidth(int measureSpec){
        int result=200;
        int specMode=MeasureSpec.getMode(measureSpec);

        int specSize=MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result=specSize;
                break;
            case MeasureSpec.AT_MOST:
                result=Math.min(result,specSize);
                break;
            case MeasureSpec.EXACTLY:
                result=specSize;
                break;
        }

        return result;
    }

    private int measureHeight(int measureSpec){
        int result=200;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result=specSize;
                break;
            case MeasureSpec.AT_MOST:
                result=Math.min(result,specSize);
                break;
            case MeasureSpec.EXACTLY:
                result=specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //不处理自己的padding，默认画在左上角
//        Rect rect=new Rect();
//        rect.left=0;
//        rect.top=0;
//        rect.right=width;
//        rect.bottom=height;
//        canvas.drawRect(rect,paint);

        //处理padding
        paddingLeft=getPaddingLeft();
        paddingRight=getPaddingRight();
        paddingTop=getPaddingTop();
        paddingBottom=getPaddingBottom();

        Rect rect=new Rect();
        rect.left=0+paddingLeft;
        rect.top=0+paddingTop;
        rect.right=width-paddingRight;
        rect.bottom=height-paddingBottom;
        canvas.drawRect(rect,paint);


    }


}
