package Game;

import Item.Doodler;
import Render.GameCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {

    private GameCanvas canvas;
    private KeyListener keyListener;

    public GameFrame(Dimension windowSize, Doodler doodler) {
        super("Doodle Jump");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(windowSize);
        setMinimumSize(windowSize);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //panel = new JPanel(null);
        canvas = new GameCanvas();
        getContentPane().add(canvas);
        pack();

        keyListener = new DoodlerListener(doodler);
        canvas.addKeyListener(keyListener);
        this.addKeyListener(keyListener);
    }

    public GameCanvas getCanvas() {
        return this.canvas;
    }

}
