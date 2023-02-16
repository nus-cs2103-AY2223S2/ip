package duke.query;

import duke.exception.DukeException;
import duke.query.exception.UnknownCommandException;

import java.util.HashMap;

public class QueryProcessor {
    private static final String UNKNOWN_COMMAND_RES = "I do not know what to do for \"%s\". Please try again.";
    private static final String COMMAND_FAILED_RES = "Sorry, but I could not perform my duties!";
    private final HashMap<String, QueryHandler> commandToQueryHandler;

    public QueryProcessor(QueryModule... modules) throws DukeException {
        commandToQueryHandler = new HashMap<>();
        for (QueryModule module : modules) {
            module.init();
            module.installQueryHandlers(commandToQueryHandler);
        }
    }

    public String processQuery(Query query) {
        try {
            QueryHandler queryHandler = getQueryHandler(query.getCommand());
            return queryHandler.processQuery(query);
        } catch (DukeException e) {
            return COMMAND_FAILED_RES + "\n" + e.getMessage();
        }
    }

    private QueryHandler getQueryHandler(String command) throws UnknownCommandException {
        if (commandToQueryHandler.containsKey(command)) {
            return commandToQueryHandler.get(command);
        }

        throw new UnknownCommandException(String.format(UNKNOWN_COMMAND_RES, command));
    }
}
