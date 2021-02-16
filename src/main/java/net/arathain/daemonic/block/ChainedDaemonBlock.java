package net.arathain.daemonic.block;

import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class ChainedDaemonBlock extends Block {

    public ChainedDaemonBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos blockPos, BlockState state, PlayerEntity playerEntity) {

        ItemStack itemStack = playerEntity.getMainHandStack();
        OriginComponent oc = ModComponents.ORIGIN.get(playerEntity);
        OriginLayer layer = OriginLayers.getLayer(new Identifier("origins", "origin"));
        Origin origin = OriginRegistry.get(new Identifier("daemonic", "cursed"));
        if (itemStack.getEnchantments().contains(Enchantments.SILK_TOUCH)) {

        } else {
            oc.setOrigin(layer, origin);
        }
    }
}

