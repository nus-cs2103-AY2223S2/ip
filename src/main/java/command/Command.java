package command;

import exception.DukeException;

import response.Response;

import sys.Storage;
import sys.Ui;

import task.TaskList;


/**
 * Represents the command given by the user.
 */
public abstract class Command {

    private String regex;

    /**
     * Constructor for Command
     * @param regex Regex for command argument to match with.
     */
    Command(String regex) {
        this.regex = regex;
    }

    /**
     * Executes the command.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @throws DukeException If an invalid input is given.
     */
    public abstract Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns the matching regex for the command
     *
     * @return The matching regex for the command.
     */
    public String stringify() {
        return regex;
    }
}