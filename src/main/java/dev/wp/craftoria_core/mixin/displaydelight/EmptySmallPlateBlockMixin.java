package dev.wp.craftoria_core.mixin.displaydelight;

import com.jkvin114.displaydelight.block.EmptyPlateBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;

import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;

@Mixin(EmptyPlateBlock.class)
public abstract class EmptySmallPlateBlockMixin {
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return ((Block) (Object) this).defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
