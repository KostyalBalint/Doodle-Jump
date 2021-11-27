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

    public boolean fallWithDoodler(Doodler doodler, Vector2 screenSize) {
        if(this.position.y + 1.5 * screenSize.y < doodler.getAbsoluteMaxHeight()) {
            vy *= 0.2;
            if(vy < 0.1) {
                //Game Over
                return true;
            }
        } else {
            vy = doodler.getVelocity().y * 1.5f;
        }
        this.position.y -= vy;
        return false;
    }

    public boolean fallDown(Doodler doodler, Dimension screenSize) {
        if (this.position.y + 1.5 * screenSize.height < doodler.getAbsoluteMaxHeight()) {
            vy *= 0.6;
            if (vy < 0.1) {
                //Game Over
                return true;
            }
        } else {
            vy += 1f;
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
