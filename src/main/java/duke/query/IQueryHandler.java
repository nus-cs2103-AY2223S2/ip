package duke.query;

import duke.DukeException;

public interface IQueryHandler {
    String processQuery(String query) throws DukeException;
}
