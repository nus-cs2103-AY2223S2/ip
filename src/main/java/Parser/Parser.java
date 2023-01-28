package Parser;
import Commands.*;
import Exceptions.InvalidInputException;
import Exceptions.NoDateException;
import Exceptions.NoDescriptionException;

public class Parser {
    protected static enum CommandList {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;
        public static CommandList findCommand(String name) {
            for (CommandList command : CommandList.values()) {
                if (command.name().equalsIgnoreCase(name)) {
                    return command;
                }
            } 
            return null;
        }
    }

    public Command parseCommand(String userInput) {
        CommandList command = CommandList.findCommand(userInput.split(" ")[0]);
        if (command == null) {
            throw new InvalidInputException(null);
        }
        switch (command) {
            case LIST:
                return new ReadCommand();
            case BYE:
                return new EndCommand();
            case MARK: 
                checkForDescription(userInput);
                return new MarkCommand(userInput);
            case UNMARK:
                checkForDescription(userInput);    
                return new UnmarkCommand(userInput);
            case TODO: 
                checkForDescription(userInput);
                return new TodoCommand(userInput);
            case DEADLINE: 
                checkForDescription(userInput);
                checkForDate(userInput);
                return new DeadlineCommand(userInput);
            case EVENT: 
                checkForDescription(userInput);
                checkForDate(userInput);
                return new EventCommand(userInput);
            case DELETE:
                checkForDescription(userInput);    
                return new DeleteCommand(userInput);
            default:
                throw new InvalidInputException(null);
        }
    }

    private void checkForDescription(String message) {
        String[] temp = message.split(" ");
        if (temp.length == 1) {
            throw new NoDescriptionException(temp[0], null);
        }
    }

    private void checkForDate(String message) {
        if (!message.contains("/by") && !(message.contains("/to") && message.contains("/from"))) {
            throw new NoDateException(message.split(" ")[0], null);
        }
    }
}