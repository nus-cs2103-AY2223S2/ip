package duke.query.loan;

import duke.exception.DukeException;
import duke.loan.LoanShark;
import duke.query.Query;
import duke.query.QueryHandler;

public class LoanSummaryQueryHandler extends QueryHandler {
    private final LoanShark ls;

    LoanSummaryQueryHandler(LoanShark ls) {
        this.ls = ls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        return "Here is the summary of your current loans!\n" + ls.getLoanSummaryString();
    }
}
