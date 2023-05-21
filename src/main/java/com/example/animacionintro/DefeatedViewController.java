package com.example.animacionintro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DefeatedViewController implements Initializable {

    @FXML
    private Button retryBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retryBTN.setOnAction(action ->{
            HelloApplication.openWindow("startPage-view.fxml");
            Stage stage = (Stage) retryBTN.getScene().getWindow();
            stage.close();
        });
    }
}
