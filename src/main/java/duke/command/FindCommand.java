package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String keyword = Parser.getFindKeyword(userInput);
        ArrayList<Task> foundTasks = tasks.filterTasks(keyword);
        ui.printFoundTasks(foundTasks);
    }
}
