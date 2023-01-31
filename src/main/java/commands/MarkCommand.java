package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(CommandType type, int taskIndex) {
        super(type);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            System.out.println("Oopsies.. Seems like that task does not exist :(");
        } else {
            Task currentTask = list.getTask(taskIndex);
            if (this.getType().equals(CommandType.MARK)) {
                currentTask.markAsDone();
            } else {
                currentTask.markAsNotDone();
            }
            ui.printOutput("Great :D I knew you could do it! I've marked this task as done:\n\t\t" + currentTask.toString());
            storage.saveListToFile(list, ui);
        }
    }
}
