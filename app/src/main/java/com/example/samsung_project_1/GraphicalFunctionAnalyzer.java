package com.example.samsung_project_1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicalFunctionAnalyzer {
    public static void analyzeGraphicalFunction(String function, Canvas canvas, Paint paint) throws InterruptedException {
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
            if (fUnction[i] != '+' && fUnction[i] != '-'
                    && fUnction[i] != '*' && fUnction[i] != '/') {
                func_mini.append(fUnction[i]);
            } else {
                arrayList.add(func_mini.toString().replaceAll(" ", ""));
                func_mini = new StringBuilder();
                if (fUnction[i] == '+')
                    znaki.add("+");
                else if (fUnction[i] == '*') {
                    znaki.add("*");
                } else
                    znaki.add("-");
            }
        }
        if (func_mini.length() > 0) {
            arrayList.add(func_mini.toString().replaceAll(" ", ""));
        }
        for (double x = -40.0; x <= 40.0; x += 0.01) {
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
        Pattern pattern = Pattern.compile("(-?\\d*x\\^\\d+|-?\\d*x|-?\\d" +
                "+|\\+|\\^|x|sin\\(.*?\\)|cos\\(.*?\\)|tan\\(.*?\\)|cot\\(.*?\\)|sinh\\(.*?\\)|cosh\\(.*?\\)|tanh\\(.*?\\)|coth\\(.*?\\))");
        Matcher matcher = pattern.matcher(function);
        double sum = 0;
        System.out.println(function);
        while (matcher.find()) {
            String token = matcher.group().trim();
            System.out.println(token);

            if (token.contains("x^")) {
                double coef;
                if (token.substring(0, 1).equals("x")) coef = 1;
                else
                    coef = Double.parseDouble(token.substring(0, token.indexOf("x")));
                double coefx2 = Double.parseDouble(token.substring(token.indexOf("^") + 1));
                sum += Math.pow(x, coefx2) * coef;
                System.out.println("Парабола");
            } else if (isTrigonometricFunction(token)) {
                double coef = 1;
//                if (token.contains("(")) {
//                    coef = Double.parseDouble(token.substring(0, token.indexOf("(")));
//                }
                String trigFunction = token.substring(0, token.indexOf("("));

                switch (trigFunction) {
                    case "sin":
                        if (!token.substring(0, 1).equals("s"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += Math.sin(x) * coef;
                        System.out.println("Синусоида");
                        break;
                    case "cos":
                        if (!token.substring(0, 1).equals("c"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += Math.cos(x) * coef;
                        System.out.println("Косинусоида");
                        break;
                    case "tan":
                        if (!token.substring(0, 1).equals("t"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += Math.tan(x) * coef;
                        System.out.println("Тангенс");
                        break;
                    case "cot":
                        if (!token.substring(0, 1).equals("c"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += 1 / Math.tan(x) * coef;
                        System.out.println("Котангенс");
                        break;
                    case "sinh":
                        if (!token.substring(0, 1).equals("s"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += Math.sinh(x) * coef;
                        System.out.println("Гиперболический синус");
                        break;
                    case "cosh":
                        if (!token.substring(0, 1).equals("c"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += Math.cosh(x) * coef;
                        System.out.println("Гиперболический косинус");
                        break;
                    case "tanh":
                        if (!token.substring(0, 1).equals("t"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += Math.tanh(x) * coef;
                        System.out.println("Гиперболический тангенс");
                        break;
                    case "coth":
                        if (!token.substring(0, 1).equals("c"))
                            coef = Double.parseDouble(token.substring(0, token.indexOf("s")));
                        sum += 1 / Math.tanh(x) * coef;
                        System.out.println("Гиперболический котангенс");
                        break;
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
        return token.matches("sin.*\\(.*\\)|cos.*\\(.*\\)|tan.*\\(.*\\)|cot.*\\(.*\\)|sinh.*\\(.*\\)|cosh.*\\(.*\\)|tanh.*\\(.*\\)|coth.*\\(.*\\)");
    }
}


