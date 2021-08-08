package com.example.calculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class stringTokenize
{
    public static Double tokenize (String expression) throws IOException {
        String delims = " ";
        StringTokenizer tokenizer = new StringTokenizer(expression,delims);
        List <String> parenthese = new ArrayList<>();
        List <String> tokens = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        List<String> ops = new ArrayList<>();
        while (tokenizer.hasMoreElements())
        {

            String token = tokenizer.nextToken();
            tokens.add(String.valueOf(token));
            search(tokens, values, ops);
        }



        evaluation0(values,ops);
        evaluation1(values,ops);
        evaluation2(values,ops);

        return values.get(0);
    }



    /** parentheses has the higher priority and this methode it works correct only
     *  if there is only 2 values between parentheses **/
    public static void evaluation0 (List<Double> values,List<String> ops){
        List<String> parethOps = new ArrayList<>();
        List<Double> parethValue = new ArrayList<>();

        if (ops.contains("(")) {
            int index1 = ops.indexOf("(");
            int index2 = ops.indexOf(")");

            ops.remove(")");
            ops.remove("(");
            // charger les operator et les value dans de nouveau lists
            for (int i = index1; i < index2 - 1; i++) {
                parethOps.add(ops.get(i));
                ops.remove(i);


            }
            for (int i = index1; i < index2; i++) {
                parethValue.add(values.get(i));

            }
            evaluation1(parethValue, parethOps);
            evaluation2(parethValue, parethOps);
            int dif =index2-index1;

            for (int i = 0 ; i < dif ; i++){
                values.remove(index1);
            }

            values.add(0,parethValue.get(0));



        }
    }
    /** evaluation of higher priority operation * and / **/
    public static void evaluation1 (List<Double> values,List<String> ops){
        for (int i = 0 ; i< ops.size();i++)
        {
            if (ops.get(i).equals("*")||ops.get(i).equals("/"))
            {
                Double result = null;

                result = applyOp(ops.get(i),values.get(i), values.get(i+1) );
                ops.remove(i);
                values.remove(i);
                values.remove(i);
                values.add(i,result);
            }
        }
    }
    /** + and - evaluation **/
    public  static void evaluation2 (List<Double> values,List<String> ops){
        while (!ops.isEmpty())
        {
            Double result = null;

            result = applyOp(ops.get(0),values.get(0), values.get(1));

            ops.remove(0);
            values.remove(0);
            values.remove(0);
            values.add(0,result);}
    }
    /** operator and value each one in one list **/
    public static void search (List<String>tokens , List<Double> values,List<String> ops)
    {

        for (int i = 0 ; i < tokens.size();i++) {
            //match.("\\d+\\.\\d+") cette pour que la token correspond a une valeur double avec virgule
            if (tokens.get(i).matches("\\d+\\.\\d+")) {

                values.add(Double.parseDouble(tokens.get(i)));
                tokens.remove(i);
            }
            else if (tokens.get(i).matches("-\\d+\\.\\d+")) {

                values.add(Double.parseDouble(tokens.get(i)));
                tokens.remove(i);
            }
            // et ceci pour you valeur sans virgule
            else if (tokens.get(i).matches("\\d+")) {
                values.add(Double.parseDouble(tokens.get(i)));
                tokens.remove(i);

            }
            else if (tokens.get(i).matches("-\\d+")) {
                values.add(Double.parseDouble(tokens.get(i)));
                tokens.remove(i);
            }
            else if (tokens.get(i).equals("+") ||
                    tokens.get(i).equals("-") ||
                    tokens.get(i).equals("*") ||
                    tokens.get(i).equals("/") ||
                    tokens.get(i).equals("(") ||
                    tokens.get(i).equals(")")    )
            {
                ops.add(tokens.get(i));
                tokens.remove(i);

            }






        }
    }

    public static Double applyOp(String op, double a, double b)
    {
        switch (op)
        {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":


                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
        }
        return null ;
    }




}