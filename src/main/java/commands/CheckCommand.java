package commands;

import java.time.LocalDate;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

public class CheckCommand extends Command {
    private LocalDate date;
    public CheckCommand(LocalDate date) {
        this.date = date;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList taskContainDateList = new TaskList();
        for (Task t: taskList) {
            if (t.contains(this.date)) {
                taskContainDateList.add(t);
            }
        }

        int len = taskContainDateList.size();
        ui.printResponse("You have " + (len == 0 
                                        ? "no tasks" 
                                        : len + (len > 1 
                                                ? " task" 
                                                : " tasks")) 
                        + " on " + this.date.toString()
                        +  taskContainDateList.getAllAsString());
        
    }
}
