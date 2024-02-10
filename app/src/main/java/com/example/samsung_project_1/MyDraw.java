package com.example.samsung_project_1;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;

class MyDraw extends View {
    private Matrix matrix = new Matrix();
    private ScaleGestureDetector scaleGestureDetector;

    private float scaleFactor = 1.0f;
    private float lastScaleFactor = 1.0f;
    private float pivotX;
    private float pivotY;
    String function;

    public MyDraw(Context context, String equation) {
        super(context);
        function = equation;
        //scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.setMatrix(matrix);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        float width = getWidth();
        float height = getHeight();
        float xMax = 20F;
        float yMax = 40F * (width / height);
        canvas.restore();
        paint.setAntiAlias(true);
        canvas.translate(width / 2, height / 2);
        canvas.scale(width / xMax, -height / yMax);
        paint.setStrokeWidth(.4f);
        paint.setColor(Color.BLACK);
        for (double i = -400.0; i <= 400.0; i += 0.1) {
            canvas.drawPoint((float) 0, (float) i, paint);
            canvas.drawPoint((float) i, (float) 0, paint);
        }
        paint.setColor(Color.GRAY);
        for (int i = -40; i <= 40; i += 1) {
            canvas.drawPoint(0, i, paint);
            canvas.drawPoint(i, 0, paint);
        }
        paint.setColor(Color.RED);
        GraphicalFunctionAnalyzer.analyzeGraphicalFunction(function, canvas, paint);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                pivotX = event.getX();
//                pivotY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float x = event.getX();
//                float y = event.getY();
//                matrix.postTranslate(x - pivotX, y - pivotY);
//                invalidate();
//                pivotX = x;
//                pivotY = y;
//                break;
//        }
//
//        return true;
//    }
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scaleFactor *= detector.getScaleFactor();
//            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
//
//            matrix.setScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
//            invalidate();
//
//            return true;
//        }
//    }
}
