package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This is a command to add a Deadline Task to Duke.
 */
public class AddDeadlineCommand extends AddCommand {
    private String description;
    private LocalDate by;

    /**
     * Constructor of AddDeadlineCommand class.
     *
     * @param description description of the Deadline Task created.
     * @param by the date that the Deadline Task will hold.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        super("D");
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a Deadline Task into a TaskList.
     *
     * @param taskList the TaskList storing all Task.
     * @param ui the Ui for handling inputs/outputs.
     * @param storage the Storage responsible for reading/writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = taskList.addNewDeadline(this.description, this.by);
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
     * @return true if o is an instance of this and has the same description and by.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddDeadlineCommand)) {
            return false;
        }

        AddDeadlineCommand c = (AddDeadlineCommand) o;
        return this.description.equals(c.description) && this.by.equals(c.by);
    }
}
