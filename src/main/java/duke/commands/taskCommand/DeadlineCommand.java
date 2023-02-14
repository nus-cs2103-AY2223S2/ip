package duke.commands.taskcommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.Utils;
import duke.parser.Arguments;
import duke.task.Deadline;

public class DeadlineCommand extends TaskCommand<Deadline> {
    public DeadlineCommand() {
        super("deadline");
    }

    @Override
    protected Deadline getTask(Arguments args, Duke instance) throws ValidationException {
        int priority = getPriorityFromArgs(args);

        validate(args.hasLabelledArgument("by"), "Expected a /by directive!");
        String[] byArgs = args.getLabelledArgument("by");

        validate(byArgs.length > 0, "Expected a time after /by!");
        
        String[] taskArgs = args.getExcessArguments();
        if (taskArgs == null) {
            throw new ValidationException("Expected a task!");
        }

        try {
            LocalDateTime deadline = Utils.parseDateTime(
                byArgs[0],
                byArgs.length > 1 ? byArgs[1] : null
            );
            validate(deadline.isAfter(LocalDateTime.now()), "I can't create something's that's due in the past!");

            String taskStr = Utils.stringJoiner(taskArgs, 0);
            return new Deadline(taskStr, priority, deadline);
        } catch (DateTimeParseException e) {
            throw new ValidationException(String.format("Error parsing deadline: %s\n", e.getMessage()));
        }
    }
}
