package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){

        soundURL[0] = getClass().getResource("/sounds/slow-travel.wav");
        soundURL[1] = getClass().getResource("/sounds/win.wav");
        soundURL[2] = getClass().getResource("/sounds/jump.wav");
        soundURL[3] = getClass().getResource("/sounds/walk.wav");
        soundURL[4] = getClass().getResource("/sounds/die.wav");
        soundURL[5] = getClass().getResource("/sounds/level_song.wav");

    }

    public void setFile(int x){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[x]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){
        }
    }

    public void play(){
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
