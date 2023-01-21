package aqua.logic.command;

import aqua.aquatask.AquaEvent;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddEventCommand extends AddTaskCommand {
    @Override
    public AquaEvent createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String from = args.get("from")
                .orElseThrow(() -> new IllegalSyntaxException("[from] disappeared!"));
        String to = args.get("to")
                .orElseThrow(() -> new IllegalSyntaxException("[to] disappeared!"));
        boolean isCompleted = args.get("completed")
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);
        return new AquaEvent(name, isCompleted, from, to);
    }
}
