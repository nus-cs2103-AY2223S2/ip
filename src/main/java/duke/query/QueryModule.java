package duke.query;

import java.util.HashMap;

import duke.exception.DukeException;

public abstract class QueryModule {
    public abstract void init() throws DukeException;

    public abstract void installQueryHandlers(HashMap<String, QueryHandler> commandToQueryHandler);
}
