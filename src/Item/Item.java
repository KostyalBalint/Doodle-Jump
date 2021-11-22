package Item;

import math.Vector2;

import javax.swing.*;

/**
 * Item is an abstract class that is extended by all items that can be moved.
 * It stores the x and y coordinates of the item as double values, witch enables a more precise movement.
 */
public abstract class Item extends JButton {

    private Vector2 position;
    private Vector2 size;

    public Item(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
        this.setBounds((int)this.position.x, (int)this.position.y, (int)this.position.x, (int)this.position.y);
    }

    public void setX(float x) {
        this.position.x = x;
        this.setLocation((int)this.position.x, (int)this.position.y);
    }

    public void setY(float y) {
        this.position.y = y;
        this.setLocation((int)this.position.x, (int)this.position.y);
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

}
