package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that adds a Deadline Task to the list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Execute adding Deadline Task to list and storing it in Storage.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     * @return String to be displayed by duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = taskList.addDeadline(description, by);
        storage.saveState(taskList);
        return response;
    }
}
