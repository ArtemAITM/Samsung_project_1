package com.example.samsung_project_1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Draw_functions {
    public static void draw_line(Canvas canvas, Paint paint, int Xkof, int SvKof){
        Path path = new Path();
        path.moveTo(-40, -40 * Xkof + SvKof);
        for (double i = -40.0; i <= 40; i += 0.01){
            path.lineTo((float) i, (float) (i * Xkof + SvKof));
        }
        path.close();
        canvas.drawPath(path, paint);
    }
    public static void draw_y_function(Canvas canvas, Paint paint, int SvKof){
        canvas.drawLine(-40, SvKof, 40, SvKof, paint);
    }
    public static void draw_parabola(Canvas canvas, Paint paint, int X2Kof, int xKof, int SvKof){
        Path path = new Path();
        path.moveTo(-40, -40 * -40 * X2Kof -40 * xKof + SvKof);
        for (double i = -40.0; i <= 40.0; i += 0.01){
            path.lineTo((float) i, (float) (i * i *X2Kof + i * xKof + SvKof));
        }
        path.close();
        canvas.drawPath(path, paint);
    }
}
