package parser;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoesNotExistCommand;
import command.EventCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ToDoCommand;
import command.UnMarkComamnd;
import command.Command.Commands;

public class Parser {
    
    public static Command parse(String input) {
        Commands cmd = Command.getCommand(input);

        switch (cmd) {
            case LIST:
                return new ListCommand();
            case UNMARK:
                return new UnMarkComamnd(input);
            case MARK:
                return new MarkCommand(input);
            case TODO:
                return new ToDoCommand(input);
            case DEADLINE:
                return new DeadlineCommand(input);
            case EVENT:
                return new EventCommand(input);
            case DELETE:
                return new DeleteCommand(input);
            case EXIT:
                return new ExitCommand(input);
            default:
                return new DoesNotExistCommand();
        }
    }
}
