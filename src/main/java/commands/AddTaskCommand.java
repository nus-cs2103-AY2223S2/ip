package commands;

import exceptions.DukeException;
import files.Storage;
import parsers.TaskInfoParser;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command which adds a task into a task list.
 */
public class AddTaskCommand extends Command {
    private String response;

    /**
     * Constructor to create a command which tells Duke to add a specific task to the task list.
     * @param response command line input to add task
     */
    public AddTaskCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of adding a task into the task list.
     * @param taskList task list to add task into
     * @param ui user interface
     * @param storage storage for reading and writing data to files
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = TaskInfoParser.parse(this.response);
            taskList.addTask(task);
            String reply = "Got it. Added this task, I have:";
            return String.format("%s\n %s\n%d tasks in the list, you have now!", reply,
                    task, taskList.getSize());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
