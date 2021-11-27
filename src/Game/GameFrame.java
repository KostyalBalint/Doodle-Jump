package Game;

import Item.Doodler;
import Render.GameCanvas;
import Render.MenuCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {

    private final GameCanvas gameCanvas;
    private final MenuCanvas menuCanvas;
    private final KeyListener keyListener;

    public GameFrame(Dimension windowSize, Doodler doodler) {
        super("Doodle Jump");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowSize);
        setMinimumSize(windowSize);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        menuCanvas = new MenuCanvas(windowSize);
        gameCanvas = new GameCanvas();
        keyListener = new DoodlerListener(doodler);
    }

    public void showGame() {
        if(menuCanvas != null) getContentPane().remove(menuCanvas);
        gameCanvas.addKeyListener(keyListener);
        //this.addKeyListener(keyListener);
        System.out.println(this.getKeyListeners());
        getContentPane().add(gameCanvas);
        pack();
    }

    public void showMenu(){
        if(gameCanvas != null) getContentPane().remove(gameCanvas);
        getContentPane().add(menuCanvas);
        pack();
    }

    public void showGameOver(){

    }

    public GameCanvas getGameCanvas() {
        return this.gameCanvas;
    }

}
