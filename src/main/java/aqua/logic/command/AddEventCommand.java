package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.usertask.UserEvent;
import aqua.util.DateUtils;


/** An {@code AddTaskCommand} to add {@code UserEvent}. */
public class AddEventCommand extends AddTaskCommand {
    @Override
    public UserEvent createTask(ArgumentMap args) throws SyntaxException {
        // get name
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new SyntaxException("Name disappeared!"));

        // get start time
        String startString = args.get(UserEvent.TAG_START_TIME)
                .orElseThrow(() -> new SyntaxException("[from] disappeared!"));
        LocalDateTime startTime = DateUtils.parse(startString);

        // get end time
        String endString = args.get(UserEvent.TAG_END_TIME)
                .orElseThrow(() -> new SyntaxException("[to] disappeared!"));
        LocalDateTime endTime = DateUtils.parse(endString);

        // get is complete
        boolean isCompleted = args.get(UserEvent.TAG_IS_COMPLETE)
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);

        return new UserEvent(name, isCompleted, startTime, endTime);
    }
}
