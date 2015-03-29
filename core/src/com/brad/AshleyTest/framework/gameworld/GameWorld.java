package com.brad.AshleyTest.framework.gameworld;

import com.brad.AshleyTest.framework.gameobjects.ObjectNode;

/**
 * Created by brad on 3/20/15.
 */
public class GameWorld
{
    public ObjectNode scene;
    public ObjectNode background;
    private int width;
    private int height;

    public GameWorld(int xWidth, int yHeight) {
        scene = new ObjectNode();  // Scene is drawn with blending
        background = new ObjectNode(); // Background is drawn without blending
        this.width = xWidth;
        this.height = yHeight;
    }

    public ObjectNode getScene() {
        return scene;
    }

    public ObjectNode getBackground() {
        return background;
    }

    public void setBackground(ObjectNode background) {
        this.background = background;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update() {
        background.update();
        scene.update();
    }
}
