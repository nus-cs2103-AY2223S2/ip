package duke.query.loan;

import duke.exception.DukeException;
import duke.loan.Loan;
import duke.loan.LoanShark;
import duke.query.Query;
import duke.query.QueryHandler;
import duke.query.exception.InvalidCommandParamException;

/**
 * LoanQueryHandler handles queries to add new loans.
 */
public class LoanQueryHandler extends QueryHandler {
    private static final String QUERY_SYNTAX = "loan <amount> /holder <holder> /desc <description>";
    private LoanShark loanShark;

    public LoanQueryHandler(LoanShark loanShark) {
        this.loanShark = loanShark;
    }

    /**
     * @param query a user input string
     * @return query result
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        String holder = getNotBlankArg(query, "/holder", getErrorMessage("holder"));
        int amount = getAmountFromQuery(query);
        String description = getNotBlankArg(query, "/desc",
                getErrorMessage("description"));
        Loan newLoan = loanShark.addLoan(amount, amount, description, holder);
        loanShark.saveLoans();

        return String.format("New loan added:\n%s\n\nHere are your active loans.\n%s",
                newLoan,
                loanShark.getAccountActiveLoansString(holder)
        );
    }

    @Override
    public String getQueryDescription() {
        return "loan \n- Adds a loan with an amount (negative represents owed, positive represents lent) "
                + "and is associated with a holder and description.";
    }

    @Override
    public String getQuerySyntax() {
        return QUERY_SYNTAX;
    }

    private int getAmountFromQuery(Query query) throws InvalidCommandParamException {
        try {
            return (int) (Double.parseDouble(query.getParam()) * 100);
        } catch (NumberFormatException e) {
            throw new InvalidCommandParamException(getErrorMessage("amount"));
        }
    }
}
