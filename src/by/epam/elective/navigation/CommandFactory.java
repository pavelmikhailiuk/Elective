package by.epam.elective.navigation;

import by.epam.elective.exception.TechnicalException;

public class CommandFactory {
    public static Command getCommand(String commandName) throws TechnicalException {
        Command command=null;
        try {
            Commands commands = Commands.valueOf(commandName.toUpperCase());
             command = commands.getCommand();
        } catch (EnumConstantNotPresentException e){
            throw new TechnicalException("Command name parameter is wrong",e);
        }
        return command;
    }
}
