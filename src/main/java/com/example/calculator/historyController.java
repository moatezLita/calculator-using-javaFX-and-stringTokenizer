package com.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class historyController {
    @FXML
    private ListView<String> listView;

    public void initializeCalculation (ArrayList<String> calculationHistory){
        calculationHistory.forEach((calculation)->{
            listView.getItems().add(calculation);
        });
    }
}