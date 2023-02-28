package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String wordsToFind) {
        super(wordsToFind);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tasksWithWords = tasks.find(this.command);
        if (tasksWithWords.isEmpty()) {
            throw new DukeException("There are no tasks found with words:\n" + this.command);
        }

        String str = "List:";
        for (int i = 1; i <= tasksWithWords.size(); i++) {
            str += String.format("\n\t%d. %s", i, tasksWithWords.get(i));
        }
        ui.print(str);
    }
}
