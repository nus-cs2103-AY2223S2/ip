package duke.query;

import duke.DukeException;

import java.util.HashMap;

public class QueryProcessor {
    private static final String GOODBYE_RES = "GOOD BYE!";
    private static final String UNKNOWN_COMMAND_RES = "Your command is not of the known tongue!";
    private final HashMap<QueryType, QueryHandler> queryTypeToQueryHandler;

    public QueryProcessor(QueryModule... modules) throws DukeException {
        queryTypeToQueryHandler = new HashMap<>();
        for (QueryModule module : modules) {
            module.init();
            module.installQueryHandlers(queryTypeToQueryHandler);
        }
    }

    public String processQuery(Query query) {
        try {
            QueryHandler queryHandler = getQueryHandler(query.getQueryType());
            return queryHandler.processQuery(query);
        } catch (DukeException e) {
            return "I have failed you my liege! " + e.getMessage();
        }
    }

    private QueryHandler getQueryHandler(QueryType queryType) throws UnknownCommandException {
        if (queryTypeToQueryHandler.containsKey(queryType)) {
            return queryTypeToQueryHandler.get(queryType);
        }

        throw new UnknownCommandException(UNKNOWN_COMMAND_RES);
    }
}
