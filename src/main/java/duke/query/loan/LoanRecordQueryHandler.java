package duke.query.loan;

import duke.exception.DukeException;
import duke.loan.LoanShark;
import duke.query.Query;
import duke.query.QueryHandler;

public class LoanRecordQueryHandler extends QueryHandler {
    private LoanShark ls;

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
        String holder = getNotBlankParam(query, "Please provide a holder!");
        return String.format("Here is your loan record.\n%s", ls.getAccountLoanRecordString(holder));
    }
}
