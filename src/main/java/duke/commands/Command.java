package duke.commands;

import duke.Ui;
import duke.exceptions.*;
import duke.task.TaskList;

/**
 * Command is the parent class of all other commands. It contains a ui and task list
 */
public abstract class Command {
    protected Ui ui;
    protected TaskList taskList;

    /**
     * Constructor for command
     * @param ui processes output to user based on input
     * @param taskList contains all present tasks
     */
    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * takes in user input and processes the input
     * @param userInput command given by user
     * @return String containing dukes' response to the user based on input given
     * @throws NeroException accounts for all possible exceptions that inherit from NeroException
     * and are thrown when processing inputs
     */
    public abstract String executeCommand(String userInput) throws NeroException;
}
