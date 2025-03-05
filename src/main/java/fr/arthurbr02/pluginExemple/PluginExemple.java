package fr.arthurbr02.pluginExemple;

import fr.arthurbr02.pluginExemple.command.ExempleCommand;
import fr.arthurbr02.pluginExemple.event.ExempleEvent;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginExemple extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Création des commandes
        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("exemple", new ExempleCommand());
        });

        // Initialisation des événements
        getServer().getPluginManager().registerEvents(new ExempleEvent(), this);

        getLogger().info("PluginExemple a été activé !");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
