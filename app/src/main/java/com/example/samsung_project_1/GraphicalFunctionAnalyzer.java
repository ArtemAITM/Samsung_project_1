package com.example.samsung_project_1;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicalFunctionAnalyzer {
    public static void analyzeGraphicalFunction(String function, Canvas canvas, Paint paint) {
        // Паттерн для выделения коэффициентов и знаков
        Pattern pattern = Pattern.compile("(-?\\d*x\\^\\d+|\\-?\\d*x|-?\\d+|\\+|\\^|x)");
        Matcher matcher = pattern.matcher(function);
        int coefficientX2 = 0;
        int coefficientX = 0;
        int constant = 0;
        while (matcher.find()) {
            String token = matcher.group().trim();
            if (token.equals("+")) {
                continue;
            } else if (token.equals("-")) {
                coefficientX += Integer.parseInt(token);
            } else if (token.contains("x^")) {
                if (token.substring(0, token.indexOf("x^")).isEmpty()) {
                    coefficientX2 = 1;
                }
                else{
                    coefficientX2 += Integer.parseInt(token.substring(0, token.indexOf("x^")));
                }
            } else if (token.contains("x")) {
                if (token.replace("x", "").isEmpty())
                    coefficientX = 1;
                else
                    coefficientX += Integer.parseInt(token.replace("x", ""));
            } else {
                int constlast = Integer.parseInt(String.valueOf(constant));
                constant = Integer.parseInt(String.valueOf(constlast + Integer.parseInt(token)));
            }
        }

        // Определяем тип функции
        String functionType;
        if (coefficientX2 != 0) {
            functionType = "Квадратичная функция";
            Draw_functions.draw_parabola(canvas, paint, coefficientX2, coefficientX, constant);
        } else if (coefficientX != 0) {
            functionType = "Линейная функция";
            Draw_functions.draw_line(canvas, paint, coefficientX, constant);
        } else {
            functionType = "Константная функция";
            Draw_functions.draw_y_function(canvas, paint, constant);
        }

        // Выводим информацию
        System.out.println("Вы анализируете функцию: " + function);
        System.out.println("Тип функции: " + functionType);
        System.out.println("Коэффициент X^2: " + coefficientX2);
        System.out.println("Коэффициент X: " + coefficientX);
        System.out.println("Свободный член: " + constant);
    }
}