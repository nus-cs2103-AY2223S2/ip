package duke.query.core;

import duke.exception.DukeException;
import duke.query.Query;
import duke.query.QueryHandler;

public class GreetQueryHandler extends QueryHandler {
    /**
     * @param query a user input string
     * @return
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        return "Hello, I am iPman!";
    }
}
