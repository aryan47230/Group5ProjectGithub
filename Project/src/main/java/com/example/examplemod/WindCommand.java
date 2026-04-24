package com.example.examplemod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class WindCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Admin wind commands (require Op level 2)
        dispatcher.register(Commands.literal("wind")
            .requires(source -> source.hasPermission(2))
            .then(Commands.literal("trigger")
                .executes(WindCommand::triggerWindSelf)
                .then(Commands.argument("player", EntityArgument.player())
                    .executes(WindCommand::triggerWindPlayer)))
            .then(Commands.literal("enable")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(WindCommand::setWindEnabled)))
            .then(Commands.literal("status")
                .executes(WindCommand::getWindStatus))
        );

        // Debug wind command (no permissions required)
        dispatcher.register(Commands.literal("windtest")
            .then(Commands.literal("trigger")
                .executes(WindCommand::debugTriggerWind))
            .then(Commands.literal("status")
                .executes(WindCommand::debugGetStatus))
        );
    }

    private static int triggerWindSelf(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("§cOnly players can use this command!"));
            return 0;
        }

        WindEventHandler.triggerWindEventForPlayer(player);
        return 1;
    }

    private static int triggerWindPlayer(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, "player");
            WindEventHandler.triggerWindEventForPlayer(targetPlayer);

            context.getSource().sendSuccess(() ->
                Component.literal("§6⚡ Wind event triggered for " + targetPlayer.getName().getString()), true);

            return 1;
        } catch (Exception e) {
            context.getSource().sendFailure(Component.literal("§cPlayer not found!"));
            return 0;
        }
    }

    private static int setWindEnabled(CommandContext<CommandSourceStack> context) {
        boolean enabled = BoolArgumentType.getBool(context, "enabled");
        WindEventHandler.setWindEventsEnabled(enabled);

        String status = enabled ? "§aENABLED" : "§cDISABLED";
        context.getSource().sendSuccess(() ->
            Component.literal("§6Wind events are now " + status), true);

        return 1;
    }

    private static int getWindStatus(CommandContext<CommandSourceStack> context) {
        boolean enabled = WindEventHandler.areWindEventsEnabled();
        String status = enabled ? "§aENABLED" : "§cDISABLED";

        context.getSource().sendSuccess(() ->
            Component.literal("§6Wind Event Status: " + status), false);

        return 1;
    }

    // Debug commands (no permissions required)
    private static int debugTriggerWind(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("§cOnly players can use this command!"));
            return 0;
        }

        WindEventHandler.triggerWindEventForPlayer(player);
        return 1;
    }

    private static int debugGetStatus(CommandContext<CommandSourceStack> context) {
        boolean enabled = WindEventHandler.areWindEventsEnabled();
        String status = enabled ? "§aENABLED" : "§cDISABLED";

        context.getSource().sendSuccess(() ->
            Component.literal("§6[DEBUG] Wind Event Status: " + status), false);

        return 1;
    }
}