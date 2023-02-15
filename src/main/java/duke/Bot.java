package duke;

import duke.exception.DukeException;
import duke.query.Query;
import duke.query.QueryParser;
import duke.query.QueryProcessor;
import duke.query.QueryType;
import duke.query.core.CoreModule;
import duke.query.loan.LoanQueryModule;
import duke.query.task.TaskQueryModule;

/**
 * The Bot class represents a bot that can process and respond to user queries.
 */
public class Bot {
    private QueryProcessor queryProcessor;
    private boolean hasInit = false;

    /**
     * Initializes the bot.
     *
     * @throws DukeException
     */
    public void init() throws DukeException {
        queryProcessor = new QueryProcessor(
                new CoreModule(),
                new TaskQueryModule(),
                new LoanQueryModule()
        );
        hasInit = true;
    }

    /**
     * Processes an input string as a query and returns a BotResult object that contains a response string
     * and the current status of the bot.
     *
     * @param input user input to be processed as a query
     * @return the result of processing the user input
     */
    public BotResult process(String input) {
        if (!hasInit) {
            return new BotResult(BotResult.BotStatus.Failure, "");
        }

        BotResult.BotStatus status = BotResult.BotStatus.Successful;

        Query query = QueryParser.parseQuery(input);
        if (query.getQueryType() == QueryType.EXIT) {
            status = BotResult.BotStatus.Exit;
        }

        IFormatter formatter = new ResponseFormatter();
        String response = formatter.format(queryProcessor.processQuery(query));
        return new BotResult(status, response);
    }
}
