import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.io.IOException;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Canvas object
 * this object added to main JFrame
 * all paintings happens here
 */
public class Canvas extends JPanel {
    private Model model;
    private Image imageGamer;
    private Image imageWall;
    private Image imageMushroom;
    private Image imageGoal;

    Canvas(Model model) {
        this.model = model;
        setOpaque(true);
        setBackground(Color.black);
        File fileNameGamer = new File("src/images/mario.png");
        File fileNameWall = new File("src/images/wall.png");
        File fileMushroom = new File("src/images/mushroom.png");
        File fileNameGoal = new File("src/images/goal.png");

        try{
            imageGamer = ImageIO.read(fileNameGamer);
            imageWall = ImageIO.read(fileNameWall);
            imageMushroom = ImageIO.read(fileMushroom);
            imageGoal = ImageIO.read(fileNameGoal);
        } catch (IOException ioe){
            System.out.println("Error: " + ioe);
        }
    }


    public void paint(Graphics g) {
        super.paint(g);
        int x = 50;
        int y = 50;
        int width = 50;
        int height = 50;
        int offset = 5;

        int[][] desktop = model.getDesktop();

        for(int i = 0; i < desktop.length;i++) {
            for(int j = 0; j < desktop[i].length;j++) {

                if(desktop[i][j] == 1) {
                    g.drawImage(imageGamer, x, y, null);
                } else if(desktop[i][j] == 2) {
                    g.drawImage(imageWall, x, y, null);

                } else if(desktop[i][j] == 3) {
                    g.drawImage(imageMushroom, x, y, null);

                } else if(desktop[i][j] == 4) {
                    g.drawImage(imageGoal, x, y, null);
                } else {
                    g.setColor(Color.white);
                }
                x = x + width + offset;
            }
            x = 50;
            y = y + height + offset;
        }

    }
}