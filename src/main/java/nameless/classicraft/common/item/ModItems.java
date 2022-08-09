package nameless.classicraft.common.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.Classicraft;
import nameless.classicraft.common.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Classicraft.MOD_ID);

    public static final RegistryObject<Item> ROTTEN_FOOD = normal("rotten_food", p -> p.food(new FoodProperties.Builder().build()));

    public static final RegistryObject<Item> FRIDGE = block(ModBlocks.FRIDGE);

    private static RegistryObject<Item> normal(String name) {
        return normal(name, p -> p);
    }

    private static RegistryObject<Item> normal(String name, Function<Item.Properties, Item.Properties> func) {
        return REGISTER.register(name, () -> new Item(func.apply(base())));
    }

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return REGISTER.register(block.getId().getPath(), () -> new BlockItem(block.get(), base()));
    }

    private static Item.Properties base() {
        return new Item.Properties().tab(Classicraft.TAB);
    }
}
