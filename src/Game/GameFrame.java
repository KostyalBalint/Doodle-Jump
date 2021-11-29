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

public class GameFrame extends JFrame {

    private final GameCanvas gameCanvas;
    private final MenuCanvas menuCanvas;
    private final GameOverCanvas gameOverCanvas;
    private SaveScoreForm saveScoreForm;
    private KeyListener keyListener;

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

    private void removeCanvases() {
        if (menuCanvas != null) getContentPane().remove(menuCanvas);
        if (gameCanvas != null) getContentPane().remove(gameCanvas);
        if (gameOverCanvas != null) getContentPane().remove(gameOverCanvas);
    }

    public void showGame() {
        removeCanvases();
        gameCanvas.addKeyListener(keyListener);
        getContentPane().add(gameCanvas);
        pack();
        gameCanvas.requestFocus();
    }

    public void setDoodler(Doodler doodler) {
        keyListener = new DoodlerListener(doodler);
        gameCanvas.addKeyListener(keyListener);
    }

    public void showMenu() {
        removeCanvases();
        getContentPane().add(menuCanvas);
        pack();
        menuCanvas.requestFocus();
    }

    public void showGameOver() {
        removeCanvases();
        getContentPane().add(gameOverCanvas);
        pack();
        gameOverCanvas.requestFocus();
    }

    public void showSaveScore() {
        if (saveScoreForm == null)
            saveScoreForm = new SaveScoreForm();
        saveScoreForm.setVisible(true);
    }

    public GameCanvas getGameCanvas() {
        return this.gameCanvas;
    }

}
