package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This is a command to add an Event Task to Duke.
 */
public class AddEventCommand extends AddCommand {
    private String description;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor of AddEventCommand class.
     *
     * @param description description of Event Task created.
     * @param from the from date of Event Task created.
     * @param to the to date of Event Task created.
     */
    public AddEventCommand(String description, LocalDate from, LocalDate to) {
        super("E");
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an Event Task into a TaskList.
     *
     * @param taskList the TaskList storing all Task.
     * @param ui the Ui for handling inputs/outputs.
     * @param storage the Storage responsible for reading/writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = taskList.addNewEvent(this.description, this.from, this.to);
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
     * @return true if o is an instance of this and has the same description, from and to.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand c = (AddEventCommand) o;
        return this.description.equals(c.description) && this.from.equals(c.from) && this.to.equals(c.to);
    }
}
