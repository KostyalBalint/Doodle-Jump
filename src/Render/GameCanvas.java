package Render;

import Game.Game;
import util.TextUtil;
import util.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends Canvas {

    private Image backGround;
    private Font font;

    public GameCanvas() {
        super();
        setBackground(Color.white);
        try {
            backGround = ImageIO.read(new File("src/assets/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/DoodleJump.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g, Iterable<RenderableIF> items) {
        super.paint(g);
        if (font != null) {
            g.setFont(font.deriveFont(Font.BOLD, 20));
        }
        renderBackground(g);
        for (RenderableIF item : items) {
            item.render(g);
        }
    }

    private void renderBackground(Graphics g) {
        g.drawImage(backGround, 0,0, null);
    }

    public void renderGameOver() {
        System.out.println("Game Over");
        Graphics g = getGraphics();
        super.paint(g);
        renderBackground(g);

        //Print Game Over in the middle of the screen
        g.setColor(Color.BLACK);
        TextUtil.drawCenteredString(g, "Game Over", getBounds(), new Vector2(0, -100), font.deriveFont(Font.BOLD, 50));

        //Print the score
        TextUtil.drawCenteredString(g, "Score: " + Game.getInstance().getScore(), getBounds(), new Vector2(0, -50), font.deriveFont(Font.BOLD, 30));
    }
}
