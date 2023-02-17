package duke.commands;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * EventCommand adds Event object into TaskList.
 */
public class EventCommand extends Command {
    private final String[] tokens;

    public EventCommand(String[] tokens) {
        this.tokens = tokens;
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
        assert tokens.length == 3;
        String desc = tokens[0].trim();
        String from = tokens[1].trim();
        String to = tokens[2].trim();
        return new Event(desc, from, to);
    }
}
