package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class AddTodoCommand extends AddCommand {
    private String description;
    public AddTodoCommand(String description) {
        super("T");
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addNewTodo(this.description);
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            Ui.showFatalError("Error in saving data.\nReboot Duke and try again");
        }
    }
}
