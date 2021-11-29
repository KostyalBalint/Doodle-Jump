package Item;

import Camera.Camera;
import util.Vector2;

import java.awt.*;

/**
 * Item is an abstract class that is extended by all items that can be moved.
 * It stores the x and y coordinates of the item as double values, witch enables a more precise movement.
 */
public abstract class Item extends Rectangle {

    private Vector2 position;
    //private Vector2 size;
    private Image image;

    /**
     * Item constructor
     *
     * @param position position of the item
     * @param size     size of the item
     */
    public Item(Vector2 position, Dimension size) {
        this.position = position;
        this.setSize(size);
        this.setBounds((int) this.position.x, (int) this.position.y, this.getSize().width, this.getSize().height);
    }

    /**
     * Sets the X coordinate of the item
     *
     * @param x X coordinate
     */
    public void setX(float x) {
        this.position.x = x;
    }

    /**
     * Sets the Y coordinate of the item
     *
     * @param y Y coordinate
     */
    public void setY(float y) {
        this.position.y = y;
    }

    /**
     * Get where the item must be drawn on the Canvas
     * This is determined by the camera
     *
     * @return Vector2 the position of the item on the Canvas
     */
    public Vector2 getRenderCoordinate() {
        Camera camera = Camera.getInstance();
        Vector2 drawPosition = camera.getPosition().add(this.position);
        return drawPosition;
    }

    /**
     * Get the position of the item
     *
     * @return Vector2 the position of the item
     */
    public Vector2 getPosition() {
        return this.position;
    }

    /**
     * Get the Bottom Right corner of the item
     *
     * @return Vector2 the Bottom Right corner of the item
     */
    public Vector2 getBottomRight() {
        return new Vector2(this.position.x + this.getSize().width, this.position.y + this.getSize().height);
    }

    /**
     * Get the Top Left corner of the item
     *
     * @return Vector2 the Top Left corner of the item
     */
    public Vector2 getTopLeft() {
        return new Vector2(this.position.x, this.position.y);
    }

    /**
     * Get the Top Right corner of the item
     *
     * @return Vector2 the Top Right corner of the item
     */
    public Vector2 getTopRight() {
        return new Vector2(this.position.x + this.getSize().width, this.position.y);
    }

    /**
     * Get the Bottom Left corner of the item
     *
     * @return Vector2 the Bottom Left corner of the item
     */
    public Vector2 getBottomLeft() {
        return new Vector2(this.position.x, this.position.y + this.getSize().height);
    }

    /**
     * Get the Top Center point of the item
     *
     * @return Vector2 the Top Center point of the item
     */
    public Vector2 getTopCenter() {
        return new Vector2(this.position.x + this.getSize().width / 2, this.position.y);
    }

    /**
     * Get the Bottom Center point of the item
     *
     * @return Vector2 the Bottom Center point of the item
     */
    public Vector2 getBottomCenter() {
        return new Vector2(this.position.x + this.getSize().width / 2, this.position.y + this.getSize().height);
    }

    /**
     * Get the Left Center point of the item
     *
     * @return Vector2 the Left Center point of the item
     */
    public Vector2 getLeftCenter() {
        return new Vector2(this.position.x, this.position.y + this.getSize().height / 2);
    }

    /**
     * Get the Right Center point of the item
     *
     * @return Vector2 the Right Center point of the item
     */
    public Vector2 getRightCenter() {
        return new Vector2(this.position.x + this.getSize().width, this.position.y + this.getSize().height / 2);
    }

    /**
     * Determine if the item is colliding with another item
     *
     * @param item the item to check collision with
     * @return boolean true if the item is colliding with another item, false if not
     */
    public boolean isColliding(Item item) {
        if (item == null) return false;
        return this.getTopLeft().x < item.getBottomRight().x && this.getBottomRight().x > item.getTopLeft().x &&
                this.getTopLeft().y < item.getBottomRight().y && this.getBottomRight().y > item.getTopLeft().y;
    }

    /**
     * Determine if the given point is inside the item
     *
     * @param point the point to check
     * @return boolean true if the point is inside the item, false if not
     */
    public boolean isInside(Point point) {
        if (point == null) return false;
        return this.getTopLeft().x <= point.x && this.getBottomRight().x >= point.x &&
                this.getTopLeft().y <= point.y && this.getBottomRight().y >= point.y;
    }

    /**
     * Determines if the given item's bottom section is colliding with this item
     *
     * @param item the item to check collision with
     * @return boolean true if the item's bottom section is colliding with this item, false if not
     */
    public boolean isCollidingFromTop(Item item) {
        return this.getTopLeft().x <= item.getBottomRight().x && this.getTopRight().x >= item.getBottomLeft().x &&
                this.getTopLeft().y <= item.getBottomRight().y && this.getBottomRight().y >= item.getBottomLeft().y;
    }

    /**
     * Get the image of the item
     *
     * @return Image the image of the item
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image of the item
     *
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
