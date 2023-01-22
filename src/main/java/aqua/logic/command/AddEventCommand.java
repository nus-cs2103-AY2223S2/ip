package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.aquatask.AquaEvent;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.util.DateUtils;


public class AddEventCommand extends AddTaskCommand {
    @Override
    public AquaEvent createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String fromString = args.get("from")
                .orElseThrow(() -> new IllegalSyntaxException("[from] disappeared!"));
        LocalDateTime from = DateUtils.parse(fromString);
        String toString = args.get("to")
                .orElseThrow(() -> new IllegalSyntaxException("[to] disappeared!"));
        LocalDateTime to = DateUtils.parse(toString);
        return new AquaEvent(name, from, to);
    }
}
