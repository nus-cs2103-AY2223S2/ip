package duke.query.loan;

import duke.exception.DukeException;
import duke.loan.LoanShark;
import duke.query.Query;
import duke.query.QueryHandler;

/**
 * LoanRecordQueryHandler handles queries to display the loan-summary.
 */
public class LoanSummaryQueryHandler extends QueryHandler {
    private static final String QUERY_SYNTAX = "loan-summary";
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

    @Override
    public String getQueryDescription() {
        return "loan-summary \n- Displays a summary of all loans.";
    }

    @Override
    public String getQuerySyntax() {
        return QUERY_SYNTAX;
    }
}
