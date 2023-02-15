package duke.query.core;

import duke.exception.DukeException;
import duke.query.QueryHandler;
import duke.query.QueryModule;
import duke.query.QueryType;
import javafx.scene.control.cell.ProgressBarTableCell;

import java.util.HashMap;

public class CoreModule extends QueryModule {
    /**
     * @throws DukeException
     */
    @Override
    public void init() throws DukeException {
        // No initialization required.
    }

    /**
     * @param queryTypeToQueryHandler
     */
    @Override
    public void installQueryHandlers(HashMap<QueryType, QueryHandler> queryTypeToQueryHandler) {
        queryTypeToQueryHandler.put(QueryType.GREET, new GreetQueryHandler());
        queryTypeToQueryHandler.put(QueryType.HELP, new HelpQueryHandler());
    }
}
