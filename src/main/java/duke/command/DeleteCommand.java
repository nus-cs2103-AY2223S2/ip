package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Task;

public class DeleteCommand extends Command {
    private String[] currentInputArray;
    private TaskList listOfTasks;

    public DeleteCommand(String[] currentInputArray, TaskList listOfTasks) {
        this.currentInputArray = currentInputArray;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void handleCommand(Ui ui) {
        int taskNumber = Integer.parseInt(currentInputArray[1]);
        Task currentTask = this.listOfTasks.get(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + currentTask.toString());
        listOfTasks.remove(taskNumber - 1);
        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));
    }
}