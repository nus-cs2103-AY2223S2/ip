package duke.commands;

import duke.utils.DukeIo;
import duke.tasks.TaskList;
import duke.tasks.Event;

/**
 * EventCommand adds Event object into TaskList.
 */
public class EventCommand extends Command {
    private final String[] TOKENS;

    public EventCommand(String[] tokens) {
        this.TOKENS = tokens;
    }

    /**
     * Method to add Deadline task into TaskList
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        Event e = createEvent();
        tasks.addTask(e);
        return dukeIo.notifySuccessAdd(e) + dukeIo.showCount();
    } 

    private Event createEvent() {
        String desc = TOKENS[0];
        String from = TOKENS[1];
        String to = TOKENS[2];
        return new Event(desc, from, to);
    }
}
