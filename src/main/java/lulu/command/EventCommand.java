package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import lulu.task.Task;
import lulu.task.Event;

import lulu.exception.LuluException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String rest) throws LuluException {
        if (rest.isEmpty()) {
            throw new LuluException("(=✖ ᆺ ✖=) The description of a event cannot be empty.");
        }
        String[] eventDetails = rest.split("/from");
        if (eventDetails.length == 1) {
            throw new LuluException("(=✖ ᆺ ✖=) Please include a timing using /from and /to");
        }
        String[] toFrom = eventDetails[1].split("/to");
        if (toFrom.length == 1) {
            throw new LuluException("(=✖ ᆺ ✖=) You included /from but missed out /to");
        }
        this.description = eventDetails[0];
        this.from = toFrom[0];
        this.to = toFrom[1];
    }

    /**
     * This method adds an event task to tasks upon execution
     *
     * @param tasks   the TaskList to be added with an event task
     * @param ui      the UI that displays messages
     * @param storage the Storage is not relevant in this command
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        Task t = new Event(description, from, to);
        tasks.add(t);
        return ui.showContainer(ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize()));
    }
}
