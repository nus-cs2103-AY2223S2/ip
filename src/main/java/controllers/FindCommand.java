package controllers;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    private final String args;
    private final Pattern VALID_FILTER = Pattern.compile("^(?<cmd>find) (?<filter>.+)$");
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
