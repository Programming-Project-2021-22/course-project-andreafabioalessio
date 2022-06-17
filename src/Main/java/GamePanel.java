import javax.swing.*;
import java.awt.*;

/*

CLASS SUMMARY


class status: PANEL TO FIX
 */

/***
 * The Game will be drawn on this Panel
 */
public class GamePanel extends JPanel{

    private Game game;

    public GamePanel(Game game){

        this.game = game;
        setPanelSize();
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); // il rendering avviene più efficientemente
        addKeyListener(new KeyHandler());
        this.setFocusable(true);

    }


    //BUGGED: THE SIZE IS ACTUALLY BIGGER THAN WHAT EXPRESSED IN [screenWidth] & [screenHeight]
    private void setPanelSize(){
        Dimension size = new Dimension(Game.screenWidth, Game.screenHeight);
        setPreferredSize(size);
    }

    /***
     * Draws the panel
     * @param g Graphics engine
     */
    public void paintComponent(Graphics g){ //Graphics è una classe default di java per disegnare oggetti sullo schermo

        super.paintComponent(g); // super significa la classe parent di questa classe. in questo caso JPanel

        game.render(g);

        g.dispose(); // salvare un pò di memoria

    }
}
