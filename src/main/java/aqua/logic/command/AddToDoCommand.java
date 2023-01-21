package aqua.logic.command;

import aqua.aquatask.AquaToDo;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class AddToDoCommand extends AddTaskCommand {
    @Override
    public AquaToDo createTask(ArgumentMap args) throws IllegalSyntaxException {
        return args.getMainInput()
            .filter(name -> !name.isBlank())
            .map(name -> new AquaToDo(name))
            .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
    }
}
