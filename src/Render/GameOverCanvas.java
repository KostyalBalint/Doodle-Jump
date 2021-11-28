package Render;

import Game.Game;
import util.TextUtil;
import util.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class GameOverCanvas extends Canvas {
    private Image backGround;
    private Image text;
    private Font font;
    private Button playAgainBtn;
    private Button menuBtn;
    private Button saveScoreBtn;
    private MouseListener mouseListener;

    public GameOverCanvas(Dimension windowSize) {
        super();
        setBackground(Color.white);
        try {
            backGround = ImageIO.read(new File("src/assets/background.png"));
            text = ImageIO.read(new File("src/assets/doodle-jump-text.png"));
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/DoodleJump.ttf"));
            playAgainBtn = new Button(new Vector2(0, 0), ImageIO.read(new File("src/assets/play-again.png")));
            Vector2 pos = new Vector2((float) ((windowSize.width - playAgainBtn.getWidth()) / 2), (float) (windowSize.height / 2 + playAgainBtn.getHeight()));
            playAgainBtn = new Button((new Vector2((float) playAgainBtn.getWidth() * 0.7f, 0)).add(pos), ImageIO.read(new File("src/assets/play-again.png")));
            menuBtn = new Button((new Vector2((float) playAgainBtn.getWidth() * -0.7f, 0)).add(pos), ImageIO.read(new File("src/assets/menu.png")));
            saveScoreBtn = new Button(new Vector2(0, playAgainBtn.height + 50).add(pos), ImageIO.read(new File("src/assets/play.png")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playAgainBtn.isInside(e.getPoint())) {
                    System.out.println("Play again");
                    Game.reinitialize().startGame();
                }
                if (menuBtn.isInside(e.getPoint())) {
                    System.out.println("Show menu");
                    Game.getInstance().getGameFrame().showMenu();
                }
                if (saveScoreBtn.isInside(e.getPoint())) {
                    System.out.println("Save score");
                    Game.getInstance().getGameFrame().showSaveScore();
                }
            }
        };
        this.addMouseListener(mouseListener);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (font != null) {
            g.setFont(font.deriveFont(Font.BOLD, 20));
        }
        g.drawImage(backGround, 0, 0, null);

        //Print Game Over in the middle of the screen
        g.setColor(Color.BLACK);
        TextUtil.drawCenteredString(g, "Game Over", getBounds(), new Vector2(0, -100), font.deriveFont(Font.BOLD, 50));

        //Print the score
        TextUtil.drawCenteredString(g, "Score: " + Game.getInstance().getScore(), getBounds(), new Vector2(0, -50), font.deriveFont(Font.BOLD, 30));

        playAgainBtn.render(g);
        menuBtn.render(g);
        saveScoreBtn.render(g);
    }
}
