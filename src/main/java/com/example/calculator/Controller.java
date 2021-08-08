package com.example.calculator;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class Controller
{
    @FXML
    private Label expression;

    @FXML
    private Label result;

    private ArrayList<String> calculationHistory = new ArrayList<>();

    public void insertNumber(String number) {
        expression.setText(expression.getText() + number);
    }

    public void insertOperator(String operator) {
        expression.setText(expression.getText() + " " + operator + " ");
    }


    public void clear()
    {
        expression.setText("");
        result.setText("");
    }


    public Label getExpression ()
    {
        return expression;
    }

    public void setResult( String newResult){
        this.result.setText(newResult);

    }

    public void insertAns (String answer){
        expression.setText(expression.getText()+answer);
    }

    public Label getResult(){
        return result;
    }

    public void deleteLast (){
        if(!expression.getText().isEmpty()){
            expression.setText(expression.getText().substring(0,expression.getText().length()-1));
        }
    }
    public Integer factorial(Integer n) {
        Integer fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
    private void openHistoryWindows() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("historyController.fxml"));
            Parent root = loader.load();
            Main.getHistoryStage().setScene(new Scene(root));

            historyController historyController= loader.getController();
            historyController.initializeCalculation(calculationHistory);

            Main.getHistoryStage().show();

        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }
    public void addCalculation(String expression , String result){
        this.calculationHistory.add(expression+" = "+result);
    }

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".":
                insertNumber(buttonText);
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "(":
            case ")":
                insertOperator(buttonText);
                break;
            case "- / +":
                expression.setText("-");
                break;
            case "C":
                clear();
                break;
            case"n!":

                Integer ex= Integer.parseInt(String.valueOf(expression.getText())) ;
                ex = factorial(ex) ;
                String res = String.valueOf(ex);
                expression.setText(res);

            case "=":
                try
                {
                    Double result = stringTokenize.tokenize(getExpression().getText());
                    addCalculation(this.getExpression().getText(),String.valueOf(result));
                    setResult(String.valueOf(result ));
                }
                catch ( UnsupportedOperationException e)
                {
                    setResult("error");
                }
                break;
            case "ANS":
                expression.setText("");
                insertAns(getResult().getText());
                break;
            case "DEL":
                deleteLast();
                break;
            case "Hist":
                openHistoryWindows();
                break;

        }
    }


}