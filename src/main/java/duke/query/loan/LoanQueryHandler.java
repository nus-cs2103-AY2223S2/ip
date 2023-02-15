package duke.query.loan;

import duke.exception.DukeException;
import duke.loan.LoanShark;
import duke.query.QueryHandler;
import duke.query.exception.InvalidCommandParamException;
import duke.query.Query;

public class LoanQueryHandler extends QueryHandler {
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
        String holder = getNotBlankArg(query, "/holder", "Please provide a holder for the loan!");
        int amount = getAmountFromQuery(query);
        String description = getNotBlankArg(query, "/description", "Please provide a description for the loan!");
        loanShark.addLoan(amount, description, holder);

        StringBuilder response = new StringBuilder();
        response.append(loanShark.getAccountActiveLoansString(holder));
        return response.toString();
    }

    private static int getAmountFromQuery(Query query) throws InvalidCommandParamException {
        try {
            return (int) (Double.parseDouble(query.getParam()) * 100);
        } catch (NumberFormatException e) {
            throw new InvalidCommandParamException("Please provide an amount for this loan!");
        }
    }
}
