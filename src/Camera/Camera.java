package Camera;

import Item.Doodler;
import Item.UpdatableIF;
import util.Vector2;

import java.awt.*;

public class Camera implements UpdatableIF {

    private static Camera instance;

    private Vector2 position = new Vector2();
    private float vy = 10;

    public Camera(Vector2 position) {
        this.position = position;
    }

    public static Camera getInstance() {
        if(Camera.instance == null) {
            Camera.instance = new Camera(new Vector2(0,0 ));
        }
        return Camera.instance;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public boolean fallDown(Doodler doodler, Dimension screenSize) {
        if ((-1 * doodler.getAbsoluteMaxHeight()) - this.position.y > screenSize.getHeight()) {
            vy *= 0.6;
            if (vy < 0.1) {
                //Game Over
                return true;
            }
        } else {
            vy += 0.8f;
            if (vy >= 30) {
                vy = 30;
            }
            this.setY(this.getPosition().y - vy);
        }
        return false;
    }

    public Vector2 getPosition() {
        return new Vector2(this.position);
    }

    @Override
    public void update() {

    }
}
