package james.command;

import james.exception.JamesException;

import james.jamesbot.Storage;

import james.task.Task;
import james.task.TaskList;

import james.jamesbot.Ui;

/**
 * Marks or unmarks a task in the task list.
 */
public class AddMarkCommand extends Command {
    /** The index of the task to be marked or unmarked. */
    private int index;

    /** The user command parsed into MarkCommand. */
    private String userCommand;

    /** The type of command, mark or unmark. */
    private String taskType;

    /**
     * Constructor for a MarkCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddMarkCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0];
        this.index = Integer.parseInt(taskData[1].trim()) - 1;
    }

    /**
     * Executes the MarkCommand which marks or unmarks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task index is out of bounds.
     */
    public String execute (TaskList tasks, Ui ui, Storage storage) throws JamesException {
        if (index >= tasks.size()) {
            throw new JamesException("Looks like task " + (index + 1) + "does not exist"
                    + "please check that you have keyed in the right task number");
        }

        String response = "OOPS! something went wrong while marking your task";

        switch (taskType) {
        case "mark":
            Task markedTask = tasks.get(index);
            markedTask.setIsDone(true);
            storage.save(tasks.taskListToStoreString());
            response = ui.markTask(markedTask);
            break;
        case "unmark":
            Task unmarkedTask = tasks.get(index);
            unmarkedTask.setIsDone(false);
            storage.save(tasks.taskListToStoreString());
            response = ui.unmarkTask(unmarkedTask);
            break;
        default:
            assert false: "Should not reach here for MarkCommand"; // Will not reach here
        }


        return response;
    }

    /**
     * Returns whether MarkCommand exits the program.
     *
     * @return false as MarkCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
