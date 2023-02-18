package duke.commands;

import duke.backend.TaskList;

public class Bye extends Command {
    private TaskList tasklist;

    public Bye(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        tasklist.closeAndSave();
        return "Bye. Hope to see you soon!\n";
    }
}
