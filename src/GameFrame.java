import Item.Doodler;
import Item.Platform;
import math.Vector2;

import javax.swing.*;
import java.util.List;

public class GameFrame extends JFrame implements UpdatableIF {

    private static final int width = 800;
    private static final int height = 600;

    private JPanel panel;

    private final Doodler doodler;
    private List<Platform> platformList;

    public GameFrame() {
        super("Doodle Jump");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();

        panel = new JPanel(null);

        doodler = new Doodler(new Vector2(width/2.0f, height/2.0f));
        panel.add(doodler);

        this.add(panel);
    }

    private void initComponents(){
        Camera.getInstance().setPosition(width/2, height/2);
    }

    @Override
    public void update() {
        panel.update(panel.getGraphics());

        doodler.update();   //Update doodler

        //platformList.forEach(Item.Platform::update);//Update platforms
    }
}
