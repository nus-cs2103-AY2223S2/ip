package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class AddEventCommand extends AddCommand {
    private String description;
    private LocalDate from;
    private LocalDate to;

    public AddEventCommand(String description, LocalDate from, LocalDate to) {
        super("E");
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addNewEvent(this.description, this.from, this.to);
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            Ui.showFatalError("Error in saving data.\nReboot Duke and try again");
        }
    }
}
