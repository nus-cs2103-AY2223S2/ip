package duke;

import duke.commands.*;
import duke.exceptions.InvalidCommandException;

public class Parser {
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String LIST_COMMAND_SHORT = "l";
    final static String DELETE_COMMAND = "delete";
    final static String DELETE_COMMAND_SHORT = "del";
    final static String MARK_COMMAND = "mark";
    final static String MARK_COMMAND_SHORT = "m";
    final static String UNMARK_COMMAND = "unmark";
    final static String TODO_COMMAND = "todo";
    final static String TODO_COMMAND_SHORT = "t";
    final static String EVENT_COMMAND = "event";
    final static String EVENT_COMMAND_SHORT = "e";
    final static String DEADLINE_COMMAND = "deadline";
    final static String DEADLINE_COMMAND_SHORT = "d";
    final static String FIND_COMMAND = "find";
    final static String FIND_COMMAND_SHORT = "f";

    private TaskList listOfTasks;
    
    public Parser(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public Command parse(String input) throws InvalidCommandException{
        String[] commands = input.split(" ");
        String inputCommand = commands[0];

        switch (inputCommand) {
        case EXIT_COMMAND:
            return new ExitCommand();
        case LIST_COMMAND:
        case LIST_COMMAND_SHORT:
            return new ListCommand(this.listOfTasks);
        case DELETE_COMMAND:
        case DELETE_COMMAND_SHORT:
            return new DeleteCommand(input, this.listOfTasks);
        case MARK_COMMAND:
        case MARK_COMMAND_SHORT:
            return new MarkCommand(input, this.listOfTasks);
        case UNMARK_COMMAND:
            return new UnmarkCommand(input, this.listOfTasks);
        case TODO_COMMAND:
        case TODO_COMMAND_SHORT:
            return new TodoCommand(input, this.listOfTasks);
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORT:
            return new EventCommand(input, this.listOfTasks);
        case DEADLINE_COMMAND:
        case DEADLINE_COMMAND_SHORT:
            return new DeadlineCommand(input, this.listOfTasks);
        case FIND_COMMAND:
        case FIND_COMMAND_SHORT:
            return new FindCommand(input, this.listOfTasks);
        default:
            throw new InvalidCommandException("");
        }
    }
}
