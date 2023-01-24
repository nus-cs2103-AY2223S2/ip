package duke.query;

import duke.DukeException;

public interface IQueryHandler {
    public String processQuery(String query) throws DukeException;
}
