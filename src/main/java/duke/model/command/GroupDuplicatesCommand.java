package duke.model.command;

import duke.model.task.TaskList;

public class GroupDuplicatesCommand extends Command {

    @Override
    public String execute(TaskList list) {
        return "Here are the descriptions in your lists:\n"
                + list.listUniqueTaskDescriptionsWithCounts();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GroupDuplicatesCommand;
    }
}
