package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.regex.PatternSyntaxException;

public class AddTaskCommand extends Command {
    private final String typeOfTask;
    public AddTaskCommand(String command) {
        super(command);
        this.typeOfTask = command.split(" ")[0];
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            Task task = makeTask();
            tasks.add(task);
            ui.print(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, tasks.size()));
            storage.saveList(tasks);
        } catch (RuntimeException wrongFormat) {
            throw new DukeException("Please enter the command in the correct format:\n" + correctFormat());
        }
    }
    private Task makeTask() throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        Task task = new Task(this.command);
        String cmd = this.command.replace(typeOfTask + " ", "");
        switch (typeOfTask) {
        case "todo":
            task = new ToDo(cmd);
            break;
        case "deadline":
            task = new Deadline(cmd);
            break;
        case "event":
            task = new Event(cmd);
            break;
        }
        return task;
    }
    public String correctFormat() {
        String format = "THE TASK";
        switch(this.typeOfTask) {
        case "todo":
            format = "todo [TASK]";
            break;
        case "deadline":
            format = "deadline [TASK] /by yyyy-mm-ddThh:mm:ss";
            break;
        case "event":
            format = "event [TASK] /from TIME /to TIME";
        }
        return format;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
