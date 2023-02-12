package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private String input;
    private TaskList listOfTasks;

    public MarkCommand(String input, TaskList listOfTasks) {
        this.input = input;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String handleCommand() {
        String[] inputArgs = input.split(" ", 2);
        int taskNumber = Integer.parseInt(inputArgs[1]);
        Task currentTask = this.listOfTasks.get(taskNumber - 1);
        currentTask.markAsDone();
        return Ui.showMark(currentTask.toString());
    }
}
