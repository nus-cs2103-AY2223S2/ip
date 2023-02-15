package duke.query;

import duke.DukeException;

public abstract class QueryHandler {
    /**
     * Processes a user input string and returns a response string.
     *
     * @param query a user input string
     * @return a response string
     * @throws DukeException
     */
    public abstract String processQuery(Query query) throws DukeException;
}
