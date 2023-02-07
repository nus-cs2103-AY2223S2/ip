package command;

import task.Event;
import task.TaskManager;

import util.DukeException;

/**
 * Executes add event task command.
 */
public class EventCommand extends Command {
    //private final TaskManager taskManager;
    private final String description;
    public EventCommand(String description) {
        //this.taskManager = taskManager;
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds an event type task to the list.
     * <p>
     * String input is parsed to extract start and end
     * dates and timings of the event.
     *
     * @return
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
            String str = String.format("I have added: %s !", event);
            String str2 = "There are currently " + taskManager.getTaskArraySize() + " task(s) in the list!";
            return str + System.lineSeparator() + str2;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please add a description, date and time e.g. party /from 12/2/23 1800 /to 12/2/23 2200");
        }
    }
}
