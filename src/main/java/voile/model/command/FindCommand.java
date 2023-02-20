package voile.model.command;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import voile.model.task.TaskList;

/**
 * Represents a command that finds all {@code Task}s whose descriptions contain the given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a new {@code FindCommand} instance.
     *
     * @param keyword the keyword to find later
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        List<String> matchingTasks = list.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .map(Objects::toString)
                .collect(Collectors.toList());
        int size = matchingTasks.size();
        String content = String.join("\n", matchingTasks);
        return size == 0
                ? "There is no matching task in the library."
                : size == 1
                        ? "There is only one matching task in the library:\n" + content
                        : "Here are the matching tasks in the library:\n" + content;

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FindCommand)) {
            return false;
        }
        FindCommand cmd = (FindCommand) obj;
        return Objects.equals(keyword, cmd.keyword);
    }
}
