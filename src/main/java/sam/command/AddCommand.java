package sam.command;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import sam.parser.Parser;
import sam.parser.SamInvalidDateException;
import sam.parser.SamUnknownCommandException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.Deadline;
import sam.task.Event;
import sam.task.SamMissingTaskArgException;
import sam.task.SamMissingTaskTitleException;
import sam.task.SamMissingTaskValueException;
import sam.task.Task;
import sam.task.TaskList;
import sam.task.ToDo;
import sam.ui.Dialog;

/**
 * Represents a user command to add a new task.
 */
public class AddCommand extends Command {
    private char taskType;

    /**
     * Constructs a new AddCommand to add a task of a given type.
     *
     * @param args The command string.
     * @param taskType The type of task to add.
     */
    public AddCommand(String args, char taskType) {
        super(args);
        this.taskType = taskType;
    }

    @Override
    public Result execute(TaskList tasks, Storage storage)
            throws SamMissingTaskTitleException, SamMissingTaskValueException,
            SamMissingTaskArgException, SamInvalidDateException, SamSaveFailedException,
            SamUnknownCommandException {
        String input = args.strip();
        if (input.isEmpty() || input.charAt(0) == '/') {
            throw new SamMissingTaskTitleException();
        }

        Task task = createTask(input);
        tasks.addTask(task);

        storage.save(tasks);

        result.addMessage(Dialog.ADD.getDialog());
        result.addTask(task);
        result.addMessage(String.format(Dialog.ADD_COUNT.getDialog(), tasks.count()));
        return result;
    }

    private Task createTask(String input) throws SamMissingTaskValueException,
            SamMissingTaskArgException, SamInvalidDateException, SamUnknownCommandException {
        String[] titleArgs = input.split(" +/", 2);
        String title = titleArgs[0];

        Map<String, String> argsMap = new HashMap<>();
        if (titleArgs.length > 1) {
            argsMap = Parser.parseTaskArgs(argsMap, titleArgs[1]);
        }

        Task task = null;
        switch (taskType) {
        case 'T':
            task = new ToDo(title);
            break;
        case 'E':
            if (!argsMap.containsKey("from") || !argsMap.containsKey("to")) {
                throw new SamMissingTaskArgException();
            }
            LocalDate from = Parser.parseDate(argsMap.get("from"));
            LocalDate to = Parser.parseDate(argsMap.get("to"));
            task = new Event(title, from, to);
            break;
        case 'D':
            if (!argsMap.containsKey("by")) {
                throw new SamMissingTaskArgException();
            }
            LocalDate by = Parser.parseDate(argsMap.get("by"));
            task = new Deadline(title, by);
            break;
        default:
            throw new SamUnknownCommandException();
        }

        assert task != null : "task shouldn't be null";
        return task;
    }
}
