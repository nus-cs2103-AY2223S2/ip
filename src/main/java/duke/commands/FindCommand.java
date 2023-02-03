package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.exceptions.DukeExceptions;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindCommand implements Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        String query = input.substring(5);
        boolean hasMatch = false;
        int count = 1;

        StringBuilder sb = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(query)) {
                hasMatch = true;
                sb.append("\n").append(count).append(". " ).append(task.toString());
                count += 1;
            }
        }

        if(hasMatch) {
            ui.showMessage("here are the task(s) which match your query:" + sb.toString() + "\n");
        } else {
            ui.showMessage("looks like there aren't any tasks which match your query...\n");
        }
    }
}