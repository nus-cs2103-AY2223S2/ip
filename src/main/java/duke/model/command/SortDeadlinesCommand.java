package duke.model.command;

import java.util.Comparator;
import java.util.stream.Collectors;

import duke.model.task.DeadlineTask;
import duke.model.task.TaskList;

public class SortDeadlinesCommand extends Command {

    @Override
    public String execute(TaskList list) {
        String content = list.stream()
                .filter(DeadlineTask.class::isInstance)
                .map(DeadlineTask.class::cast)
                .sorted(Comparator.comparing(DeadlineTask::getDeadline))
                .map(DeadlineTask::toString)
                .collect(Collectors.joining("\n"));
        return "Here are the deadlines in your list:\n" + content;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SortDeadlinesCommand;
    }
}
