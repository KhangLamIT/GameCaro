/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caro;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Music {
    private Clip clip;
    private AudioInputStream audioInput;
    private File musicPath;
    public Music(String filepath){
        musicPath = new File(filepath);
    }
    public void player_stop(){
        clip.stop();
    }
    public void player_start() throws LineUnavailableException, IOException{
        try{
            if(musicPath.exists()){
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
        clip.open(audioInput);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
