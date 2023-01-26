package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Ui;

public class CommandDelete extends Command{
    private int index;
    public CommandDelete(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf(taskList.getTask(index).toString() + "\n");
        taskList.deleteTask(index);
        String len = (taskList.getLength() == 1 ? taskList.getLength() + " task" : taskList.getLength() + " tasks");
        System.out.println("Now you have " + len + " in the list.");

        storage.writeArray(taskList);
    }
}
