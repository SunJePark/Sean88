package System.Cart;

import java.util.UUID;

public class Aperture {
    private String element;
    private UUID apertureId;
    private int x;
    private int y;
    private int width;
    private int height;

    protected Aperture(String element, int x, int y, int width, int height) {
        if (element == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }
        this.element = element;
        this.apertureId = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getElement() {
        return this.element;
    }

    public UUID getApertureId() {
        return this.apertureId;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
