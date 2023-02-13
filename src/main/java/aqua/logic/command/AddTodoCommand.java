package aqua.logic.command;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.usertask.UserTodo;


/** An {@code AddTaskCommand} to add {@code UserTodo}. */
public class AddTodoCommand extends AddTaskCommand {
    @Override
    protected UserTodo createTask(ArgumentMap args) throws SyntaxException {
        // get name
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new SyntaxException("Name disappeared!"));

        // get is complete
        boolean isComplete = args.get(UserTodo.TAG_IS_COMPLETE)
                    .map(isComp -> Boolean.parseBoolean(isComp))
                    .orElse(false);

        return new UserTodo(name, isComplete);
    }
}
