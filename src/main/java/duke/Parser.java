package duke;

import duke.command.*;

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

    public Command parse(String inputCommand, String[] currentInputArray) throws InvalidCommandException{
        switch (inputCommand) {
        case EXIT_COMMAND:
            return new ExitCommand();
        case LIST_COMMAND:
            return new ListCommand(this.listOfTasks);
        case DELETE_COMMAND:
            return new DeleteCommand(currentInputArray, this.listOfTasks);
        case MARK_COMMAND:
            return new MarkCommand(currentInputArray, this.listOfTasks);
        case UNMARK_COMMAND:
            return new UnmarkCommand(currentInputArray, this.listOfTasks);
        case TODO_COMMAND:
            return new TodoCommand(currentInputArray, this.listOfTasks);
        case EVENT_COMMAND:
            return new EventCommand(currentInputArray, this.listOfTasks);
        case DEADLINE_COMMAND:
            return new DeadlineCommand(currentInputArray, this.listOfTasks);
        case FIND_COMMAND:
            return new FindCommand(currentInputArray, this.listOfTasks);
        default:
            throw new InvalidCommandException("");
        }
    }
}
