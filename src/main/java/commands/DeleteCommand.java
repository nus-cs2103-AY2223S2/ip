package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            System.out.println("Oopsies.. Seems like that task does not exist :(");
        } else {
            Task toDelete = list.getTask(taskIndex);
            list.deleteTask(taskIndex);
            ui.printOutput("Ok. I've removed this task:\n\t\t" + toDelete.toString() + "\n\t Now you have " + list.getSize() + " tasks in the list.");
            storage.saveListToFile(list, ui);
        }
    }
}
