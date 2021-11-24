package Camera;

import Item.UpdatableIF;
import math.Vector2;

public class Camera implements UpdatableIF {

    private static Camera instance;

    private Vector2 position = new Vector2();
    private Vector2 viewPort = new Vector2();

    public Camera(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public Camera(Vector2 position) {
        this.position = position;
    }

    public void setViewPort(int ViewPortWidth, int ViewPortHeight){
        this.viewPort.x = ViewPortWidth;
        this.viewPort.y = ViewPortHeight;
    }

    public static Camera getInstance() {
        if(Camera.instance == null) {
            Camera.instance = new Camera(0, 0);
        }
        return Camera.instance;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public Vector2 getPosition() {
        return new Vector2(this.position).sub(this.viewPort.x / 2, this.viewPort.y / 2);
    }

    @Override
    public void update() {
        System.out.println(this.position);
        this.position.y -= 0.5;
    }
}
