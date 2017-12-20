package com.bawei.jingdong_lss;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;



public class RightGridView extends GridView {

    public RightGridView(Context context) {
        super(context);
    }

    public RightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expendSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expendSpec);
    }
}
