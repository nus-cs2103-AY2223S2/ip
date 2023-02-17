package duke.query.core;

import duke.exception.DukeException;
import duke.query.Query;
import duke.query.QueryHandler;

/**
 * GreetQueryHandler handles responses to greetings.
 */
public class GreetQueryHandler extends QueryHandler {
    @Override
    public String processQuery(Query query) throws DukeException {
        return "Hello, I am iPman! Type \"help\" for a full list of commands you can use!";
    }

    @Override
    public String getQueryDescription() {
        return "hi \n- Shows respect to the bot.";
    }

    @Override
    public String getQuerySyntax() {
        return "hi";
    }
}
