package com.daivd.chart.core;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.daivd.chart.data.PieData;
import com.daivd.chart.matrix.RotateHelper;
import com.daivd.chart.provider.pie.PieProvider;

/**
 * Created by huang on 2017/10/9.
 * 饼图
 */

public class PieChart  extends BaseChart<PieProvider,PieData> implements RotateHelper.OnRotateListener{
    private RotateHelper rotateHelper;
    private boolean isOnRotate;

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PieChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void drawContent(Canvas canvas) {
        provider.drawProvider(canvas, chartRect, matrixHelper, paint);
    }

    @Override
    protected PieProvider initProvider() {
        rotateHelper = new RotateHelper(this);
        PieProvider provider =  new PieProvider();
        provider.setRotateHelper(rotateHelper);
        return provider;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN){
            isOnRotate = provider.getProviderRect().contains((int)event.getX(),(int)event.getY());
        }
        if(isOnRotate){
            super.onTouchEvent(event);
            return  rotateHelper.dispatchTouchEvent(event);
        }
         return super.onTouchEvent(event);
    }

    public void setRotate(boolean rotate) {
        if(rotate){
            setZoom(false);
        }
        rotateHelper.setRotate(rotate);
    }

    @Override
    public void onRotate(double angle) {
        invalidate();
    }
}
