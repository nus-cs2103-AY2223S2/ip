package command;

import java.util.Arrays;
import dukeexception.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {
    private String request;
    private Commands command;
    private boolean isExit = false;

    public enum Commands {
        LIST, FIND, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE, EXIT, DOES_NOT_EXIST
    };

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public Command(Commands command) {
        this.command = command;
    }

    public Command(String request) {
        this.request = request;
        this.command = getCommand(request);
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void toggleIsExit() {
        this.isExit = !this.isExit;
    }

    public String getRequest() {
        return this.request;
    }

    public Commands getCommand() {
        return this.command;
    }

    public String[] unwrap() throws CommandException {
        String[] values = this.request.split(" ");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, values.length));

        if (this.command != Commands.LIST && description.isEmpty()) {
            throw new CommandException("Command description cannot be empty!");
        }

        return new String[] { description };
    }

    public void checkRequestRequirement() throws CommandException {
    }

    public static Commands getCommand(String request) {
        String cmd = request.split(" ")[0];

        switch (cmd) {
        case "list":
            return Commands.LIST;
        case "find":
            return Commands.FIND;
        case "unmark":
            return Commands.UNMARK;
        case "mark":
            return Commands.MARK;
        case "todo":
            return Commands.TODO;
        case "deadline":
            return Commands.DEADLINE;
        case "event":
            return Commands.EVENT;
        case "delete":
            return Commands.DELETE;
        case "bye":
            return Commands.EXIT;
        default:
            return Commands.DOES_NOT_EXIST;
        }
    }
}
