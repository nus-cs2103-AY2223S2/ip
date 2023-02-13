package duke.commands.taskCommand;

import duke.Duke;
import duke.Utils;
import duke.parser.Arguments;
import duke.task.ToDo;

public class TodoCommand extends TaskCommand<ToDo> {
    public TodoCommand() {
        super("todo");
    }

    @Override
    protected ToDo getTask(Arguments args, Duke instance) throws ValidationException {
        validate(args.getNumOfArgs() > 0, "Expected a task!");

        int priority = getPriorityFromArgs(args);
        String[] strArgs = args.getExcessArguments();
        String taskStr = Utils.stringJoiner(strArgs, 0, strArgs.length);
        return new ToDo(taskStr, priority);
    }
}
