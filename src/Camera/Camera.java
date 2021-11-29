package Camera;

import Item.Doodler;
import Item.UpdatableIF;
import util.Vector2;

import java.awt.*;

/**
 * Camera class is used for easier coordinate system conversion.
 * By default, the camera will follow the doodler up.
 * When the doodler dies the camera will fall down.
 */
public class Camera implements UpdatableIF {

    private static Camera instance;

    private Vector2 position = new Vector2();
    private float vy = 10;

    /**
     * Camera constructor
     *
     * @param position The position of the camera
     */
    public Camera(Vector2 position) {
        this.position = position;
    }

    /**
     * Get the instance of the camera, as there can only be one camera this is a Singleton
     * By default the cameras position is (0,0)
     *
     * @return The instance of the camera
     */
    public static Camera getInstance() {
        if (Camera.instance == null) {
            Camera.instance = new Camera(new Vector2(0, 0));
        }
        return Camera.instance;
    }

    /**
     * Get the position of the camera
     *
     * @param x The x position of the camera
     * @param y The y position of the camera
     */
    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * Get the position of the camera
     *
     * @return Vector2 The position of the camera
     */
    public Vector2 getPosition() {
        return new Vector2(this.position);
    }

    /**
     * Set the x position of the camera
     *
     * @param x The x position of the camera
     */
    public void setX(float x) {
        this.position.x = x;
    }

    /**
     * Get the y position of the camera
     *
     * @param y The y position of the camera
     */
    public void setY(float y) {
        this.position.y = y;
    }

    /**
     * Makes the camera to fall down one screen height
     *
     * @param doodler    The doodler
     * @param screenSize The size of the screen
     * @return True if the camera has fallen down
     */
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

    @Override
    public void update() {

    }
}
