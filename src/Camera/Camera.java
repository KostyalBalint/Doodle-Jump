package Camera;

import Item.UpdatableIF;
import math.Vector2;

public class Camera implements UpdatableIF {

    private static Camera instance;

    private Vector2 position = new Vector2();

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

    public Vector2 getPosition() {
        return new Vector2(this.position);
    }

    @Override
    public void update() {
        this.position.y += 0.5;
    }
}
