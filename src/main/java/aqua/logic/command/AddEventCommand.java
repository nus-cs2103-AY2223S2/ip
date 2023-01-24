package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.aquatask.AquaEvent;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.util.DateUtils;


public class AddEventCommand extends AddTaskCommand {
    @Override
    public AquaEvent createTask(ArgumentMap args) throws IllegalSyntaxException {
        // get name
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        
        // get from date
        String fromString = args.get("from")
                .orElseThrow(() -> new IllegalSyntaxException("[from] disappeared!"));
        LocalDateTime from = DateUtils.parse(fromString);

        // get to date
        String toString = args.get("to")
                .orElseThrow(() -> new IllegalSyntaxException("[to] disappeared!"));
        LocalDateTime to = DateUtils.parse(toString);

        // get is complete
        boolean isCompleted = args.get(AquaEvent.TAG_IS_COMPLETE)
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);
        
        // return formed event
        return new AquaEvent(name, isCompleted, from, to);
    }
}
