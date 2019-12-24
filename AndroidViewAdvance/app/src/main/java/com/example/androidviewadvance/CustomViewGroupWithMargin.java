package com.example.androidviewadvance;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroupWithMargin extends ViewGroup {
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;

    private int viewsWidth;
    private int viewsHeight;
    private int viewGroupWidth=0;
    private int viewGroupHeight=0;

    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;

    public CustomViewGroupWithMargin(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewsWidth=0;
        viewsHeight=0;

        marginLeft=0;
        marginTop=0;
        marginRight=0;
        marginBottom=0;

        //自己的padding
        paddingLeft=getPaddingLeft();
        paddingTop=getPaddingTop();
        paddingRight=getPaddingRight();
        paddingBottom=getPaddingBottom();

        int childCount=getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View childView=getChildAt(i);
            //获得子view设置的margin
            MarginLayoutParams lp=(MarginLayoutParams)childView.getLayoutParams();
            //先不考虑margin测量子view的大小
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            viewsHeight+=childView.getMeasuredHeight();
            viewsWidth=Math.max(viewsWidth,childView.getMeasuredWidth());

            //可见，这里measureChild的时候不需要考虑margin，只是先记录，等待measure自己的时候再考虑margin
            //我觉得也可以在measureChild的时候就先考虑margin，measure自己的时候就不用考虑margin
            marginLeft=Math.max(marginLeft,lp.leftMargin);
            marginTop+=lp.topMargin;
            marginRight=Math.max(marginRight,lp.rightMargin);
            marginBottom+=lp.bottomMargin;
        }

        viewGroupWidth=paddingLeft+viewsWidth+paddingRight+marginLeft;
        viewGroupHeight=paddingTop+viewsHeight+paddingBottom+marginBottom;

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
            int top=paddingTop;
            for (int i = 0; i <childCount ; i++) {
                View childView=getChildAt(i);
                MarginLayoutParams lp=(MarginLayoutParams)childView.getLayoutParams();
                int left=paddingLeft+lp.leftMargin;
                top+=lp.topMargin;

                childView.layout(left,top,left+childView.getMeasuredWidth(),
                        top+childView.getMeasuredHeight());

                top+=(childView.getMeasuredHeight()+lp.bottomMargin);
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }


}
