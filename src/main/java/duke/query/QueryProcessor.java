package duke.query;

import duke.exception.DukeException;
import duke.query.exception.UnknownCommandException;

import java.util.HashMap;

public class QueryProcessor {
    private static final String GOODBYE_RES = "GOOD BYE!";
    private static final String UNKNOWN_COMMAND_RES = "Your command is not of the known tongue!";
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
            return "I have failed you my liege! " + e.getMessage();
        }
    }

    private QueryHandler getQueryHandler(String command) throws UnknownCommandException {
        if (commandToQueryHandler.containsKey(command)) {
            return commandToQueryHandler.get(command);
        }

        throw new UnknownCommandException(UNKNOWN_COMMAND_RES);
    }
}
