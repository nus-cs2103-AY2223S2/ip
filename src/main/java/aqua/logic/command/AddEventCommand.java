package aqua.logic.command;

import aqua.aquatask.AquaEvent;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddEventCommand extends AddTaskCommand {
    @Override
    public AquaEvent createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput().filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String from = args.get(AquaEvent.FROM_TAG)
                .orElseThrow(() -> new IllegalSyntaxException("[from] disappeared!"));
        String to = args.get(AquaEvent.TO_TAG)
                .orElseThrow(() -> new IllegalSyntaxException("[to] disappeared!"));
        boolean isCompleted = args.get(AquaEvent.IS_COMPLETED_TAG)
                .map(isComp -> Boolean.parseBoolean(isComp))
                .orElse(false);
        return new AquaEvent(name, isCompleted, from, to);
    }
}
