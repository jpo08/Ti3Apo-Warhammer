package com.example.animacionintro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VictoryController implements Initializable {

    @FXML
    private Button nextBTN;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nextBTN.setOnAction(action ->{
            HelloApplication.openWindow("startPage-view.fxml");
            Stage stage = (Stage) nextBTN.getScene().getWindow();
            stage.close();
        });


    }
}
