package duke.query;

import java.util.HashMap;

import duke.DukeException;

public abstract class QueryModule {
    public abstract void init() throws DukeException;

    public abstract void installQueryHandlers(HashMap<QueryType, QueryHandler> queryTypeToQueryHandler);
}
