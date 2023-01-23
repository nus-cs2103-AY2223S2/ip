package sam.command;

import java.time.LocalDate;
import java.util.Map;

import sam.Ui;
import sam.parser.Parser;
import sam.parser.SamInvalidDateException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.Deadline;
import sam.task.Event;
import sam.task.SamMissingTaskArgException;
import sam.task.SamMissingTaskTitleException;
import sam.task.SamMissingTaskValueException;
import sam.task.Task;
import sam.task.TaskList;
import sam.task.TaskType;
import sam.task.ToDo;

public class AddCommand extends Command {
    private TaskType taskType;

    public AddCommand(String args, TaskType taskType) {
        super(args);
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws SamMissingTaskTitleException, SamMissingTaskValueException,
            SamMissingTaskArgException, SamInvalidDateException, SamSaveFailedException {
        Map<String, String> taskArgs = Parser.parseTaskArgs(args);
        String title = taskArgs.get("title");
        Task task = null;

        switch (taskType) {
        case TODO:
            task = new ToDo(title);
            break;
        case EVENT:
            if (!taskArgs.containsKey("from") || !taskArgs.containsKey("to")) {
                throw new SamMissingTaskArgException();
            }
            LocalDate from = Parser.parseDate(taskArgs.get("from"));
            LocalDate to = Parser.parseDate(taskArgs.get("to"));
            task = new Event(title, from, to);
            break;
        case DEADLINE:
            if (!taskArgs.containsKey("by")) {
                throw new SamMissingTaskArgException();
            }
            LocalDate by = Parser.parseDate(taskArgs.get("by"));
            task = new Deadline(title, by);
            break;
        }

        tasks.addTask(task);

        ui.talk("Gotcha, I'll add the task to your list:",
                task.toString(),
                String.format("Now you have %d tasks in the list", tasks.count()));

        storage.save(tasks);
    }
}
