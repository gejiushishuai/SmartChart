package com.daivd.chart.provider;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;



public class LineProvider extends BaseLineProvider{



    @Override
    protected void drawLines(Canvas canvas, List<Float> pointX, List<Float> pointY, Paint paint) {
        Path path = new Path();
        for(int i = 0; i < pointY.size();i++){
            float x = pointX.get(i);
            float y = pointY.get(i);
            if(i == 0){
                path.moveTo(x,y);
            }else{
                path.lineTo(x,y);
            }
        }
        canvas.drawPath(path,paint);
    }
}