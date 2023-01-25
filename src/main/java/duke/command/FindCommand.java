package duke.command;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a find command that is entered by the user to find tasks related to a key phrase.
 */
public class FindCommand extends Command {
    /** Key phrase with this find command. */
    private String keyPhrase;

    /** Task list containing all the tasks. */
    private TaskList tasks;


    /**
     * Constructs a <code>FindCommand</code>.
     *
     * @param ui The Ui to allow the command to print messages to the user.
     * @param keyPhrase The phrase to look up in task names.
     * @param tasks The lists of all available tasks.
     */
    public FindCommand(Ui ui, String keyPhrase, TaskList tasks) {
        super(ui);
        this.keyPhrase = keyPhrase;
        this.tasks = tasks;
    }

    /**
     * Prints out tasks whose names contain any words mentioned in the key phrase
     */
    @Override
    public void runCommand() {
        Ui.printStraightLine();
        ui.printStatement("Tasks whose name contains \"" + keyPhrase + "\":\n");

        //Iterate through each task in the task list
        int count = 1;
        for (int i = 0; i < tasks.getSizeOfTaskList(); i = i + 1) {
            Task currentTask = tasks.getTask(i);
            String taskName = currentTask.getNameOfTask();
            if (taskName.contains(keyPhrase)) {
                ui.printStatement(count + ". " + currentTask.getStatusOfTaskInString());
                count += 1;
            }
        }

        //No result
        if (count == 1) {
            ui.printStatement("There is no such task.");
        }
        Ui.printStraightLine();
    }
}
