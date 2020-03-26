import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * This is out Viewer component
 * It draws whole project
 */
public class Viewer{
    private Canvas canvas;
    private JFrame frame;
    private JMenuItem jMenuMusic;
    private JMenu jMenuLevel;
    private Controller controller;

    public Viewer() {
        Model model = new Model(this);
        controller = new Controller(model);
        canvas = new Canvas(model);

        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenuBar menuBar = new JMenuBar();
        JMenu jMenuGame = new JMenu("Game");
        jMenuGame.setFont(font);
        JMenuItem jMenuReturn = new JMenuItem("Restart");
        jMenuReturn.setFont(font);
        jMenuReturn.setActionCommand("Restart");
        jMenuReturn.addActionListener(controller);
        jMenuMusic = new JMenuItem("Sound");
        jMenuMusic.setFont(font);
        jMenuMusic.setActionCommand("Sound");
        jMenuMusic.addActionListener(controller);
        jMenuGame.add(jMenuReturn);
        jMenuGame.add(jMenuMusic);
        menuBar.add(jMenuGame);

        jMenuLevel= new JMenu("Levels");
        jMenuLevel.setFont(font);
        JMenuItem jMenuItem = new JMenuItem("Level "+1);
        jMenuItem.setActionCommand("1");
        jMenuItem.addActionListener(controller);
        jMenuLevel.add(jMenuItem);
        menuBar.add(jMenuLevel);

        frame = new JFrame("Sokoban Intern Labs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocation(300, 0);
        frame.add("Center", canvas);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
        frame.addKeyListener(controller);
    }

    /**
     * This method updates our text in JMenuMusic
     */
    public void setTextOnJMenuMusicItem(Boolean boolValue){
        if(boolValue)
            jMenuMusic.setText("Sounds on");
        else
            jMenuMusic.setText("Sounds off");
    }

    /**
     * This method redraws our map.
     */
    public void update() {
        canvas.repaint();
    }


    /**
     * This method adds a new level button to our menu.
     */
    public void addItem(String level) {
        JMenuItem jMenuItem = new JMenuItem("Level "+level);
        jMenuItem.setActionCommand(level);
        jMenuItem.addActionListener(controller);
        jMenuLevel.add(jMenuItem);
    }

    /**
     * This method calls JOptionPane on our JFrame
     * and displays our GIF picture when you win.
     */
    public void showLabelWin() {
        URL url = this.getClass().getResource("images/win.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(frame, label);
    }
}