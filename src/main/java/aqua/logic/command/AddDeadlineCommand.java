package aqua.logic.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import aqua.aquatask.AquaDeadline;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddDeadlineCommand extends AddTaskCommand {
    @Override
    public AquaDeadline createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String byString = args.get("by")
                .orElseThrow(() -> new IllegalSyntaxException("[by] disappeared!"));
        LocalDateTime byTime;
        try {
            byTime = LocalDateTime.parse(byString);
        } catch (DateTimeParseException parseEx) {
            throw new IllegalSyntaxException(
                String.format("I do not understand when this is [%s]", byString)
            );
        }
        return new AquaDeadline(name, byTime);
    }
}
