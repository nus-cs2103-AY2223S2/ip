package voile.model.command;

import java.util.Comparator;
import java.util.stream.Collectors;

import voile.model.task.DeadlineTask;
import voile.model.task.TaskList;

/**
 * Represents a command that sorts groups of {@code Task}s.
 */
public class SortCommand extends Command {

    /**
     * {@inheritDoc}
     */
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
        return obj instanceof SortCommand;
    }
}
