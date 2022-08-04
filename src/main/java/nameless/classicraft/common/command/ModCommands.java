package nameless.classicraft.common.command;

import com.mojang.brigadier.arguments.FloatArgumentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nameless.classicraft.Classicraft;
import nameless.classicraft.common.capability.ModCapabilities;
import nameless.classicraft.common.capability.rot.AbstractRot;
import nameless.classicraft.common.rot.RotManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModCommands {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        var dispatcher = event.getDispatcher();

        String valueKey = "value";
        String percentKey = "percent";

        dispatcher.register(
                literal(Classicraft.MOD_ID).then(
                        literal("rot").then(
                                literal(valueKey).then(
                                        argument(valueKey, FloatArgumentType.floatArg())
                                                .requires(commandSource -> commandSource.hasPermission(2))
                                                .executes(c -> {
                                                    CommandSourceStack source = c.getSource();
                                                    ServerPlayer player = source.getPlayerOrException();
                                                    ItemStack stack = player.getMainHandItem();
                                                    stack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                                                        float value = c.getArgument(valueKey, Float.class);
                                                        rot.setRotValue(value);
                                                        source.sendSuccess(successMsg(rot), false);
                                                    });
                                                    return 1;
                                                })
                                )
                        ).then(
                                literal(percentKey).then(
                                        argument(percentKey, FloatArgumentType.floatArg())
                                                .requires(commandSource -> commandSource.hasPermission(2))
                                                .executes(c -> {
                                                    CommandSourceStack source = c.getSource();
                                                    ServerPlayer player = source.getPlayerOrException();
                                                    ItemStack stack = player.getMainHandItem();
                                                    stack.getCapability(ModCapabilities.ROT).ifPresent(rot -> {
                                                        float percent = c.getArgument(percentKey, Float.class) / 100;
                                                        rot.setRotValue(rot.getHolder().getMax() * percent);
                                                        source.sendSuccess(successMsg(rot), false);
                                                    });
                                                    return 1;
                                                })
                                )
                        )
                ).then(
                        literal("baseSpeed").then(
                                argument("speed", FloatArgumentType.floatArg())
                                        .requires(source -> source.hasPermission(2))
                                        .executes(c -> {
                                            float speed = c.getArgument("speed", Float.class);
                                            RotManager.INSTANCE.setBaseSpeed(speed);
                                            c.getSource().sendSuccess(
                                                    new TextComponent("设置成功，当前为: %.1f".formatted(speed)),
                                                    false);
                                            return 1;
                                        })
                        )
                )
        );
    }

    static TextComponent successMsg(AbstractRot rot) {
        float current = rot.getRotValue();
        float max = rot.getHolder().getMax();

        return new TextComponent("设置成功，当前: %.1f / %.1f (%.1f%%)".formatted(current, max, current * 100 / max));
    }
}
