package duke.query;

import duke.DukeException;

import java.util.HashMap;

public abstract class QueryModule {
    public abstract void init() throws DukeException;

    public abstract void installQueryHandlers(HashMap<QueryType, QueryHandler> queryTypeToQueryHandler);
}
