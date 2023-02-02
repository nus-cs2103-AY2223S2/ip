package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Deadline;
import duke.Task;
import java.time.DateTimeException;

public class DeadlineCommand extends Command {
    private String[] currentInputArray;
    private TaskList listOfTasks;

    public DeadlineCommand(String[] currentInputArray, TaskList listOfTasks) {
        this.currentInputArray = currentInputArray;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void handleCommand(Ui ui) {
        this.currentInputArray = this.currentInputArray[1].split(" /by ", 2);
        String description = this.currentInputArray[0];
        String by = this.currentInputArray[1];
        Task currentTask;
        try {
            currentTask = new Deadline(description, by);
        } catch (DateTimeException e) {
            System.out.println(e);
            return;
        }
        this.listOfTasks.add(currentTask);
        ui.showAdd(this.listOfTasks, currentTask.toString());
    }
}
