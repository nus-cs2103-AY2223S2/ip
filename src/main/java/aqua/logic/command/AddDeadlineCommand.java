package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.aquatask.AquaDeadline;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.util.DateUtils;


/**
 * A full implementation of AddTaskCommand that creates and adds AquaDeadlines.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    /**
     * {@inheritDoc}
     * <p>
     * Specifically, an AquaDeadline.
     *
     * @return an AquaDeadline created from the given arguments.
     */
    @Override
    public AquaDeadline createTask(ArgumentMap args) throws IllegalSyntaxException {
        // get name
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));

        // get by date
        String byString = args.get("by")
                .orElseThrow(() -> new IllegalSyntaxException("[by] disappeared!"));
        LocalDateTime by = DateUtils.parse(byString);

        // get is complete
        boolean isCompleted = args.get(AquaDeadline.TAG_IS_COMPLETE)
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);

        // return formed deadline
        return new AquaDeadline(name, isCompleted, by);
    }


    @Override
    public String getSyntax() {
        return "<literal:name> /by <date:by>";
    }
}
