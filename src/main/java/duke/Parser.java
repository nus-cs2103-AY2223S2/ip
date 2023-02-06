package duke;

import duke.command.Command;
import duke.command.CreateDeadlineCommand;
import duke.command.CreateEventCommand;
import duke.command.CreateTodoCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EndChatCommand;
import duke.command.FindTaskWithTextCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.task.TaskList;

/**
 * Helps to make sense of the user's commands.
 *
 * @author wz2k
 */
public class Parser {
    /**
     * Returns an instance of the correct command from the user's input.
     *
     * @param input User's input.
     * @param taskList List of task maintained by the chatbot.
     * @param storage Chatbot's storage of the tasks it maintains.
     * @return Command instance.
     * @throws DukeException If input is not recognized.
     */
    public static Command parseCommand(String input, TaskList taskList, Storage storage)
            throws DukeException {
        String type = getType(input);

        switch(type) {
        case "todo":
            return new CreateTodoCommand(input, taskList, storage);
        case "deadline":
            return new CreateDeadlineCommand(input, taskList, storage);
        case "event":
            return new CreateEventCommand(input, taskList, storage);
        case "mark":
            return new MarkTaskCommand(input, taskList, storage);
        case "unmark":
            return new UnmarkTaskCommand(input, taskList, storage);
        case "delete":
            return new DeleteTaskCommand(input, taskList, storage);
        case "list":
            return new ListTasksCommand(input, taskList);
        case "find":
            return new FindTaskWithTextCommand(input, taskList);
        case "bye":
            return new EndChatCommand(input);
        default:
            throw new DukeException("Input is not recognized.");
        }
    }

    /**
     * Returns the type of command.
     *
     * @param input User's input.
     * @return Type of command.
     */
    public static String getType(String input) {
        return input.split(" ", 2)[0];
    }
}
