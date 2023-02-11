package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * This is a command to add a Todo Task to Duke.
 */
public class AddTodoCommand extends AddCommand {
    private String description;

    /**
     * Constructor for AddTodoCommand class.
     *
     * @param description the description of the Todo Task created.
     */
    public AddTodoCommand(String description) {
        super("T");
        this.description = description;
    }

    /**
     * Adds a Todo Task into a TaskList.
     *
     * @param taskList the TaskList storing all Task.
     * @param ui the Ui for handling inputs/outputs.
     * @param storage the Storage responsible for reading/writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = taskList.addNewTodo(this.description);
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            return Ui.showFatalError("Error in saving data.\nReboot Duke and try again");
        }
        return response;
    }

    /**
     * Checks if the given Object equals to this.
     *
     * @param o the Object being compared.
     * @return true if o is an instance of this and has the same description.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddTodoCommand)) {
            return false;
        }

        AddTodoCommand c = (AddTodoCommand) o;
        return this.description.equals(c.description);
    }
}
