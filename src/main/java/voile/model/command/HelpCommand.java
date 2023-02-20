package voile.model.command;

import java.util.Optional;

import voile.model.task.TaskList;

/**
 * Represents a command that shows help message.
 */
public class HelpCommand extends Command {

    private static final String DEFAULT_HELP_MESSAGE = String.join("\n",
            "This is the list of commands that you can use:",
            " - help",
            " - list",
            " - todo",
            " - deadline",
            " - event",
            " - mark",
            " - unmark",
            " - delete",
            " - clear",
            " - find",
            " - sort",
            " - edit",
            " - bye",
            "Use 'help [command]' for more details about a particular command.");

    private static final String HELP_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: help [command]",
            "Display the list of commands supported.",
            "Include [command] for more details about a particular command.");

    private static final String LIST_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: list",
            "Show all tasks in the library.",
            "Tasks will be displyed with their indicies in the list.",
            "You can use these indicies with other commands to manipulate the tasks.");

    private static final String TODO_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: todo <description>",
            "Add a new TODO task to the library.",
            "Note:",
            " - <description> cannot be empty.");

    private static final String DEADLINE_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: deadline <description> --by <deadline>",
            "Add a new DEADLINE task to the library.",
            "Notes:",
            " - <description> cannot be empty.",
            " - <deadline> is either: a date with yyyy-MM-dd format (2023-01-01),"
                    + " or a day of week with EEE format (Mon/Tue).",
            " - If you supply a day of week as argument,"
                    + " the nearest day of week in the future will be used.");

    private static final String EVENT_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: event <description> --from <start-time> --to <end-time>",
            "Add a new EVENT tasks to the library.",
            "Notes:",
            " - <description> cannot be empty.",
            " - <start-time> and <end-time> are either: dates with yyyy-MM-dd format (2023-01-01),"
                    + " or days of week with EEE format (Mon/Tue).",
            " - <start-time> cannot be after <end-time>.",
            " - If you supply a day of week as argument,"
                    + " the nearest day of week in the future will be used.");

    private static final String MARK_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: mark <index>",
            "Mark a task as done.",
            "Notes:",
            " - You can use 'list' to find the exact indicies of the tasks.",
            " - <index> must be a number that is greater than 0.");

    private static final String UNMARK_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: unmark <index>",
            "Mark a task as not done yet.",
            "Use this command if you mistakenly marked a task as done.",
            "Notes:",
            " - You can use 'list' to find the exact indicies of the tasks.",
            " - <index> must be a number that is greater than 0.");

    private static final String DELETE_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: delete <index>",
            "Delete a task from the library.",
            " - You can use 'list' to find the exact indicies of the tasks.",
            " - <index> must be a number that is greater than 0.");

    private static final String CLEAR_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: clear",
            "Delete all tasks from the library.");

    private static final String FIND_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: find <keyword>",
            "Find and list all tasks, whose descriptions contain the given keyword.");

    private static final String SORT_HELP_MESSAGE = String.join("\n",
            "Usage: sort",
            "Sort and show all DEADLINE tasks in the library.");

    private static final String EDIT_HELP_MESSAGE = String.join("\n",
            "Usage: edit <index> <description>",
            "Change the description of a task.",
            "Notes:",
            " - You can use 'list' to find the exact indicies of the tasks in the library.",
            " - <index> must be a number that is greater than 0.",
            " - <description> cannot be empty.");

    private static final String BYE_COMMAND_HELP_MESSAGE = String.join("\n",
            "Usage: bye",
            "Exit the application.");

    private Optional<Keyword> keyword;

    /**
     * Creates a new {@code HelpCommand} instance.
     *
     * @param keyword an {@code Optional} that may contain the keyword
     */
    public HelpCommand(Optional<Keyword> keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        return keyword.map(keyword -> {
            switch (keyword) {
            case HELP:
                return HELP_COMMAND_HELP_MESSAGE;
            case LIST:
                return LIST_COMMAND_HELP_MESSAGE;
            case TODO:
                return TODO_COMMAND_HELP_MESSAGE;
            case DEADLINE:
                return DEADLINE_COMMAND_HELP_MESSAGE;
            case EVENT:
                return EVENT_COMMAND_HELP_MESSAGE;
            case MARK:
                return MARK_COMMAND_HELP_MESSAGE;
            case UNMARK:
                return UNMARK_COMMAND_HELP_MESSAGE;
            case DELETE:
                return DELETE_COMMAND_HELP_MESSAGE;
            case CLEAR:
                return CLEAR_COMMAND_HELP_MESSAGE;
            case FIND:
                return FIND_COMMAND_HELP_MESSAGE;
            case EDIT:
                return EDIT_HELP_MESSAGE;
            case SORT:
                return SORT_HELP_MESSAGE;
            case BYE:
                return BYE_COMMAND_HELP_MESSAGE;
            default:
                throw new RuntimeException("should not reach here");
            }
        }).orElse(DEFAULT_HELP_MESSAGE);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }
}
