package com.example.animacionintro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.sound.sampled.*;

public class DefeatedViewController implements Initializable {

    @FXML
    private Button retryBTN;

    private AudioInputStream audioInput;

    private File musicPath = new File("src/main/resources/com/example/animacionintro/music/defeatMusic.wav");
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
        retryBTN.setOnAction(action ->{
            HelloApplication.openWindow("startPage-view.fxml");
            stopSound();
            Stage stage = (Stage) retryBTN.getScene().getWindow();
            stage.close();
        });
    }
}
