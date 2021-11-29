package Game;

import Item.Doodler;
import Render.GameCanvas;
import Render.GameOverCanvas;
import Render.MenuCanvas;
import ScoreBoard.SaveScoreForm;
import ScoreBoard.ScoreBoardData;
import ScoreBoard.ScoreLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Game Frame is the main frame of the game, and acts as a Menu.
 * It contains the game canvas, menu canvas, and game over canvas.
 */
public class GameFrame extends JFrame {

    private final GameCanvas gameCanvas;
    private final MenuCanvas menuCanvas;
    private final GameOverCanvas gameOverCanvas;
    private SaveScoreForm saveScoreForm;
    private KeyListener keyListener;

    /**
     * GameFrame constructor.
     *
     * @param windowSize the size of the window.
     * @param doodler    the doodler of the game.
     */
    public GameFrame(Dimension windowSize, Doodler doodler) {
        super("Doodle Jump");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowSize);
        setMinimumSize(windowSize);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        menuCanvas = new MenuCanvas(windowSize);
        gameOverCanvas = new GameOverCanvas(windowSize);
        gameCanvas = new GameCanvas();
        keyListener = new DoodlerListener(doodler);

        //Load scores
        ScoreLoader.loadScores(ScoreBoardData.getInstance(), "scores.xml");
    }

    /**
     * Remove all canvases from the frame.
     */
    private void removeCanvases() {
        if (menuCanvas != null) getContentPane().remove(menuCanvas);
        if (gameCanvas != null) getContentPane().remove(gameCanvas);
        if (gameOverCanvas != null) getContentPane().remove(gameOverCanvas);
    }

    /**
     * Show the game canvas.
     */
    public void showGame() {
        removeCanvases();
        gameCanvas.addKeyListener(keyListener);
        getContentPane().add(gameCanvas);
        pack();
        gameCanvas.requestFocus();
    }

    /**
     * Show the menu canvas.
     */
    public void showMenu() {
        removeCanvases();
        getContentPane().add(menuCanvas);
        pack();
        menuCanvas.requestFocus();
    }

    /**
     * Show the game over canvas.
     */
    public void showGameOver() {
        removeCanvases();
        getContentPane().add(gameOverCanvas);
        pack();
        gameOverCanvas.requestFocus();
    }

    /**
     * Set the key listener of the game.
     * Connect the key listener to the doodler
     *
     * @param doodler the doodler of the game.
     */
    public void setDoodler(Doodler doodler) {
        keyListener = new DoodlerListener(doodler);
        gameCanvas.addKeyListener(keyListener);
    }

    /**
     * Show the save score form.
     */
    public void showSaveScore() {
        if (saveScoreForm == null)
            saveScoreForm = new SaveScoreForm();
        saveScoreForm.setVisible(true);
    }

    /**
     * Get the game canvas
     *
     * @return GameCanvas
     */
    public GameCanvas getGameCanvas() {
        return this.gameCanvas;
    }

    /**
     * Get the menu canvas
     *
     * @return MenuCanvas
     */
    public MenuCanvas getMenuCanvas() {
        return this.menuCanvas;
    }

    /**
     * Get the game over canvas
     *
     * @return GameOverCanvas
     */
    public GameOverCanvas getGameOverCanvas() {
        return this.gameOverCanvas;
    }

}
