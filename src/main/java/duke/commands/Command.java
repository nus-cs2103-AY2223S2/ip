package duke.commands;

import duke.tasks.TaskList;

/**
 * Commands that the user wants to execute are encapsulated in this class.
 */
public abstract class Command {

    protected TaskList taskList;
    protected String lineInput;


    /**
     * Constructor method.
     * @param taskList Task list to execute the command on
     * @param lineInput Command line that the user has enters
     */
    public Command(TaskList taskList, String lineInput) {
        this.taskList = taskList;
        this.lineInput = lineInput;
    }

    // Performs the action according to the type of command.
    public abstract void execute();
    
    // Acknowledges to the user that the command has been executed.
    public abstract void uiReply();
}
