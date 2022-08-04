package nameless.classicraft.common.block.entity.crop;

import lombok.Getter;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 */
public class PotatoBlockEntity extends CropBlockEntity {
    @Getter
    boolean poison;

    public PotatoBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.POTATO.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void tick() {
        super.tick();

        if (!isMax() && poison) {
            poison = false;
        }
    }

    @Override
    protected void replaceOnRot(Level level) {
        poison = true;
    }
}
