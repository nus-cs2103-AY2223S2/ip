package duke.query;

import java.util.HashMap;

import duke.exception.DukeException;

/**
 * QueryModule provides a suite of query handler associated with a functionality.
 */
public abstract class QueryModule {
    public abstract void init() throws DukeException;

    public abstract void installQueryHandlers(HashMap<String, QueryHandler> commandToQueryHandler);
}
