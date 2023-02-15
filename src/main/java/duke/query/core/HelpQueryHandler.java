package duke.query.core;

import duke.exception.DukeException;
import duke.query.Query;
import duke.query.QueryHandler;

public class HelpQueryHandler extends QueryHandler {
    private static final String HELP_TEXT = "Here are some commands you can try!";
    private static final String LIST_HELP_TEXT = "1. list - list all tasks.";
    private static final String TODO_HELP_TEXT = "2. todo <description> - add a task with <description>.";

    /**
     * @param query a user input string
     * @return query response
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        return String.join("\n",
                HELP_TEXT,
                "",
                LIST_HELP_TEXT,
                TODO_HELP_TEXT
        );
    }
}
