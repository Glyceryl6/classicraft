package nameless.classicraft.common.block.entity.crop;

import nameless.classicraft.common.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class CarrotBlockEntity extends CropBlockEntity {
    public CarrotBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CARROT.get(), pWorldPosition, pBlockState);
    }
}
