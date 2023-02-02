package aqua.logic.command;

import aqua.aquatask.AquaToDo;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


/** An {@code AddTaskCommand} to add {@code AquaToDo}. */
public class AddToDoCommand extends AddTaskCommand {
    /**
     * {@inheritDoc}
     * <p>
     * Specifically, an {@code AquaToDo}.
     */
    @Override
    public AquaToDo createTask(ArgumentMap args) throws IllegalSyntaxException {
        // get name
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));

        // get is complete
        boolean isComplete = args.get(AquaToDo.TAG_IS_COMPLETE)
                    .map(isComp -> Boolean.parseBoolean(isComp))
                    .orElse(false);

        // return formed todo task
        return new AquaToDo(name, isComplete);
    }


    @Override
    public String getSyntax() {
        return "<literal:name>";
    }


    @Override
    public String getDescription() {
        return "Adds a TODO";
    }
}
