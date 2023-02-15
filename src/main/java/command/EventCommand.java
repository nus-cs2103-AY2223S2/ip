package command;

import task.Event;
import task.TaskManager;
import util.DukeException;
import util.DukeUI;

/**
 * Executes add event task command.
 */
public class EventCommand extends Command {
    private final String description;

    /**
     * Executes command to add an event task to the list.
     * <p>
     * @param description
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Adds an event type task to the list.
     * <p>
     * String input is parsed to extract start and end
     * dates and timings of the event.
     * <p>
     * @param taskManager
     * @return Successful add event message
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            String[] arr = this.description.split(" /from ");
            String[] time = arr[1].split(" /to ");
            String start = time[0];
            String end = time[1];
            Event event = new Event(arr[0], false, start, end);
            assert taskManager != null;
            taskManager.addTaskToList(event);
            return DukeUI.eventTaskMessage(event, taskManager.getTaskArraySize());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.eventFormatErrorMessage());
        }
    }
}
