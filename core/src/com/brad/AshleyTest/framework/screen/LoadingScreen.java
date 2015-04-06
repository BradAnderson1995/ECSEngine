package com.brad.AshleyTest.framework.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.brad.AshleyTest.AshleyTest;

import java.util.HashMap;

/**
 * Created by brad on 3/16/15.
 */
public class LoadingScreen implements Screen
{
    protected AshleyTest game;
    protected Screen callingScreen;
    private Stage stage;
    private TextureAtlas atlas;

    public LoadingScreen(AshleyTest game, Screen screen) {
        this.game = game;
        this.callingScreen = screen;
        game.manager.load("sprites/packed/ui/ui.atlas", TextureAtlas.class);
        game.manager.finishLoading();
        atlas = game.manager.get("sprites/packed/ui/ui.atlas");
    }

    @Override
    public void show() {
        Image loadSprite = new Image(atlas.findRegion("loadScreen"));
        loadSprite.setPosition(160f, 80f);
        stage = new Stage();

        stage.addActor(loadSprite);
    }

    public void loadAtlases(HashMap<String, TextureAtlas> atlases) {
        for (String atlas : atlases.keySet()) {
            game.manager.load(atlas, TextureAtlas.class);
        }
    }

//    public void loadTextures(HashMap<String, Texture> textures) {
//        for (String texture : textures.keySet()) {
//            game.manager.load(texture, Texture.class);
//        }
//    }

    public void loadTextures(Array<String> textures) {
        for (String texture : textures) {
            game.manager.load(texture, Texture.class);
        }
    }

    public void loadMaps(Array<String> maps) {
        for (String map : maps) {
            Gdx.app.log("LoadingScreen", map);
            game.manager.load(map, TiledMap.class);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (game.manager.update()) {
            game.setScreen(callingScreen);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        game.manager.unload("sprites/packed/ui/ui.atlas");
    }

    @Override
    public void dispose() {

    }
}
