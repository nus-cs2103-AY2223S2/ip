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
            String[] inputArgs = splitArgs(input);
            Task currentTask = new Deadline(inputArgs[0], inputArgs[1]);
            listOfTasks.addTask(currentTask);
            return Ui.showAdd(this.listOfTasks, currentTask.toString());
        } catch (DateTimeException e) {
            return Ui.showError(e);
        }
    }
    
    private String[] splitArgs(String input) {
        String[] inputArgs = input.split(" ", 2);
        inputArgs = inputArgs[1].split(" /by ", 2);
        return inputArgs;
    }
}
