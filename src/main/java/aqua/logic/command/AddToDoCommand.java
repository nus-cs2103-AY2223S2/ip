package aqua.logic.command;

import aqua.aquatask.AquaToDo;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


/**
 * A full implementation of AddTaskCommand that creates and adds AquaToDos.
 */
public class AddToDoCommand extends AddTaskCommand {
    /**
     * {@inheritDoc}
     * <p>
     * Specifically, an AquaToDo.
     * 
     * @return an AquaToDo created from the given arguments.
     */
    @Override
    public AquaToDo createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        boolean isComplete = args.get(AquaToDo.IS_COMPLETED_TAG)
                    .map(isComp -> Boolean.parseBoolean(isComp))
                    .orElse(false);
        return new AquaToDo(name, isComplete);
    }
}
