package aqua.logic.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import aqua.aquatask.AquaEvent;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddEventCommand extends AddTaskCommand {
    @Override
    public AquaEvent createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String fromString = args.get("from")
                .orElseThrow(() -> new IllegalSyntaxException("[from] disappeared!"));
        LocalDateTime from;
        try {
            from = LocalDateTime.parse(fromString);
        } catch (DateTimeParseException parseEx) {
            throw new IllegalSyntaxException(
                String.format("I do not understand when this is [%s]", fromString)
            );
        }
        String toString = args.get("to")
                .orElseThrow(() -> new IllegalSyntaxException("[to] disappeared!"));
        LocalDateTime to;
        try {
            to = LocalDateTime.parse(toString);
        } catch (DateTimeParseException parseEx) {
            throw new IllegalSyntaxException(
                String.format("I do not understand when this is [%s]", toString)
            );
        }
        return new AquaEvent(name, from, to);
    }
}
