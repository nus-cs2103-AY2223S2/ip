package duke.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.Duke;
import duke.Utils;
import duke.parser.Arguments;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand() {
        super("find");
    }

    @Override
    protected void executeInternal(Arguments args, Duke instance) throws ValidationException {
        validate(args.getNumOfArgs() > 1, "Need a string to search for!");

        TaskList tasks = instance.getTaskList();
        String searchStr = Utils.stringJoiner(args.getOriginalArgs(), 1);

        List<Task> filteredTasks = tasks.stream().filter(t -> {
            String desc = t.getDescription();
            return desc.contains(searchStr);
        })
            .collect(Collectors.toUnmodifiableList());

        if (filteredTasks.size() == 0) {
            output("Could not find any tasks that matched '%s'", searchStr);
        } else {
            output("%d tasks matched '%s':\n%s",
                filteredTasks.size(),
                searchStr,
                Utils.flattenIterableWithIndex(filteredTasks, 1)
            );
        }
    }
}
