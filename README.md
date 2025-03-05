# Création d'un plugin

Dans un premier temps, je vous invite à copier le projet là et à le modifier pour faire votre plugin, ça simplifiera la création du projet.

## Création d'un serveur sur votre PC pour tester
Il faut installer Java 23 (https://www.oracle.com/fr/java/technologies/downloads/#jdk23-windows) et télécharger le serveur PaperMC (https://api.papermc.io/v2/projects/paper/versions/1.21.4/builds/190/downloads/paper-1.21.4-190.jar).

Pour vérifier votre version actuelle de java, faire la commande suivante dans un terminal:
```bash
java -version
```

Ensuite, il faut créer un fichier nommé "start.bat" avec comme contenu le code suivant:
```bat
java -Xmx4G -Xms1G -jar paper-1.21.4-190.jar -nogui 
PAUSE
```

Il faudra créer aussi un fichier "eula.txt" avec comme contenu:
```txt
eula=true
```

Enfin, il suffit de lancer le fichier "start.bat" pour démarrer le serveur.

## Doc pour setup un projet: https://docs.papermc.io/paper/dev/project-setup

## Doc pour l'API (simple à comprendre tkt): https://docs.papermc.io/paper/dev/api

Je vous conseille d'utiliser l'IDE IntelliJ IDEA pour développer votre plugin.

## Création du projet
Télécharger l'extension sur IntelliJ IDEA (https://docs.papermc.io/paper/dev/project-setup#using-the-minecraft-development-intellij-plugin)

Suivre les étapes de la doc et configurer le plugin.yml avec les infos de base de votre plugin.

Exemple de configuration:
```yaml
name: plugin-exemple
version: '1.0-SNAPSHOT'
main: fr.arthurbr02.pluginExemple.PluginExemple
api-version: '1.21'
```

## Ajout d'une commande

Il faut l'ajouter dans le fichier plugin.yml

```yaml
commands:
    exemple:
        description: Commande d'exemple
        usage: /exemple
        permission: plugin-exemple.command.exemple
        aliases: [ex]
```

Ensuite il faut créer la classe de la commande

```java
public class ExempleCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        if (stack.getExecutor() instanceof Player) {
            // Si l'exécuteur est un joueur
            Player player = (Player) stack.getExecutor();

            // On affiche tous les arguments de la commande: par ex /exemple arg1 arg2 arg3
            StringBuilder argsStr = new StringBuilder();
            for (String arg : args) {
                argsStr.append(arg).append(" ");
            }

            // On envoie un message au joueur
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
```

Et enfin, il faut la déclarer dans la méthode _**onEnable**_ de la classe principale du plugin

```java
@Override
public void onEnable() {
    ....
    LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
    manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
        final Commands commands = event.registrar();
        commands.register("exemple", new ExempleCommand());
    });
    ....
}
```

## Gestion des événements

Les événements sont des actions qui se déclenchent à un moment précis du jeu. Par exemple, lorsqu'un joueur se connecte, lorsqu'un joueur casse un bloc, lorsqu'un joueur meurt, etc.
La liste complète des événements est disponible ici (peut-être pas à jour mais ça donne une idée): https://bukkit.org/threads/directory-list-of-events.112493/

Pour gérer un événement, il faut créer une classe qui implémente l'interface _**Listener**_ et qui contient des méthodes annotées avec _**@EventHandler**_.
Par exemple, pour gérer l'événement de connexion d'un joueur:

```java
public class ExempleEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("[Plugin Exemple] Bienvenue sur le serveur !");
    }
}
```

Ensuite il faut le déclarer dans la méthode _**onEnable**_ de la classe principale du plugin

```java
@Override
public void onEnable() {
    ....
    // Initialisation des événements
    getServer().getPluginManager().registerEvents(new ExempleEvent(), this);
    ....
}
```

Enfin, il faut compiler le plugin en fichier _**.jar**_ et le mettre dans le dossier _**plugins**_ du serveur pour le tester.

Normalement, en récupérant ce projet la compilation pour IntelliJ est configurée. 

Il suffit de faire: Menu gradle (icône d'éléphant) > Build > Double clique sur "jar" ou plus simplement de faire la commande suivante à la racine du projet:
```bash
.\gradlew jar
```

Le fichier compilé se trouvera dans le dossier build/libs du projet.

Quand c'est testé et validé, envoyez le moi pour que je le mette sur le serveur.
Si possible mettez votre code sur GitHub pour que je puisse le voir, le contrôler avant de le mettre sur le serveur et vous aider si besoin.

Amusez-vous bien !
