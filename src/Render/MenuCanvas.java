package Render;

import util.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MenuCanvas extends Canvas {
    private Image backGround;
    private Image text;
    private Font font;
    private Button startBtn;
    private MouseListener mouseListener;

    public MenuCanvas(Dimension windowSize) {
        super();
        setBackground(Color.white);
        try {
            backGround = ImageIO.read(new File("src/assets/background.png"));
            text = ImageIO.read(new File("src/assets/doodle-jump-text.png"));
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/DoodleJump.ttf"));
            startBtn = new Button(new Vector2(0, 0), ImageIO.read(new File("src/assets/play.png")));
            Vector2 pos = new Vector2((float)((windowSize.width - startBtn.getWidth()) / 2), (float)(windowSize.height/2 + startBtn.getHeight()));
            startBtn = new Button(pos, ImageIO.read(new File("src/assets/play.png")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(startBtn.isInside(e.getPoint())){
                    System.out.println("Start Game");
                    Game.Game.getInstance().startGame();
                };
            }
        };
        this.addMouseListener(mouseListener);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (font != null) {
            g.setFont(font.deriveFont(Font.BOLD, 20));
        }
        g.drawImage(backGround, 0,0, null);
        g.drawImage(text, (getWidth() - text.getWidth(null)) / 2, ((getHeight() - text.getHeight(null)) / 2) - getHeight() / 4, null);
        startBtn.render(g);
    }
}
