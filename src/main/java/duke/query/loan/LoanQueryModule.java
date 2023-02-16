package duke.query.loan;

import java.util.HashMap;

import duke.exception.DukeException;
import duke.loan.LoanShark;
import duke.query.QueryHandler;
import duke.query.QueryModule;

public class LoanQueryModule extends QueryModule {
    public static final String LOAN_QUERY_TYPE = "loan";
    public static final String LOAN_RECORD_QUERY_TYPE = "loan-record";

    private final LoanShark ls = new LoanShark();

    /**
     * @throws DukeException
     */
    @Override
    public void init() throws DukeException {
        ls.load();
    }

    /**
     * @param commandToQueryHandler
     */
    @Override
    public void installQueryHandlers(HashMap<String, QueryHandler> commandToQueryHandler) {
        commandToQueryHandler.put(LOAN_QUERY_TYPE, new LoanQueryHandler(ls));
        commandToQueryHandler.put(LOAN_RECORD_QUERY_TYPE, new LoanRecordQueryHandler(ls));
    }
}
