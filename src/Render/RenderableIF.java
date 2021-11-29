package Render;

import java.awt.*;

/**
 * Interface for renderable objects.
 */
public interface RenderableIF {
    /**
     * Renders the object.
     *
     * @param graphics The graphics object to use.
     */
    void render(Graphics graphics);
}
