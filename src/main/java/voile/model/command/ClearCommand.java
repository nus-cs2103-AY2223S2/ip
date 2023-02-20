package voile.model.command;

import voile.model.task.TaskList;

public class ClearCommand extends Command {

    @Override
    public String execute(TaskList list) {
        list.clear();
        return "I've cleared your list. Now it is empty!";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClearCommand;
    }
}
