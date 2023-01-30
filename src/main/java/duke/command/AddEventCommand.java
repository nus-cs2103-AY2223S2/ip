package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.datetime.DateTime;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case of adding Event task
 */
public class AddEventCommand extends Command {
    private String fromString;
    private String toString;
    private String name;


    /**
     * Constructor to create AddEventCommand
     *
     * @param name Name of task
     * @param fromString Task's starting time in string
     * @param toString Task's ending time in string
     */
    public AddEventCommand(String name, String fromString, String toString) {
        this.fromString = fromString;
        this.toString = toString;
        this.name = name;
    }

    /**
     * Adds the Task to TaskList and storage, and output result string
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDateTime from = DateTime.parseDateTimeString(this.fromString);
            LocalDateTime to = DateTime.parseDateTimeString(this.toString);
            Event ev = new Event(this.name, from, to);
            tl.addTask(ev);
            storage.add(storage.getStorageTaskString(ev));
            return ui.showAddEventResult(ev.toString(), tl.getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for event, "
                    + "please follow deadline name /from datetime /to datetime(yyyy-mm-dd hh:mm)");
        }
    }
}
