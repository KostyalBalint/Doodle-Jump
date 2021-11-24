import Camera.Camera;
import Item.Doodler;
import Item.Platform;
import math.Vector2;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class GameFrame extends JFrame implements UpdatableIF {

    private static final int width = 800;
    private static final int height = 600;

    private Random random = new Random();

    private JPanel panel;

    private final Doodler doodler;
    private LinkedList<Platform> platformList;

    public GameFrame() {
        super("Doodle Jump");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);

        //Init camera
        Camera.getInstance().setPosition(width/2, height/2);
        Camera.getInstance().setViewPort(width, height);

        panel = new JPanel(null);

        doodler = new Doodler(new Vector2(width / 2.0f, height / 2.0f));
        panel.add(doodler);

        platformList = new LinkedList<Platform>();

        this.add(panel);
        initComponents();
    }

    private void initComponents() {
        Camera.getInstance().setPosition(doodler.getX(), doodler.getY());
        generatePlatforms(0, height);
    }

    private void generatePlatforms(int startY, int endY) {
        int currentY = startY;

        while (currentY < endY) {

            int x = random.nextInt(width - Platform.width);

            //Maximum height difference between platforms must be lower than the maxJumpHeight of the Doodler
            int dH = random.nextInt(Doodler.maxJumpHeight);

            Platform platform = new Platform(new Vector2(x, dH + currentY));
            currentY += dH;

            platformList.add(platform);
            panel.add(platform);
        }

    }

    @Override
    public void update() {
        panel.update(panel.getGraphics());

        doodler.update();   //Update doodler

        Camera.getInstance().update();

        //platformList.forEach(Item.Platform::update);//Update platforms
    }
}
