package duke.command;

import duke.util.container.TaskList;

public class UniqueCommand extends Command {

    @Override
    public String execute(TaskList list) {
        return "Here are the descriptions in your lists:\n"
                + list.listUniqueTaskDescriptionsWithCounts();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UniqueCommand;
    }
}
