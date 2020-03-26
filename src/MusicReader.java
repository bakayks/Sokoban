import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is the MusicReader class that inherits Thread.
 * With it, we can play music in the game, and cause other sound effects.
 */
public class MusicReader extends Thread{
    private AudioPlayer audioPlayer;
    private AudioStream audioStream;
    private Boolean sound;

    public MusicReader(){
        sound = true;
        audioPlayer = AudioPlayer.player;
        start();
    }

    /**
     * Returns boolean value whether background music is currently playing.
     */
    public boolean isPlaying(){
        return sound;
    }

    /**
     * Rewriting the run method in order to start the music
     */
    public void run(){
        while (true) {
            try {
                if(sound){
                    startBackgroundMusic();
                    Thread.sleep(120000);
                }else {
                    stopBackgroundMusic();
                    break;
                }
            }catch (InterruptedException ie){
                System.out.println();
            }
        }
    }

    /**
     * Here we can set a bool value to sound
     */
    public void setBoolValueSound(Boolean boolValue){
        sound = boolValue;
    }

    /**
     * This method is needed to start the background music "main.wav"
     */
    private void startBackgroundMusic(){
        try{
            InputStream test = new FileInputStream("src/musicFiles/main.wav");
            audioStream = new AudioStream(test);
            audioPlayer.start(audioStream);
        }catch(IOException error){
            System.out.println(error);
        }
    }

    /**
     * This method is needed to stop the background music
     */
    public void stopBackgroundMusic(){
        sound = false;
        audioPlayer.stop(audioStream);
    }

    /**
     * This method is needed to play the music effect "coin.wav"
     */
    public void winLevel(){
        AudioPlayer audioPlayerCoin = AudioPlayer.player;
        AudioStream audioStreamCoin;
        try{
            InputStream test = new FileInputStream("src/musicFiles/coin.wav");
            audioStreamCoin = new AudioStream(test);
            audioPlayerCoin.start(audioStreamCoin);
        }catch(IOException error){
            System.out.println(error);
        }
    }
}
