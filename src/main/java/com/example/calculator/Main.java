package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage historyStage=null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root));
        createHistoryStage();
        /** Using keyword to leave **/
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
            System.out.println("Key pressed: " + event.toString());

            switch(event.getCode().getCode()) {
                case 27 : { // 27 = ESC key
                    primaryStage.close();
                    break;
                }
                case 10 : { // 10 = Return
                    primaryStage.setWidth( primaryStage.getWidth() * 2);
                }
                default:  {
                    System.out.println("Unrecognized key");
                }
            }

        });
        primaryStage.show();
    }
    public void createHistoryStage(){
        historyStage=new Stage();
        historyStage.setTitle("calculation History");
        historyStage.setAlwaysOnTop(true);
        historyStage.setResizable(false);
        // historyStage.initModality(Modality.APPLICATION_MODAL);

    }

    public static Stage getHistoryStage() {
        return historyStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}