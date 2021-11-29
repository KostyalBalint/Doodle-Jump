package Camera;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CameraTest {

    @Test
    public void createCamera() {
        Camera cam = Camera.getInstance();
        assertNotNull("Camera instance must not be null", cam);
    }

    @Test
    public void getInstance() {
        Camera cam1 = Camera.getInstance();
        Camera cam2 = Camera.getInstance();
        assertEquals("Camera instance must be the same", cam1, cam2);
    }

    @Test
    public void setPosition() {
        Camera cam = Camera.getInstance();
        cam.setPosition(43, 12);
        assertEquals("Camera position X must be the correct value", 43, cam.getPosition().x, 0.00001);
        assertEquals("Camera position Y must be the correct value", 12, cam.getPosition().y, 0.00001);
    }

}
