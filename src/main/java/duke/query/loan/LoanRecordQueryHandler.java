package duke.query.loan;

import duke.exception.DukeException;
import duke.loan.LoanShark;
import duke.query.Query;
import duke.query.QueryHandler;

/**
 * LoanRecordQueryHandler handles queries to display the loan-record affiliated with a holder.
 */
public class LoanRecordQueryHandler extends QueryHandler {
    private static final String QUERY_SYNTAX = "loan-record <holder>";
    private final LoanShark ls;

    public LoanRecordQueryHandler(LoanShark loanShark) {
        this.ls = loanShark;
    }

    /**
     * @param query a user input string
     * @return loan record string
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        String holder = getNotBlankParam(query, getErrorMessage("holder"));
        return String.format("Here is your loan record.\n%s", ls.getAccountLoanRecordString(holder));
    }

    @Override
    public String getQueryDescription() {
        return "loan-record \n- Displays a record of loans associated with a holder.";
    }

    @Override
    public String getQuerySyntax() {
        return QUERY_SYNTAX;
    }
}
