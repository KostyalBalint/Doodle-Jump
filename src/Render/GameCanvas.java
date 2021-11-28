package Render;

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

}
