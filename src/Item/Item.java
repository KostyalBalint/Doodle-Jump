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

    public Item(Vector2 position, Dimension size) {
        this.position = position;
        this.setSize(size);
        this.setBounds((int)this.position.x, (int)this.position.y, (int)this.getSize().width, (int)this.getSize().height);
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Vector2 getRenderCoordinate(){
        Camera camera = Camera.getInstance();
        Vector2 drawPosition = camera.getPosition().add(this.position);
        return drawPosition;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getBottomRight() {
        return new Vector2(this.position.x + this.getSize().width, this.position.y + this.getSize().height);
    }

    public Vector2 getTopLeft() {
        return new Vector2(this.position.x, this.position.y);
    }

    public Vector2 getTopRight() {
        return new Vector2(this.position.x + this.getSize().width, this.position.y);
    }

    public Vector2 getBottomLeft() {
        return new Vector2(this.position.x, this.position.y + this.getSize().height);
    }

    public Vector2 getTopCenter() {
        return new Vector2(this.position.x + this.getSize().width / 2, this.position.y);
    }

    public Vector2 getBottomCenter() {
        return new Vector2(this.position.x + this.getSize().width / 2, this.position.y + this.getSize().height);
    }

    public Vector2 getLeftCenter() {
        return new Vector2(this.position.x, this.position.y + this.getSize().height / 2);
    }

    public Vector2 getRightCenter() {
        return new Vector2(this.position.x + this.getSize().width, this.position.y + this.getSize().height / 2);
    }

    public boolean isColliding(Item item) {
        return this.getTopLeft().x < item.getBottomRight().x && this.getBottomRight().x > item.getTopLeft().x &&
                this.getTopLeft().y < item.getBottomRight().y && this.getBottomRight().y > item.getTopLeft().y;
    }

    public boolean isCollidingFromTop(Item item){
        return this.getTopLeft().x <= item.getBottomRight().x && this.getTopRight().x >= item.getBottomLeft().x &&
                this.getTopLeft().y <= item.getBottomRight().y && this.getBottomRight().y >= item.getBottomLeft().y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
