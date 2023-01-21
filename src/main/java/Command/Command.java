package Command;

import DukeException.CommandException.UnknownCommandException;
import Storage.Storage;
import Task.TaskList;
import Ui.Ui;

public abstract class Command {
    public Command() {

    }

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

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        /**
         * Returns false by default, but overridden in Command.ExitCommand.
         */
        return false;
    }
}
