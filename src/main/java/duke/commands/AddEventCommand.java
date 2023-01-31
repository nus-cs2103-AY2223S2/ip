package duke.commands;
import java.time.LocalDateTime;

import duke.taskers.Event;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Command for adding events.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private final Event event;

    /**
     * Constructor.
     *
     * @param desc Description of task.
     * @param isDone If task is done or not.
     * @param startDate Starting date of event.
     * @param endDate Ending date of event.
     */
    public AddEventCommand(String desc, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super();
        this.event = new Event(desc, isDone, startDate, endDate);
    }

    /**
     * Executes the command.
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addItem(this.event);
        storage.writeToFile(this.event);
        return ui.addItemResponse(this.event, taskList.getList());

    }


    @Override
    public boolean isExit() {
        return false;
    }

}
