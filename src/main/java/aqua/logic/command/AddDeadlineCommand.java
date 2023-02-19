package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.usertask.UserDeadline;
import aqua.util.DateUtils;


/** An {@code AddTaskCommand} to add {@code UserDeadline}. */
public class AddDeadlineCommand extends AddTaskCommand {
    @Override
    protected UserDeadline createTask(ArgumentMap args) throws SyntaxException {
        // get name
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new SyntaxException("Name disappeared!"));

        // get due time
        String dueString = args.get(UserDeadline.TAG_DUE_TIME)
                .orElseThrow(() -> new SyntaxException("[by] disappeared!"));
        LocalDateTime dueTime = DateUtils.parse(dueString);

        // get is complete
        boolean isCompleted = args.get(UserDeadline.TAG_IS_COMPLETE)
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);

        return new UserDeadline(name, isCompleted, dueTime);
    }
}
