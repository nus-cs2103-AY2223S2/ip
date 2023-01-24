package controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;


/**
 * Represents the find command.
 * Parses through each task and check if it contains the specified string.
 */
public class FindCommand extends Command {
    private static final Pattern VALID_FILTER = Pattern.compile("^(?<cmd>find) (?<filter>.+)$");
    private final String args;

    /**
     * Instantiate the find command.
     *
     * @param args The args of the command.
     */
    public FindCommand(String args) {
        super(CommandType.FIND);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and filters tasks based on the filter string.
     */
    @Override
    public void execute(Supplier<? extends TaskList> store) throws DukeException {
        TaskList taskList = store.get();
        Matcher matcher = VALID_FILTER.matcher(args);
        if (matcher.find()) {
            taskList.filter((task -> task.matchString(matcher.group("filter"))),
                    "There are no tasks with this description.");
        } else {
            throw new DukeException(INVALID_FORMAT_ERROR + " " + "Please ensure you follow: filter [filter]");
        }
    }
}
