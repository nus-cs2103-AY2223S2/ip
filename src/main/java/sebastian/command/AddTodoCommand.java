package sebastian.command;

import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to add a to-do
 */
public class AddTodoCommand extends AddTaskCommand {
    private final String instruction;

    public AddTodoCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Add a to-do to the task list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws LackOfArgumentException when user did not indicate a to-do to be added
     * @throws CannotWriteDataException when fail to write task list to the hard disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify a description for your Todo");
        } else if (insArr.length > 1) {
            String task = instruction.substring(5);
            int originalSize = taskList.getTotalTasks();
            String res = this.addTask(taskList.addTodo(false, task.trim()), taskList.getTotalTasks());
            assert taskList.getTotalTasks() == originalSize + 1;
            storage.writeToDisk(taskList);
            return ui.getFormattedString(res);
        } else {
            throw new Error("Internal Error");
        }
    }
}
