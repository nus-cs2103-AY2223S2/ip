package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.aquatask.AquaDeadline;
import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.util.DateUtils;


/** An {@code AddTaskCommand} to add {@code AquaDeadline}. */
public class AddDeadlineCommand extends AddTaskCommand {
    /**
     * {@inheritDoc}
     * <p>
     * Specifically, an {@code AquaDeadline}.
     */
    @Override
    public AquaDeadline createTask(ArgumentMap args) throws SyntaxException {
        // get name
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new SyntaxException("Name disappeared!"));

        // get by date
        String byString = args.get("by")
                .orElseThrow(() -> new SyntaxException("[by] disappeared!"));
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


    @Override
    public String getDescription() {
        return "Adds a DEADLINE";
    }
}
