package com.brad.AshleyTest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.brad.AshleyTest.AshleyTest;

public class DesktopLauncher
{
    public static void main(String[] arg) {
        // TexturePacker
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 512;
        settings.maxHeight = 512;
        TexturePacker.process(settings, "sprites/game", "sprites/packed/game", "game");
        TexturePacker.process(settings, "sprites/ui", "sprites/packed/ui", "ui");
        TexturePacker.process(settings, "sprites/env", "sprites/packed/env", "env");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new AshleyTest(), config);

        config.title = "AshleyTest";
        config.width = 640;
        config.height = 480;


    }
}
