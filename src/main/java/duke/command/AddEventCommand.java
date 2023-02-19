package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that adds an Event Task to the list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Execute adding Event Task to list and storing it in Storage.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     * @return String to be displayed by duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = taskList.addEvent(description, from, to);
        storage.saveState(taskList);
        return response;
    }
}
