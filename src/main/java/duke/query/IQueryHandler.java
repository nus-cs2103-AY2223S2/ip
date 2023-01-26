package duke.query;

import duke.DukeException;

public interface IQueryHandler {
    /**
     * Processes a user input string and returns a response string.
     *
     * @param query a user input string
     * @return a response string
     * @throws DukeException
     */
    String processQuery(String query) throws DukeException;
}
