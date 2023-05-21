package com.example.animacionintro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    private Button playBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playBTN.setOnAction(action ->{
            HelloApplication.openWindow("hello-view.fxml");
            Stage stage = (Stage) playBTN.getScene().getWindow();
            stage.close();
        });
    }
}
