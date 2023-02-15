package duke.query.loan;

import java.util.HashMap;

import duke.DukeException;
import duke.loan.LoanShark;
import duke.query.QueryHandler;
import duke.query.QueryModule;
import duke.query.QueryType;

public class LoanQueryModule extends QueryModule {
    private final LoanShark ls = new LoanShark();

    /**
     * @throws DukeException
     */
    @Override
    public void init() throws DukeException {
        // Nothing to initialize.
    }

    /**
     * @param queryTypeToQueryHandler
     */
    @Override
    public void installQueryHandlers(HashMap<QueryType, QueryHandler> queryTypeToQueryHandler) {
        queryTypeToQueryHandler.put(QueryType.LOAN, new LoanQueryHandler(ls));
    }
}
