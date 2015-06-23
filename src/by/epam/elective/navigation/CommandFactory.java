package by.epam.elective.navigation;

public class CommandFactory {
    public static Command getCommand(String page) {
        Commands commands = Commands.valueOf(page.toUpperCase());
        return commands.getCommand();
    }
}
