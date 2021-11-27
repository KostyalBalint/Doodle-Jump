package Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends Canvas {

    private Image backGround;

    public GameCanvas() {
        setBackground(Color.white);
        try{
            backGround = ImageIO.read(new File("src/assets/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g, Iterable<RenderableIF> items) {
        super.paint(g);
        renderBackground(g);
        for (RenderableIF item : items) {
            item.render(g);
        }
    }

    private void renderBackground(Graphics g) {
        g.drawImage(backGround, 0,0, null);
    }

    public void renderGameOver(){
        System.out.println("Game Over");
        Graphics g = getGraphics();
        super.paint(g);
        renderBackground(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g.drawString("Game Over", (getWidth()/2)-100, (getHeight()/2));
    }
}
