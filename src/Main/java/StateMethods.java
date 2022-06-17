import java.awt.*;
import java.awt.event.KeyEvent;

/***
 * Defines an interface for all the GameStates. Could be helpful for future gamestates
 */
public interface StateMethods {
    public void update();
    public void draw(Graphics g);
}
