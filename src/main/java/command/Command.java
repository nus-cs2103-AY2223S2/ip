package command;

import dukeexception.commandexception.UnknownCommandException;
import gui.Gui;
import storage.Storage;
import task.TaskList;

/**
 * Command to be executed.
 */
public abstract class Command {
    /**
     * Creates a new Command object based on the command given.
     * @param commandWordContent The content of the user response.
     * @return Command object that can be executed.
     */
    public static Command create(String[] commandWordContent) {
        assert commandWordContent != null : "Command should not be null.";
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandWordContent[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        String commandContent = commandWordContent[1];
        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(commandContent);
        case MARK:
        case UNMARK:
            return new MarkCommand(commandType, commandContent);
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(commandType, commandContent);
        case FIND:
            return new FindCommand(commandContent.strip().split(" "));
        case REMIND:
            return new RemindCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Executes the given command and outputs to the ui.
     * @param taskList List of tasks.
     * @param gui User Interface, which deals with inputs and outputs.
     * @param storage Storage module, which deals with caching the data of the task list.
     */
    public abstract void execute(TaskList taskList, Gui gui, Storage storage);

    /**
     * Returns whether the command is an exit command.
     * @return whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
