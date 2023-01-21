package aqua.logic.command;

import aqua.aquatask.AquaToDo;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddToDoCommand extends AddTaskCommand {
    @Override
    public AquaToDo createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        boolean isComplete = args.get("completed")
                    .map(isComp -> Boolean.parseBoolean(isComp))
                    .orElse(false);
        return new AquaToDo(name, isComplete);
    }
}
