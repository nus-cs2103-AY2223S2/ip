package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand implements Command {
    @Override
    public String getCommandName() {
        return "unmark";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^unmark ([0-9]+)$";
    }

    @Override
    public String getCommandPattern() {
        return "unmark <taskNo>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        int taskNo = Integer.parseInt(args[0]);

        if (taskNo > 0 && taskNo <= taskList.getTotalTasks()) {
            Task task = taskList.getTask(taskNo);
            task.setIsDone(false);

            ui.showLine();
            ui.showText("OK, I've marked this task as not done yet:");
            ui.showText(task.toString());
            ui.showLine();

            storage.save(taskList.getAllTasks());
        } else {
            ui.showLine();
            ui.showText("Invalid task number!");
            ui.showLine();
        }
    }
}
