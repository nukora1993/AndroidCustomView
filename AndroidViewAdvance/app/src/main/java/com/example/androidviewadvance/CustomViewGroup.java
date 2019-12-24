package com.example.androidviewadvance;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

//ViewGroup一般需要在onMeasure和onLayout中处理自己的padding和子view的margin
public class CustomViewGroup extends ViewGroup {
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    //所有子view的宽度之和
    private int viewsWidth;
    //所有子view的高度之和
    private int viewsHeight;
    //viewGroup的实际占用宽高(算padding)
    private int viewGroupWidth;
    private int viewGroupHeight;

    public CustomViewGroup(Context context, AttributeSet attrs){
        super(context,attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //对于view group，一般是先measure child，再measure自己，考虑padding和margin
        viewsWidth=0;
        viewsHeight=0;
        paddingLeft=getPaddingLeft();
        paddingTop=getPaddingTop();
        paddingRight=getPaddingRight();
        paddingBottom=getPaddingBottom();

        int childCount=getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View childView=getChildAt(i);
            //注意该函数传入的父view的spec，但是内部生成了子view的spec，而且考虑父的padding
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            viewsHeight+=childView.getMeasuredHeight();
            //宽度取最大的子view的宽度
            viewsWidth=Math.max(viewsWidth,childView.getMeasuredWidth());
        }

        viewGroupWidth=paddingLeft+viewsWidth+paddingRight;
        viewGroupHeight=paddingTop+viewsHeight+paddingBottom;

        //考虑wrap content
        setMeasuredDimension(measureWidth(widthMeasureSpec,viewGroupWidth),
                measureHeight(heightMeasureSpec,viewGroupHeight));
    }

    private int measureWidth(int measureSpec,int viewGroupWidth){
        int result=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result=specSize;
                break;
            case MeasureSpec.AT_MOST:
                //若为至多，那么大小为viewgroup的父的可用和自己的实测大小的较小值
                result=Math.min(viewGroupWidth,specSize);
                break;
            case MeasureSpec.EXACTLY:
                result=specSize;
                break;
        }
        return result;
    }

    private int measureHeight(int measureSpec,int viewGroupWidth){
        int result=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result=specSize;
                break;
            case MeasureSpec.AT_MOST:
                //若为至多，那么大小为viewgroup的父的可用和自己的实测大小的较小值
                result=Math.min(viewGroupWidth,specSize);
                break;
            case MeasureSpec.EXACTLY:
                result=specSize;
                break;
        }
        return result;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount=getChildCount();
            //考虑padding，从padding的位置开始layout
            int top=paddingTop;
            for (int i = 0; i <childCount ; i++) {
                View childView=getChildAt(i);
                int left=paddingLeft;
                childView.layout(left,top,left+childView.getMeasuredWidth(),top+childView.getMeasuredHeight());
                top+=childView.getMeasuredHeight();

            }
        }
    }
}
