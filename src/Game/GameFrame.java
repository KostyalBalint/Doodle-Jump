package Game;

import Camera.Camera;
import Item.Doodler;
import Item.Platform;
import Item.UpdatableIF;
import Render.GameCanvas;
import Render.RenderableIF;
import math.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class GameFrame extends JFrame implements UpdatableIF{

    private static final int width = 800;
    private static final int height = 600;

    private Random random = new Random();

    private GameCanvas canvas;
    private final Doodler doodler;
    private LinkedList<Platform> platformList;
    private JPanel keyListener;

    public GameFrame() {
        super("Doodle Jump");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setLocationRelativeTo(null);
        setResizable(false);


        //Init camera
        Camera.getInstance().setPosition(0, 0);

        //panel = new JPanel(null);
        canvas = new GameCanvas();
        getContentPane().add(canvas);
        pack();

        doodler = new Doodler(new Vector2(width / 2.0f, height / 2.0f), new Vector2(width, height));

        platformList = new LinkedList<Platform>();

        keyListener = new DoodlerListener(doodler);
        this.add(keyListener);
        initComponents();
    }

    private void initComponents() {
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
        }

    }

    @Override
    public void update() {
        doodler.update();   //Update doodler
        //platformList.forEach(Item.Platform::update);//Update platforms
        Camera.getInstance().setY( -1 * (doodler.getAbsoluteMaxHeight() - (height/2.0f)));
        Camera.getInstance().update();  //Update the camera

        //Render everything
        LinkedList<RenderableIF> renderableList = (LinkedList<RenderableIF>) platformList.clone();
        renderableList.addLast(doodler);
        canvas.paint(canvas.getGraphics(), renderableList);
        this.repaint();
    }
}
