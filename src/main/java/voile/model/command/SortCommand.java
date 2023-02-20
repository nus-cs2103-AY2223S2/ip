package voile.model.command;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import voile.model.task.DeadlineTask;
import voile.model.task.EventTask;
import voile.model.task.Task;
import voile.model.task.TaskList;
import voile.model.task.TodoTask;

/**
 * Represents a command that sorts groups of {@code Task}s.
 */
public class SortCommand extends Command {

    private static String generateResponse(List<String> tasks, String taskType) {
        int size = tasks.size();
        if (size == 0) {
            return String.format("There is no %s task in the library.", taskType);
        }
        String content = String.join("\n", tasks);
        return size == 1
                ? String.format("There is only one %s task in the library:\n%s", taskType, content)
                : String.format("Here are the %s tasks in the library:\n%s", taskType, content);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        Map<Class<? extends Task>, List<Task>> tasks = list
                .stream()
                .collect(Collectors.groupingBy(Task::getClass));

        List<String> todos = tasks.getOrDefault(TodoTask.class, List.of())
                .stream()
                .map(TodoTask.class::cast)
                .map(TodoTask::toString)
                .collect(Collectors.toList());

        List<String> deadlines = tasks.getOrDefault(DeadlineTask.class, List.of())
                .stream()
                .map(DeadlineTask.class::cast)
                .sorted(Comparator.comparing(DeadlineTask::getDeadline))
                .map(DeadlineTask::toString)
                .collect(Collectors.toList());

        List<String> events = tasks.getOrDefault(EventTask.class, List.of())
                .stream()
                .map(EventTask.class::cast)
                .sorted(Comparator
                        .comparing(EventTask::getStartTime)
                        .thenComparing(EventTask::getEndTime))
                .map(EventTask::toString)
                .collect(Collectors.toList());

        return String.join("\n...and...\n",
                generateResponse(todos, "TODO"),
                generateResponse(deadlines, "DEADLINE"),
                generateResponse(events, "EVENT"));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SortCommand;
    }
}
