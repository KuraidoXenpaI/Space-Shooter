import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

class MainFrame extends JFrame {
    
    public ImageIcon icon = new ImageIcon(this.getClass().getResource("src/icon.ico"));

    MainFrame() {

        JLayeredPane jlp = new JLayeredPane();
        jlp.setBounds(0,0, 1200,800);

        jlp.add(new GamePanel());

        this.add(jlp);

        setTitle("Space Shooter");
        setSize(new Dimension(1200, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(150, 50);        
        setIconImage(icon.getImage());
        
        setResizable(false);

        setVisible(true);
    }

}