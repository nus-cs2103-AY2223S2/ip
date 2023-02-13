package duke.command;

import duke.util.container.TaskList;

public class ClearCommand extends Command {

    @Override
    public String execute(TaskList list) {
        list.clear();
        return "I've cleared your task list! Now your list is empty";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClearCommand;
    }

}
