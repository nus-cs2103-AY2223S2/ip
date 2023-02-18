package commands;

import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/** 
 * This class is used to add a new task
 */
public class AddCommand extends Command {
    /** Parsed user string */
    private String[]  args;

    /** 
     * Constructs command to create a new task by use of args 
     * to add to a tasklist
     * 
     * @param args user input that has been parsed
     */
    public AddCommand(String[] args) {
        this.args = args;
    }

    /**
     * Creates a new Task and add it to the tasklist
     * 
     * @param taskList the list to add the new task to
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = Task.createTask(args);
            taskList.add(t);
            ui.printResponse("The following task has been added to your list: \n    " + t + "\n\n"
                            + taskList.getSizeAsString());
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
