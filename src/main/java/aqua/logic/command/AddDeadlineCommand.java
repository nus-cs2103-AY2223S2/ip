package aqua.logic.command;

import aqua.aquatask.AquaDeadline;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddDeadlineCommand extends AddTaskCommand {
    @Override
    public AquaDeadline createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String by = args.get("by")
                .orElseThrow(() -> new IllegalSyntaxException("[by] disappeared!"));
        return new AquaDeadline(name, by);
    }
}
