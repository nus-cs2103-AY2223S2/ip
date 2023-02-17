package duke.query.core;

import duke.exception.DukeException;
import duke.query.Query;
import duke.query.QueryHandler;

/**
 * HelpQueryHandler handles responses for help queries.
 */
public class HelpQueryHandler extends QueryHandler {
    private static final String HELP_TEXT = "Get work done with these commands!";
    private final Help help;

    HelpQueryHandler(Help help) {
        this.help = help;
    }

    /**
     * @param query a user input string
     * @return query response
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        return HELP_TEXT + "\n\n" + help.getHelp();
    }

    @Override
    public String getQueryDescription() {
        return "help \n- Displays help.";
    }

    @Override
    public String getQuerySyntax() {
        return "help";
    }
}
