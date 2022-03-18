package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {



    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){ // se premi w
            upPressed = true;
        }
        if (code == KeyEvent.VK_A){ // se premi a
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S){ // se premi s
            downPressed = true;
        }
        if (code == KeyEvent.VK_D){ // se premi d
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){ // se premi w
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){ // se premi a
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){ // se premi s
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){ // se premi d
            rightPressed = false;
        }
    }
}
