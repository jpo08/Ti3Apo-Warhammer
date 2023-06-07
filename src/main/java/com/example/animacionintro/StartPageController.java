package com.example.animacionintro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.sound.sampled.*;

public class StartPageController implements Initializable {

    @FXML
    private Button playBTN;

    private AudioInputStream audioInput;

    private File musicPath = new File("src/main/resources/com/example/animacionintro/music/startMusic.wav");
    private Clip clip;

    public AudioInputStream getAudioInput() {
        return audioInput;
    }

    public void setAudioInput(AudioInputStream audioInput) {
        this.audioInput = audioInput;
    }

    public void playSound(){
        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(1000);
        }catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }
    public void stopSound(){
        if(clip!=null){
            clip.stop();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playSound();
        playBTN.setOnAction(action ->{
            HelloApplication.openWindow("hello-view.fxml");
            stopSound();
            Stage stage = (Stage) playBTN.getScene().getWindow();
            stage.close();
        });
    }
}
