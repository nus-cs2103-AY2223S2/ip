package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Task;

import java.time.DateTimeException;

public class DeadlineCommand extends Command {
    private String input;
    private TaskList listOfTasks;

    public DeadlineCommand(String input, TaskList listOfTasks) {
        this.input = input;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String handleCommand() {
        try {
            String[] inputArgs = input.split(" ", 2);
            inputArgs = inputArgs[1].split(" /by ", 2);        
            String description = inputArgs[0];
            String by = inputArgs[1];
            Task currentTask = new Deadline(description, by);
            listOfTasks.add(currentTask);
            return Ui.showAdd(this.listOfTasks, currentTask.toString());
        } catch (DateTimeException e) {
            return Ui.showError(e);
        }
    }
}
