package Game;

import Camera.Camera;
import Item.BlackHole;
import Item.Doodler;
import Item.Platform;
import Item.UpdatableIF;
import Render.GameCanvas;
import Render.RenderableIF;
import math.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import static Game.BlackHoleGenerator.generateBlackHole;
import static Game.PlatformGenerator.generatePlatforms;

public class GameFrame extends JFrame implements UpdatableIF{

    private static final int width = 600;
    private static final int height = 800;

    private GameCanvas canvas;
    private final Doodler doodler;
    private BlackHole blackHole;
    private LinkedList<Platform> platformList;
    private KeyListener keyListener;
    private int platformsGeneratedHeight;

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
        canvas.addKeyListener(keyListener);
        this.addKeyListener(keyListener);
        initComponents();
    }

    private void initComponents() {
        platformsGeneratedHeight = generatePlatforms(height, -1*height, new Vector2(width, height), platformList);
    }

    @Override
    public void update() {
        doodler.update(platformList);   //Update doodler
        //platformList.forEach(Item.Platform::update);//Update platforms
        Camera.getInstance().setY( -1 * (doodler.getAbsoluteMaxHeight() - (height/3.0f)));
        Camera.getInstance().update();  //Update the camera

        //Generate black hole
        blackHole = generateBlackHole(this.blackHole, doodler.getScore(), new Vector2(width, height));
        if(blackHole != null){
            System.out.println(blackHole.x + " " + blackHole.y);
        }

        //Generate platforms if needed
        if(doodler.getAbsoluteMaxHeight() - height < platformsGeneratedHeight) {
            platformsGeneratedHeight = generatePlatforms(platformsGeneratedHeight, platformsGeneratedHeight - height, new Vector2(width, height), platformList);
            //System.out.println("Current platform count: " + platformList.size());
        }
        //Remove platforms that are out of the screen
        platformList.removeIf(platform -> platform.getRenderCoordinate().y > height);

        //Render everything
        LinkedList<RenderableIF> renderableList = (LinkedList<RenderableIF>) platformList.clone();
        if(blackHole != null) {
            renderableList.addFirst(blackHole);
        }
        renderableList.addLast(doodler);
        canvas.paint(canvas.getGraphics(), renderableList);
        this.repaint();
    }
}
