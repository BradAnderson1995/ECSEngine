package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.ComponentMapper;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.CollisionComponent;
import com.brad.AshleyTest.ecs.components.DynamicBodyComponent;
import com.brad.AshleyTest.ecs.components.ExpireComponent;
import com.brad.AshleyTest.ecs.components.HideComponent;
import com.brad.AshleyTest.ecs.components.KinematicBodyComponent;
import com.brad.AshleyTest.ecs.components.MapComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.NoBlendComponent;
import com.brad.AshleyTest.ecs.components.StaticBodyComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * Created by brad on 3/25/15.
 */
public class Mappers
{
    public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<AssetComponent> asset = ComponentMapper.getFor(AssetComponent.class);
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<HideComponent> hide = ComponentMapper.getFor(HideComponent.class);
    public static final ComponentMapper<MapComponent> map = ComponentMapper.getFor(MapComponent.class);
    public static final ComponentMapper<MotionComponent> motion = ComponentMapper.getFor(MotionComponent.class);
    public static final ComponentMapper<NoBlendComponent> noBlend = ComponentMapper.getFor(NoBlendComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<CameraControlComponent> cameraControl = ComponentMapper.getFor(CameraControlComponent.class);
    public static final ComponentMapper<ExpireComponent> expire = ComponentMapper.getFor(ExpireComponent.class);
    public static final ComponentMapper<DynamicBodyComponent> dynamicBody = ComponentMapper.getFor(DynamicBodyComponent.class);
    public static final ComponentMapper<StaticBodyComponent> staticBody = ComponentMapper.getFor(StaticBodyComponent.class);
    public static final ComponentMapper<KinematicBodyComponent> kinematicBody = ComponentMapper.getFor(KinematicBodyComponent.class);
}
