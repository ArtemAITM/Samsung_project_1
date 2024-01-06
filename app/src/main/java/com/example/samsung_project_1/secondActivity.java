package com.example.samsung_project_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyDraw(this));
    }
    class MyDraw extends View {
        public MyDraw(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            @SuppressLint("DrawAllocation") Paint mPaint = new Paint();
            mPaint.setColor(Color.GRAY);
            mPaint.setAntiAlias(true);
            float xMax = 40F;
            float yMax = 40F;
            @SuppressLint("CanvasSize") float width = canvas.getWidth();
            @SuppressLint("CanvasSize") float height = canvas.getHeight();
            canvas.translate(width / 2, height / 2);
            canvas.scale(width / xMax, -height / yMax);
            mPaint.setStrokeWidth(.4f);
            canvas.drawLine(-20F, 0F, 20F, 0F, mPaint);
            canvas.drawLine(0F, -20F, 0F, 20F, mPaint);
            mPaint.setColor(Color.RED);

            for (int i = -20; i <= 20; ++i){
                canvas.drawPoint(0, i, mPaint);
                canvas.drawPoint(i, 0, mPaint);
            }
            String equation = getIntent().getStringExtra("equation");
            assert equation != null;
            equation = equation.replaceAll(" ", "");
            if (equation.contains("^2")) {
                System.out.println("YES");
                draw_parabola(equation, canvas, mPaint);
            }
            else if (equation.contains("x")) {
                draw_line(equation, canvas, mPaint);
            }
        }

        private void draw_parabola(String equation, Canvas canvas, Paint mPaint) {
            int aIndex = equation.indexOf("x^2");
            int a = 1;
            int b = 1;
            int c = 0;
            int left = 1;
            if (equation.charAt(aIndex - 1) != '=')
                a = Integer.parseInt(String.valueOf(equation.charAt(aIndex - 1)));
            int bIndex = equation.indexOf("x", aIndex + 1);
            if (equation.charAt(bIndex - 1) != '+' && equation.charAt(bIndex - 1) != '-')
                b = Integer.parseInt(String.valueOf(equation.charAt(bIndex - 1)));
            if (bIndex != equation.length() - 1)
                c = Integer.parseInt(String.valueOf(equation.charAt(equation.length() - 1)));
            if (equation.charAt(0) != 'y')
                left = Integer.parseInt(String.valueOf(equation.charAt(0)));
            System.out.println(a + " " + b + " " + c + " " + left);
            float y;
            if (equation.charAt(bIndex + 1) == '-') {
                if (equation.charAt(equation.length() - 1) != 'x'
                        && equation.charAt(equation.length() - 2) == '-'){
                    for (float i = -20; i <= 20; i += 0.01) {
                        y = (i * i * a - b * i - c) / left;
                        canvas.drawPoint(i, y, mPaint);
                    }
                }
                else{
                    for (float i = -20; i <= 20; i += 0.01){
                        y = (i * i * a - b * i + c) / left;
                        canvas.drawPoint(i, y, mPaint);
                    }
                }
            }
            else if (equation.charAt(bIndex + 1) == '+') {
                if (equation.charAt(equation.length() - 1) != 'x'
                        && equation.charAt(equation.length() - 2) == '-'){
                    for (float i = -20; i <= 20; i += 0.01) {
                        y = (i * i * a + b * i - c) / left;
                        canvas.drawPoint(i, y, mPaint);
                    }
                }
                else{
                    for (float i = -20; i <= 20; i += 0.01){
                        y = (i * i * a + b * i + c) / left;
                        canvas.drawPoint(i, y, mPaint);
                    }
                }
            }
        }
    }
    private void draw_line(String equation, Canvas canvas, Paint mPaint) {
        int aIndex = equation.indexOf("x");
        int left = 1;
        int k = 1;
        int b = 0;
        if (equation.charAt(0) != 'y')
            left = Integer.parseInt(String.valueOf(equation.charAt(0)));
        if (equation.charAt(aIndex - 1) != '=')
            k = Integer.parseInt(String.valueOf(equation.charAt(aIndex - 1)));
        if (equation.charAt(equation.length() - 1) != 'x')
            b = Integer.parseInt(String.valueOf(equation.charAt(equation.length() - 1)));
        float y;
        for (float i = -20; i <= 20; i += 0.01){
            y = (k * i + b) / left;
            canvas.drawPoint(i, y, mPaint);
        }
    }

}
