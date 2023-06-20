import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ShipMouseEvents implements MouseListener{

    private GamePanel gPanel;

    public int DELAY = 80;

    public Timer t;

    private Clip blasterSound;

    ShipMouseEvents(GamePanel gPanel) {
        this.gPanel = gPanel;
        loadSounds();

    }

    private void loadSounds() {
        try {
            blasterSound = AudioSystem.getClip();
            AudioInputStream blaster = AudioSystem.getAudioInputStream(new File("src/blaster.wav"));
            blasterSound.open(blaster);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
                
    }  

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gPanel.shoot) {
            gPanel.bulletX = e.getX()-33;
            gPanel.shoot = true;

            blasterSound.setFramePosition(0);
            blasterSound.start();
        } else {
            System.out.println("Out of ammo!");
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //gPanel.shoot = false;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //gPanel.reposShip(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
