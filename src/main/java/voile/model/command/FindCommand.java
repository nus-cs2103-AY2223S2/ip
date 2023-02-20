package voile.model.command;

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
        String content = list.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
        return "Here are the matching tasks in your list:\n" + content;
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
