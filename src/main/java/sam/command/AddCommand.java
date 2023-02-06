package sam.command;

import java.time.LocalDate;
import java.util.Map;

import sam.Ui;
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
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws SamMissingTaskTitleException, SamMissingTaskValueException,
            SamMissingTaskArgException, SamInvalidDateException, SamSaveFailedException,
            SamUnknownCommandException {
        Map<String, String> taskArgs = Parser.parseTaskArgs(args);
        String title = taskArgs.get("title");
        Task task = null;

        switch (taskType) {
        case 'T':
            task = new ToDo(title);
            break;
        case 'E':
            if (!taskArgs.containsKey("from") || !taskArgs.containsKey("to")) {
                throw new SamMissingTaskArgException();
            }
            LocalDate from = Parser.parseDate(taskArgs.get("from"));
            LocalDate to = Parser.parseDate(taskArgs.get("to"));
            task = new Event(title, from, to);
            break;
        case 'D':
            if (!taskArgs.containsKey("by")) {
                throw new SamMissingTaskArgException();
            }
            LocalDate by = Parser.parseDate(taskArgs.get("by"));
            task = new Deadline(title, by);
            break;
        default:
            throw new SamUnknownCommandException();
        }

        assert task != null : "task shouldn't be null";
        tasks.addTask(task);

        ui.respond(Ui.Dialog.ADD.getDialog(),
                task.toString(),
                String.format(Ui.Dialog.ADD_COUNT.getDialog(), tasks.count()));

        storage.save(tasks);
    }
}
