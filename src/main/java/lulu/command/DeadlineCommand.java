package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import lulu.task.Task;
import lulu.task.Deadline;

import lulu.exception.LuluException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;
    public DeadlineCommand(String rest) throws LuluException {
        if (rest.isEmpty()) {
            throw new LuluException("(=✖ ᆺ ✖=) The description of a deadline cannot be empty.");
        }
        String[] deadlineDetails = rest.split("/by");
        if (deadlineDetails.length == 1) {
            throw new LuluException("(=✖ ᆺ ✖=) Please include a deadline using /by");
        }
        this.description = deadlineDetails[0];
        this.by = deadlineDetails[1];
    }
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = new Deadline(description, by);
        tasks.add(t);
        ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize());
    }
}
