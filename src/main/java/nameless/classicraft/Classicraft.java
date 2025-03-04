package nameless.classicraft;

import nameless.classicraft.common.block.ModBlocks;
import nameless.classicraft.common.block.entity.ModBlockEntities;
import nameless.classicraft.common.item.ModItems;
import nameless.classicraft.common.menu.ModMenuTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author Nameless
 */
@Mod(Classicraft.MOD_ID)
public class Classicraft {

    public static final String MOD_ID = "classicraft";
    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @NotNull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CLASSIC_CRAFT.get());
        }
    };

    @SuppressWarnings("deprecation")
    public Classicraft() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        if (Items.DRIED_KELP.getFoodProperties() != null) {
            Items.DRIED_KELP.getFoodProperties().canAlwaysEat = true;
            Items.DRIED_KELP.getFoodProperties().nutrition = 0;
            Items.DRIED_KELP.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.CHORUS_FRUIT.getFoodProperties() != null) {
            Items.CHORUS_FRUIT.getFoodProperties().canAlwaysEat = true;
            Items.CHORUS_FRUIT.getFoodProperties().nutrition = 0;
            Items.CHORUS_FRUIT.getFoodProperties().saturationModifier = 0.0F;
        }
        if (Items.ROTTEN_FLESH.getFoodProperties() != null) {
            Items.ROTTEN_FLESH.getFoodProperties().canAlwaysEat = true;
            Items.ROTTEN_FLESH.getFoodProperties().nutrition = 0;
            Items.ROTTEN_FLESH.getFoodProperties().saturationModifier = 0.0F;
        }
        bus.addListener(this::renderRegister);
        ModItems.REGISTER.register(bus);
        ModBlocks.REGISTER.register(bus);
        ModMenuTypes.REGISTER.register(bus);
        ModBlockEntities.REGISTER.register(bus);
    }

    private void renderRegister(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_SOUL_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNLIT_SOUL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_UNLIT_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_UNLIT_SOUL_TORCH.get(), RenderType.cutout());
    }

}