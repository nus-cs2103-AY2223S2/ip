package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.TaskList;

/**
 * Abstract class for Command.
 */
public abstract class Command {
    protected String userInput;

    /**
     * Constructor for Command class.
     * @param userInput
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes user command.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     */
    public abstract String execute(TaskList tasks, Note notes) throws JenoException;

}
