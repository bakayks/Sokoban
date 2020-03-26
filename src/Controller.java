import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * This is Controller component
 * which implements keylistener, actionlistener.
 * When you click on a button or keyboard in Jframe, a method is called that already sends a command to model
 */
public class Controller implements KeyListener, ActionListener {
    private Model model;

    Controller(Model model) {
        this.model = model;
    }

    public void keyTyped(KeyEvent event) {
    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        String direction = null;
        System.out.println(keyCode);
        switch(keyCode) {
            case 37:
                direction = "Left";
                break;
            case 38:
                direction = "Up";
                break;
            case 39:
                direction = "Right";
                break;
            case 40:
                direction = "Down";
                break;
            case 82:
                direction = "Restart";
                break;
            case 66:
                direction = "Back";
                break;
            default:
                return;
        }
        model.doAction(direction);
    }

    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        model.doAction(command);
    }
}