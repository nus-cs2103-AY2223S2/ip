package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.usertask.UserDeadline;
import aqua.util.DateUtils;


/** An {@code AddTaskCommand} to add {@code UserDeadline}. */
public class AddDeadlineCommand extends AddTaskCommand {
    @Override
    public UserDeadline createTask(ArgumentMap args) throws SyntaxException {
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


    @Override
    public String getSyntax() {
        return "<literal:name> /by <date:by>";
    }


    @Override
    public String getDescription() {
        return "Adds a DEADLINE";
    }
}
