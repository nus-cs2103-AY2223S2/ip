package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.reply(taskList.addTodo(description));
            storage.saveState(taskList);
        } catch (DukeException e) {
            ui.reply(e.toString());
        }
    }
}
