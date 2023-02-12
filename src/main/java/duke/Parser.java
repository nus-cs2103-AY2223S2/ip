package duke;

import duke.commands.*;
import duke.exceptions.InvalidCommandException;

public class Parser {
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String DELETE_COMMAND = "delete";
    final static String MARK_COMMAND = "mark";
    final static String UNMARK_COMMAND = "unmark";
    final static String TODO_COMMAND = "todo";
    final static String EVENT_COMMAND = "event";
    final static String DEADLINE_COMMAND = "deadline";
    final static String FIND_COMMAND = "find";

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
            return new ListCommand(this.listOfTasks);
        case DELETE_COMMAND:
            return new DeleteCommand(input, this.listOfTasks);
        case MARK_COMMAND:
            return new MarkCommand(input, this.listOfTasks);
        case UNMARK_COMMAND:
            return new UnmarkCommand(input, this.listOfTasks);
        case TODO_COMMAND:
            return new TodoCommand(input, this.listOfTasks);
        case EVENT_COMMAND:
            return new EventCommand(input, this.listOfTasks);
        case DEADLINE_COMMAND:
            return new DeadlineCommand(input, this.listOfTasks);
        case FIND_COMMAND:
            return new FindCommand(input, this.listOfTasks);
        default:
            throw new InvalidCommandException("");
        }
    }
}
