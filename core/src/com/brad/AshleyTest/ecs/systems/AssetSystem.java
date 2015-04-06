package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.components.MapComponent;
import com.brad.AshleyTest.ecs.components.SoundComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;
import com.brad.AshleyTest.framework.screen.LoadingScreen;

import java.util.HashMap;

/**
 * Created by brad on 3/26/15.
 */
public class AssetSystem extends EntitySystem implements EntityListener, Disposable
{
    public boolean assetsLoaded = false;
    HashMap<String, TextureAtlas> atlases;
    Array<String> textures;
    Array<String> maps;
    HashMap<String, Sound> sounds;
    HashMap<String, Music> music;
    HashMap<String, BitmapFont> fonts;
    AssetManager manager;
    Engine engine;
    float scale;
    //    private Family assetFamily = Family.all(AssetComponent.class).get();
    private Family animationFamily = Family.all(AssetComponent.class, TextureComponent.class, AnimationComponent.class, TransformComponent.class).get();
    private Family textureFamily = Family.all(AssetComponent.class, TextureComponent.class, TransformComponent.class).get();
    private Family mapFamily = Family.all(AssetComponent.class, MapComponent.class).get();
    private Family soundFamily = Family.all(AssetComponent.class, SoundComponent.class).get();

    public AssetSystem(Engine engine, AssetManager manager, float scale) {
        super();
        this.manager = manager;
        this.engine = engine;
        this.atlases = new HashMap<String, TextureAtlas>();
        this.textures = new Array<String>();
        this.maps = new Array<String>();
        this.sounds = new HashMap<String, Sound>();
        this.music = new HashMap<String, Music>();
        this.fonts = new HashMap<String, BitmapFont>();
        this.scale = scale;
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    }

    public void addAtlas(String atlas) {
        atlases.put(atlas, new TextureAtlas());
    }

    public void addTexture(String texture) {
        textures.add(texture);
    }

    public void addMap(String map) {
        maps.add(map);
    }

    @Override
    public void update(float delta) {
        for (Entity entity : engine.getEntitiesFor(textureFamily)) {
            TextureComponent texture = Mappers.texture.get(entity);
            TransformComponent transform = Mappers.transform.get(entity);
            AssetComponent assets = Mappers.asset.get(entity);
            if (!assets.handledAssets) {
                for (String textureName : assets.textureName) {
                    texture.textures.get(textureName).setRegion(atlases.get(assets.atlasName).findRegion(textureName));
                }
                assets.handledAssets = true;
            }
            if (animationFamily.matches(entity)) {
                if (!Mappers.animation.get(entity).animationRunning) {
                    texture.region = texture.textures.get(texture.frameString);
                }
            } else {
                texture.region = texture.textures.get(texture.frameString);
            }
            if (transform.autosize) {
                transform.imageSize.set(texture.region.getRegionWidth(), texture.region.getRegionHeight());
                transform.size.set(transform.imageSize.x / scale, transform.imageSize.y / scale);
            }
            if (transform.autoCenterOrigin) {
                transform.setCenterOrigin();
            }
        }
    }

    public void loadAssets(LoadingScreen loadingScreen) {
        loadingScreen.loadAtlases(atlases);
        loadingScreen.loadMaps(maps);
        loadingScreen.loadTextures(textures);
    }

    public void retrieveAssets() {
        retrieveAtlases(manager, atlases);
//        retrieveTextures(manager, textures);
        assetsLoaded = true;

    }

    public void retrieveAtlases(AssetManager manager, HashMap<String, TextureAtlas> atlases) {
        for (String atlas : atlases.keySet()) {
            TextureAtlas loadedAtlas = manager.get(atlas);
            atlases.put(atlas, loadedAtlas);
        }
    }

//    public void retrieveTextures(AssetManager manager, HashMap<String, Texture> textures) {
//        for (String texture : textures.keySet()) {
//            Texture loadedTexture = manager.get(texture);
//            textures.put(texture, loadedTexture);
//        }
//    }

    @Override
    public void entityAdded(Entity entity) {
        if (assetsLoaded) {
            // TODO: Check for whether assets are loaded in already and load them in if not
            if (textureFamily.matches(entity)) {
                for (String textureName : Mappers.asset.get(entity).textureName) {
//                    Mappers.texture.get(entity).textures.put(textureName, atlases.get(Mappers.asset.get(entity).atlasName).findRegion(textureName));
                    Mappers.texture.get(entity).textures.get(textureName).setRegion(atlases.get(Mappers.asset.get(entity).atlasName).findRegion(textureName));
                }
                Mappers.asset.get(entity).handledAssets = true;
            }
        }
        if (mapFamily.matches(entity)) {
            Gdx.app.log("AssetSystem", Mappers.map.get(entity).mapName + " " + Boolean.toString(manager.isLoaded(Mappers.map.get(entity).mapName)));
            Mappers.map.get(entity).map = manager.get(Mappers.map.get(entity).mapName);
        }
        if (soundFamily.matches(entity)) {

        }
    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    @Override
    public void dispose() {
        for (TextureAtlas atlas : atlases.values()) {
            atlas.dispose();
        }
    }
}
