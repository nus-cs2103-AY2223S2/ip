package duke.query;

import duke.exception.DukeException;
import duke.query.exception.InvalidCommandParamException;

/**
 * QueryHandler is an abstract class that processes queries.
 */
public abstract class QueryHandler {
    /**
     * Processes a user input string and returns a response string.
     *
     * @param query user input string
     * @return response string
     * @throws DukeException
     */
    public abstract String processQuery(Query query) throws DukeException;

    /**
     * Returns a help text for query.
     * @return query help string
     */
    public abstract String getQueryDescription();

    /**
     * Returns the defined syntax for the query.
     * @return query syntax string
     */
    public abstract String getQuerySyntax();

    public String getQueryHelp() {
        return getQueryDescription() + "\n\t" + getQuerySyntax();
    }

    protected String getNotBlankArg(Query query, String argKey, String errMsg) throws InvalidCommandParamException {
        String arg = query.getArgument(argKey);
        if (arg == null || arg.isBlank()) {
            throw new InvalidCommandParamException(errMsg);
        }
        return arg;
    }

    protected String getNotBlankParam(Query query, String errMsg) throws InvalidCommandParamException {
        String param = query.getParam();
        if (param == null || param.isBlank()) {
            throw new InvalidCommandParamException(errMsg);
        }
        return param;
    }

    protected int getIntegerParam(Query query, String errMsg) throws InvalidCommandParamException {
        try {
            return Integer.parseInt(query.getParam());
        } catch (NumberFormatException e) {
            throw new InvalidCommandParamException(errMsg);
        }
    }

    protected String getErrorMessage(String invalidParam) {
        return String.format("\nPlease provide a valid \"%s\"!\n\nTry following this syntax instead:\n%s",
                invalidParam, getQuerySyntax());
    }
}
