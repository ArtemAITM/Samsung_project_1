package com.example.samsung_project_1;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicalFunctionAnalyzer {
    public static void analyzeGraphicalFunction(String function, Canvas canvas, Paint paint) {
        float y;
        char[] fUnction = function.toCharArray();
        StringBuilder func_mini = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> znaki = new ArrayList<>();
        if (fUnction[0] != '-')
            znaki.add("+");
        else
            znaki.add("-");

        for (int i = 0; i < fUnction.length; ++i) {
            if (fUnction[i] != '+' && fUnction[i] != '-') {
                func_mini.append(fUnction[i]);
            } else {
                arrayList.add(func_mini.toString().replaceAll(" ", ""));
                func_mini = new StringBuilder();
                if (fUnction[i] == '+')
                    znaki.add("+");
                else
                    znaki.add("-");
            }
        }
        if (func_mini.length() > 0) {
            arrayList.add(func_mini.toString().replaceAll(" ", ""));
        }

        for (double x = -40.0; x <= 40.0; x += 0.1) {
            y = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                float podf = PodgraphicAnalyze(arrayList.get(i), (float) x);
                if(znaki.get(i).equals("+"))
                    y += podf;
                else
                    y -= podf;
            }
            canvas.drawPoint((float) x, y, paint);
        }
    }
    public static float PodgraphicAnalyze(String function, float x) {
        Pattern pattern = Pattern.compile("(-?\\d*x\\^\\d+|-?\\d*x|-?\\d+|\\+|\\^|x)");
        Matcher matcher = pattern.matcher(function);
        double sum = 0;

        while (matcher.find()) {
            String token = matcher.group().trim();
            System.out.println(token);

            if (token.contains("x^")) {
                double coef = Float.parseFloat(token.substring(0, token.indexOf("x")));
                double coefx2 = Double.parseDouble(token.substring(token.indexOf("^") + 1));
                sum += Math.pow(x, coefx2) * coef;
                System.out.println("Парабола");
            } else if (isTrigonometricFunction(token)) {
                double coef = 1; // Коэффициент по умолчанию для тригонометрических функций
                if (token.contains("(")) {
                    coef = Double.parseDouble(token.substring(0, token.indexOf("(")));
                }
                String trigFunction = token.substring(0, 3);
                switch (trigFunction) {
                    case "sin":
                        sum += Math.sin(x) * coef;
                        System.out.println("Синусоида");
                        break;
                    // Добавьте обработку других тригонометрических функций, если необходимо
                }
            } else if (token.contains("x")) {
                if (token.length() == 1)
                    sum += x;
                else
                    sum += x * Float.parseFloat(token.substring(0, token.indexOf("x")));
                System.out.println("Просто x");
            } else {
                sum += Float.parseFloat(token);
            }
            if (token.equals("-")) {
                sum = -sum;
            }
        }

        return (float) sum;
    }

    private static boolean isTrigonometricFunction(String token) {
        return token.matches("sin.*|cos.*|tan.*|cot.*|sinh.*|cosh.*|tanh.*|coth.*");
    }
}