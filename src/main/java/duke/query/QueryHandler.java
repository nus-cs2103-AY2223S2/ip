package duke.query;

import duke.exception.DukeException;
import duke.query.exception.InvalidCommandParamException;

public abstract class QueryHandler {
    /**
     * Processes a user input string and returns a response string.
     *
     * @param query a user input string
     * @return a response string
     * @throws DukeException
     */
    public abstract String processQuery(Query query) throws DukeException;

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
}
