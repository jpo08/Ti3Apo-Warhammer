package com.example.animacionintro;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private AudioInputStream audioInput;

    private Clip clip;
    private Clip clip2;

    public AudioInputStream getAudioInput() {
        return audioInput;
    }

    public void setAudioInput(AudioInputStream audioInput) {
        this.audioInput = audioInput;
    }

    public void playSound(File musicPath){
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
    public void playSound2(File musicPath){
        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath);
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
    public void stopSound2(){
        if(clip2!=null){
            clip2.stop();
        }

    }
}
