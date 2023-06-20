import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GamePanel extends JPanel implements MouseMotionListener, ActionListener {
    
    private ImageIcon spaceShip = new ImageIcon("src/spaceship.png");
    private JLabel shipLabel = new JLabel();

    private BufferedImage bkg;

    public boolean shoot = false;
    private boolean running;

    public int shipX;
    public int shipY;

    public int bulletY = 640;
    public int bulletX = 660;

    private Image bullet = new ImageIcon("src/bullet.png").getImage();
    private Image alien = new ImageIcon("src/ufo.png").getImage();


    public int alienY = -240;
    public int alienX = 60;

    public int[][] alienPosX = new int[4][16];
    public int[][] alienPosY = new int[4][16];

    public boolean[][] alienShot = new boolean[4][16];

    private Timer t;

    private ShipMouseEvents shipMouseEvents = new ShipMouseEvents(this);

    private boolean moveR = true;

    GamePanel() {
        shipLabel.setIcon(spaceShip);
        shipLabel.setBounds(560, 700, 44, 44);

        setPreferredSize(new Dimension(1200, 800));
        setBounds(0, 0, 1200, 800);
        setLayout(null);
        add(shipLabel);

        addMouseMotionListener(this);
        addMouseListener(shipMouseEvents);

        try {
            bkg = ImageIO.read(new File("src/background.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 16; y++) {
                alienShot[x][y] = false;
            }
        }

        startGame();
        
    }

    public void startGame() {
        running = true;
        t = new Timer(20, this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (running) {

            g.drawImage(bkg, 0, 0, getWidth(), getHeight(), null);
            
            if (shoot) {
                if (bulletY > -100) {
            
                g.drawImage(bullet, bulletX, bulletY, null);
                moveBullet(g);

                } else {
                    shoot = false;
                    bulletY = 660;
                }
            }

            int alienXColumn = 0;
            int alienXRow = 0;

            int alienYColumn = 0;
            int alienYRow = 0;

            // A for loop for drawing aliens in a certain coordinate
            for (int i = 60; i < 1020;) {

                // This Condition checks if an alien got shot
                // if not, then that alien gets drawn in the screen
                if (alienShot[0][alienXColumn] == false) {
                    g.drawImage(alien, alienX+i, alienY, null);
                    i += 60;
                
                    alienPosX[alienXRow][alienXColumn] = alienX+i;
                    alienXColumn += 1;

                } else {
                    i += 60;
                
                    alienPosX[alienXRow][alienXColumn] = 0;
                    alienXColumn += 1;
                }

                for (;alienYColumn < 16; alienYColumn++) {
                    alienPosY[alienYRow][alienYColumn] = alienY;
                }
                
                
            }
            alienXColumn = 0;

            alienXColumn = 0;
            alienYColumn = 0;

            alienXRow++;
            alienYRow++;

            // A for loop for drawing aliens in a certain coordinate
            for (int i = 60; i < 1020;) {

                // This Condition checks if an alien got shot
                // if not, then that alien gets drawn in the screen
                if (alienShot[1][alienXColumn] == false) {
                    g.drawImage(alien, alienX+i, alienY+60, null);
                    i += 60;

                    alienPosX[alienXRow][alienXColumn] = alienX+i;
                    alienXColumn += 1;
                } else {
                    i += 60;

                    alienPosX[alienXRow][alienXColumn] = 0;
                    alienXColumn += 1;
                }

                for (;alienYColumn < 16; alienYColumn++) {
                    alienPosY[alienYRow][alienYColumn] = alienY+60;
                }
            }
            alienXColumn = 0;

            alienXColumn = 0;
            alienYColumn = 0;

            alienXRow++;
            alienYRow++;

            // A for loop for drawing aliens in a certain coordinate
            for (int i = 60; i < 1020;) {

                // This Condition checks if an alien got shot
                // if not, then that alien gets drawn in the screen
                if (alienShot[2][alienXColumn] == false) {
                    g.drawImage(alien, alienX+i, alienY+120, null);
                    i += 60;

                    alienPosX[alienXRow][alienXColumn] = alienX+i;
                    alienXColumn += 1;
                } else {
                    i += 60;

                    alienPosX[alienXRow][alienXColumn] = 0;
                    alienXColumn += 1;
                }

                for (;alienYColumn < 16; alienYColumn++) {
                    alienPosY[alienYRow][alienYColumn] = alienY+120;
                }
            }
            alienXColumn = 0;

            alienXColumn = 0;
            alienYColumn = 0;

            alienXRow++;
            alienYRow++;

            // A for loop for drawing aliens in a certain coordinate
            for (int i = 60; i < 1020;) {

                // This Condition checks if an alien got shot
                // if not, then that alien gets drawn in the screen
                if (alienShot[3][alienXColumn] == false) {
                    g.drawImage(alien, alienX+i, alienY+180, null);
                    i += 60;
            
                    alienPosX[alienXRow][alienXColumn] = alienX+i;
                    alienXColumn += 1;
                } else {
                    i += 60;
            
                    alienPosX[alienXRow][alienXColumn] = 0;
                    alienXColumn += 1;
                }
            
                for (;alienYColumn < 16; alienYColumn++) {
                    alienPosY[alienYRow][alienYColumn] = alienY+180;
                }


            }

        }

    }

    public void moveBullet(Graphics g) {
        bulletY -= 10;
    }

    public void moveAlien() {
        
        if ((int) Math.abs(System.currentTimeMillis()%1000) > 500) {
            alienY += 1;  
        } else {
            if (moveR) {
                alienX += 1;
            } else {
                alienX -= 1;
            }
        }

        if ((int) Math.abs(System.currentTimeMillis()%1000) > 800) {
            if (moveR) {
                moveR = false;
            } else {
                moveR = true;
            }
        }
    }

    public void checkCollision() {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 16; column++) {

                // if the bullet's coordinate matches an aien's then change the alien's shot bool to true
                if (alienPosY[row][column]+40 >= bulletY && alienPosY[row][column] <= bulletY && alienPosX[row][column]-70 <= bulletX && alienPosX[row][column]-20 >= bulletX) {

                    bulletY = -100;
                        alienShot[row][column] = true;

                        break;
                }
                
            }
        }

    }

    // Repositon the ship when the mouse moves horizontally
    public void reposShip() {
        shipLabel.setBounds(shipX-40, 680, 44, 44);
    
    }

    public void gameOver() {
        
    }

    // Reposition ship even when dragged
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        shipX = e.getX();
        reposShip();
    }

    // Reposition ship when there is a mouse movement
    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        shipX = e.getX();
        reposShip();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // do the following while the game is running
        if (running) {
            moveAlien();
            checkCollision();
        }

        repaint();
    }

}