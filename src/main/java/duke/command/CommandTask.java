package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.task.Ui;

public class CommandTask extends Command{
    private final Task task;
    public CommandTask(String command, Task task) {
        super(command);
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        System.out.println("added: " + task.toString() + "\n");
        String len = (taskList.getLength() == 1 ? taskList.getLength() + " task" : taskList.getLength() + " tasks");
        System.out.println("Now you have " + len + " in the list.");

        storage.writeArray(taskList);
    }
}
