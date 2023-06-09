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

public class VictoryController implements Initializable {

    @FXML
    private Button nextBTN;

    private AudioInputStream audioInput;

    private File musicPath = new File("src/main/resources/com/example/animacionintro/music/victoryMusic.wav");
    private File musicPath2 = new File("src/main/resources/com/example/animacionintro/music/victorySound.wav");
    private Clip clip;
    private Clip clip2;

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
    public void playSound2(){
        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath2);
            clip2 = AudioSystem.getClip();
            clip2.open(audioInput);
            clip2.start();
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
        playSound2();

        nextBTN.setOnAction(action ->{
            HelloApplication.openWindow("startPage-view.fxml");
            stopSound();
            Stage stage = (Stage) nextBTN.getScene().getWindow();
            stage.close();
        });


    }
}
