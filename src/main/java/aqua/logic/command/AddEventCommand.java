package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.usertask.UserEvent;
import aqua.util.DateUtils;


/** An {@code AddTaskCommand} to add {@code AquaEvent}. */
public class AddEventCommand extends AddTaskCommand {
    /**
     * {@inheritDoc}
     * <p>
     * Specifically, an {@code AquaEvent}.
     */
    @Override
    public UserEvent createTask(ArgumentMap args) throws SyntaxException {
        // get name
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new SyntaxException("Name disappeared!"));

        // get from date
        String fromString = args.get(UserEvent.TAG_FROM)
                .orElseThrow(() -> new SyntaxException("[from] disappeared!"));
        LocalDateTime from = DateUtils.parse(fromString);

        // get to date
        String toString = args.get(UserEvent.TAG_TO)
                .orElseThrow(() -> new SyntaxException("[to] disappeared!"));
        LocalDateTime to = DateUtils.parse(toString);

        // get is complete
        boolean isCompleted = args.get(UserEvent.TAG_IS_COMPLETE)
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);

        // return formed event
        return new UserEvent(name, isCompleted, from, to);
    }


    @Override
    public String getSyntax() {
        return "<literal:name> /from <date:from> /to <date:to>";
    }


    @Override
    public String getDescription() {
        return "Adds an EVENT";
    }
}
