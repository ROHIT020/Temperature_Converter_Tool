package com.example.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Label welcomeLabel;

    @FXML
    public ChoiceBox<String> choiceBox;

    @FXML
    public TextField userInputField;

    @FXML
    public Button convertButton;

    private static final String C_TO_F="Celcius To Farenheit";
    private static final String F_TO_C="Farenheit To Celcius";

    private boolean isC_TO_F=true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add(C_TO_F);
        choiceBox.getItems().add(F_TO_C);

        choiceBox.setValue(C_TO_F);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
                {
                    if (newValue.equals(C_TO_F)){
                        isC_TO_F=true;
                    }
                    else
                    {
                        isC_TO_F=false;
                    }
                }
        );

        convertButton.setOnAction(actionEvent ->
                convert()
        );

    }

    private void convert() {
        String input=userInputField.getText();


        float enteredTemperature=0.0f;
        try{
              enteredTemperature=Float.parseFloat(input);
        }
        catch (Exception exception) {
            warnUser();
            return;
        }


        float newTemperature = 0.0f;
        if (isC_TO_F){
            newTemperature=(enteredTemperature*9/5)+32;

        }
        else {
            newTemperature=(enteredTemperature-32)*5/9;
        }
        display(newTemperature);
    }

    private void warnUser() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please Enter a Valid Temperature");
        alert.show();
    }

    private void display(float newTemperature) {
        String unit =isC_TO_F? " F" : " C";
        System.out.println("The new Temperature is :" + newTemperature + unit);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new Temperature is :" + newTemperature + unit);
        alert.show();
    }
}