package Render;

import Game.Game;
import ScoreBoard.ScoreBoard;
import util.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * Menu Canvas is a canvas that is used to display the menu.
 * Start button is used to start the game.
 * Score button is used to display the score board.
 */
public class MenuCanvas extends Canvas {
    private Image backGround;
    private Image text;
    private Font font;
    private Button startBtn;
    private Button scoreBtn;
    private MouseListener mouseListener;
    private ScoreBoard scoreBoard;

    /**
     * Constructor for the Menu Canvas.
     * Loads the background image and the button images.
     * Sets the mouse listener for the buttons.
     *
     * @param windowSize The size of the window.
     */
    public MenuCanvas(Dimension windowSize) {
        super();
        setBackground(Color.white);
        try {
            backGround = ImageIO.read(new File("src/assets/background.png"));
            text = ImageIO.read(new File("src/assets/doodle-jump-text.png"));
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/DoodleJump.ttf"));
            startBtn = new Button(new Vector2(0, 0), ImageIO.read(new File("src/assets/play.png")));
            Vector2 pos = new Vector2((float) ((windowSize.width - startBtn.getWidth()) / 2), (float) (windowSize.height / 2 + startBtn.getHeight()));
            startBtn = new Button(pos, ImageIO.read(new File("src/assets/play.png")));
            scoreBtn = new Button(new Vector2(0, 0), ImageIO.read(new File("src/assets/scores.png")));
            scoreBtn = new Button(new Vector2(windowSize.width, windowSize.height).sub((float) scoreBtn.getWidth() + 20, (float) scoreBtn.getHeight() + 50), ImageIO.read(new File("src/assets/scores.png")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (startBtn.isInside(e.getPoint())) {
                    System.out.println("Start Game");
                    Game.reinitialize().startGame();
                }
                ;
                if (scoreBtn.isInside(e.getPoint())) {
                    System.out.println("Show Scores");
                    if (scoreBoard == null)
                        scoreBoard = new ScoreBoard();
                    scoreBoard.setVisible(true);
                }
            }
        };
        this.addMouseListener(mouseListener);
    }

    /**
     * Paints the background image and the buttons.
     *
     * @param g The graphics object.
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (font != null) {
            g.setFont(font.deriveFont(Font.BOLD, 20));
        }
        g.drawImage(backGround, 0, 0, null);
        g.drawImage(text, (getWidth() - text.getWidth(null)) / 2, ((getHeight() - text.getHeight(null)) / 2) - getHeight() / 4, null);
        startBtn.render(g);
        scoreBtn.render(g);
    }
}
