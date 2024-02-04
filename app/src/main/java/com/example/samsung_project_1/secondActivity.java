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
    public static String ERROR = "Error";

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
            @SuppressLint("CanvasSize") float width = getWidth();
            @SuppressLint("CanvasSize") float height = getHeight();
            canvas.translate(width / 2, height / 2);
            canvas.scale(width / xMax, -height / yMax);
            mPaint.setStrokeWidth(.4f);
            canvas.drawLine(-20F, 0F, 20F, 0F, mPaint);
            canvas.drawLine(0F, -20F, 0F, 20F, mPaint);
            mPaint.setColor(Color.RED);

            for (int i = -40; i <= 40; ++i) {
                canvas.drawPoint(0, i, mPaint);
                canvas.drawPoint(i, 0, mPaint);
            }
            String equation = getIntent().getStringExtra("equation");
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            GraphicalFunctionAnalyzer.analyzeGraphicalFunction(equation, canvas, mPaint);
            // find_type_function_and_draw(equation, canvas, mPaint);
        }
    }
}

//        protected void find_type_function_and_draw(String equation, Canvas canvas, Paint paint) {
//            if (equation.contains("sin")) {
//                drawSin(equation, canvas, paint);
//            }
//            else if (equation.contains("cos")){
//                drawCos(equation, canvas, paint);
//            }
//            else if (! equation.contains("x")){
//                draw_lineY(equation, canvas, paint);
//           }
//            else if (equation.contains("x^2")){
//                draw_parabola(equation, canvas, paint);
//            }
//            else if(equation.contains("/x")){
//                draw_giperbola(equation, canvas, paint);
//            }
//            else if (equation.contains("x")){
//                draw_line(equation, canvas, paint);
//            }
//        }
//
//    }
//
//    private void draw_line(String equation, Canvas canvas, Paint paint) {
//        //y=kx+-b
//        char[] func = equation.toCharArray();
//        int K = 1;
//        int B = 0;
//        B = func[-1];
//        if (func[equation.indexOf("x") + 1] == '-')
//            B = -B;
//        K = func[equation.indexOf("x") - 1];
//        if (func[equation.indexOf("x") - 2] == '-')
//            K = -K;
//        paint.setColor(Color.RED);
//        Path path = new Path();
//        path.moveTo(-40, K * -10 + B);
//        for(int i = -40; i <= 40; i+=0.1){
//            path.lineTo(i, i * K + B);
//        }
//        path.close();
//        canvas.drawPath(path, paint);
//    }
//
//    private void draw_giperbola(String equation, Canvas canvas, Paint paint) {
//        //y = ax^2 + bx + c or other variants
//        equation = equation.substring(equation.indexOf("=") + 1);
//        char[] func = equation.toCharArray();
//        int A = 1;
//        int B = 1;
//        int C = 0;
//        for (int i = 0; i < func.length - 1; i++) {
//            if (func[i] == 'x' && func[i + 1] == '^' &&
//                    func[i - 1] >= '0' && func[i - 1] <= '9'){
//            }
//        }
//    }
//
//    private void draw_parabola(String equation, Canvas canvas, Paint paint) {
//    }
//
//    private void draw_lineY(String equation, Canvas canvas, Paint paint) {
//    }
//
//    private void drawCos(String equation, Canvas canvas, Paint paint) {
//    }
//
//    private void drawSin(String equation, Canvas canvas, Paint paint) {
//
//    }

