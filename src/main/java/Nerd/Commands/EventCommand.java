package Nerd.Commands;

import Nerd.entities.Event;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.EventCommand of the Chat bot.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructor of Event commands
     *
     * @param description The description of the task.
     * @param from        The date the event starts.
     * @param to          The date the event ends.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.to = to;
        this.from = from;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a EventCommand Task.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return A string output from processing the event task.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        Event event = new Event(description, from, to);
        list.addTask(event);
        String response = ui.printEventResponse(event.toString(), list.getSize());
        return response;
    }
}
