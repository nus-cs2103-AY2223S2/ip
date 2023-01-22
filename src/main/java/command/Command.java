package command;

import dukeexception.commandexception.UnknownCommandException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
<<<<<<< HEAD
 * Command to be executed.
=======
 * Command that can be executed.
>>>>>>> master
 */
public abstract class Command {
    /**
     * Creates a new Command object based on the command given.
     * @param commandWordContent The content of the user response.
     * @return Command object that can be executed.
     */
    public static Command create(String[] commandWordContent) {
        String commandWord = commandWordContent[0];
        String commandContent = commandWordContent[1];
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(commandContent);
        case "mark":
        case "unmark":
            return new MarkCommand(commandWord, commandContent);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandWord.toUpperCase().charAt(0), commandContent);
        default:
            throw new UnknownCommandException();
        }
    };

    /**
     * Executes the given command and outputs to the ui.
     * @param taskList List of tasks.
     * @param ui User Interface, which deals with inputs and outputs.
     * @param storage Storage module, which deals with caching the data of the task list.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns whether the command is an exit command.
     * @return whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
