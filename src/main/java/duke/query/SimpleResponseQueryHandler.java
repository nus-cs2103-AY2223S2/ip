package duke.query;

import duke.DukeException;

/**
 * The SimpleResponseQueryHandler class handles simple responses to user queries.
 */
public class SimpleResponseQueryHandler extends QueryHandler {
    private final String response;

    public SimpleResponseQueryHandler(String response) {
        this.response = response;
    }

    /**
     * Simply returns a pre-defined response.
     *
     * @param query a user input string
     * @return pre-defined response
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        return response;
    }
}
