package Item;

import math.Vector2;
import Camera.Camera;

import java.awt.*;

/**
 * Item is an abstract class that is extended by all items that can be moved.
 * It stores the x and y coordinates of the item as double values, witch enables a more precise movement.
 */
public abstract class Item extends Rectangle {

    private Vector2 position;
    private Vector2 size;

    public Item(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
        this.setBounds((int)this.position.x, (int)this.position.y, (int)this.size.x, (int)this.size.y);
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
        return new Vector2(this.position.x + this.size.x, this.position.y + this.size.y);
    }

    public Vector2 getTopLeft() {
        return new Vector2(this.position.x, this.position.y);
    }

    public Vector2 getTopRight() {
        return new Vector2(this.position.x + this.size.x, this.position.y);
    }

    public Vector2 getBottomLeft() {
        return new Vector2(this.position.x, this.position.y + this.size.y);
    }

    public Vector2 getTopCenter() {
        return new Vector2(this.position.x + this.size.x / 2, this.position.y);
    }

    public Vector2 getBottomCenter() {
        return new Vector2(this.position.x + this.size.x / 2, this.position.y + this.size.y);
    }

    public Vector2 getLeftCenter() {
        return new Vector2(this.position.x, this.position.y + this.size.y / 2);
    }

    public Vector2 getRightCenter() {
        return new Vector2(this.position.x + this.size.x, this.position.y + this.size.y / 2);
    }

    public boolean isColliding(Item item) {
        return this.getTopLeft().x < item.getBottomRight().x && this.getBottomRight().x > item.getTopLeft().x &&
                this.getTopLeft().y < item.getBottomRight().y && this.getBottomRight().y > item.getTopLeft().y;
    }

    public boolean isCollidingFromTop(Item item){
        return this.getTopLeft().x < item.getBottomRight().x && this.getTopRight().x > item.getBottomLeft().x &&
                this.getTopLeft().y < item.getBottomRight().y && this.getBottomRight().y > item.getBottomLeft().y;
    }

}
