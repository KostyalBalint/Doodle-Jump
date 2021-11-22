public class Camera implements UpdatableIF {

    private static Camera instance;

    private int x;
    private int y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Camera getInstance() {
        if(Camera.instance == null) {
            Camera.instance = new Camera(0, 0);
        }
        return Camera.instance;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        y += 1;
    }
}
