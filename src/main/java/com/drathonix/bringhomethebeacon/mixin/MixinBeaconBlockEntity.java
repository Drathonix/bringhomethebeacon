package com.drathonix.bringhomethebeacon.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

//? >1.20.6 {
@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity {
    @Redirect(method = "applyEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;"))
    private static <T extends Entity> List<T> intercept(Level instance, Class<T> aClass, AABB aabb){
        aabb = aabb.setMinY(instance.getMinBuildHeight());
        return instance.getEntitiesOfClass(aClass,aabb);
    }
}
//?}
