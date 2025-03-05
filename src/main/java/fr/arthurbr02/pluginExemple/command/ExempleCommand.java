package fr.arthurbr02.pluginExemple.command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.Nullable;

import java.util.Collection;

public class ExempleCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        System.out.println("Commande exemple !");
        if (stack.getExecutor() instanceof Player) {
            Player player = (Player) stack.getExecutor();

            StringBuilder argsStr = new StringBuilder();
            for (String arg : args) {
                argsStr.append(arg).append(" ");
            }

            player.sendMessage("[Plugin Exemple] Commande exemple !\nArguments : " + argsStr);
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        return BasicCommand.super.suggest(commandSourceStack, args);
    }

    @Override
    public boolean canUse(CommandSender sender) {
        return BasicCommand.super.canUse(sender);
    }

    @Override
    public @Nullable String permission() {
        return BasicCommand.super.permission();
    }
}
