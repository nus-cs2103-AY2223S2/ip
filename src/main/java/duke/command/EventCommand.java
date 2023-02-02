package duke.command;

import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import java.time.DateTimeException;

public class EventCommand extends Command {
    private String[] currentInputArray;
    private TaskList listOfTasks;

    public EventCommand(String[] currentInputArray, TaskList listOfTasks) {
        this.currentInputArray = currentInputArray;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void handleCommand(Ui ui) {
        this.currentInputArray = this.currentInputArray[1].split(" /from ", 2);
        String description = this.currentInputArray[0];
        this.currentInputArray = this.currentInputArray[1].split(" /to ", 2);
        String from = this.currentInputArray[0];
        String to = this.currentInputArray[1];
        Task currentTask;
        try {
            currentTask= new Event(description, from, to);
        } catch (DateTimeException e) {
            System.out.println(e);
            return;
        }
        this.listOfTasks.add(currentTask);
        ui.showAdd(this.listOfTasks, currentTask.toString());
    }
}
