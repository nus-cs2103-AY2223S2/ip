package dukes.command;

import dukes.util.*;
import dukes.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    protected String taskTag;
    protected LocalDate deadline;
    protected LocalDate start;
    protected LocalDate end;

    public AddCommand(boolean isExit, boolean isValid,
                       String header, String body, String taskTag) {
        super(isExit, isValid, header, body);
        this.taskTag = taskTag;
        this.deadline = LocalDate.parse("1970-01-01");
        this.start = LocalDate.parse("1970-01-01");
        this.end = LocalDate.parse("1970-01-01");
    }

    public AddCommand(boolean isExit, boolean isValid,
                      String header, String body, String taskTag,
                      LocalDate deadline) {
        super(isExit, isValid, header, body);
        this.taskTag = taskTag;
        this.deadline = deadline;
        this.start = LocalDate.parse("1970-01-01");
        this.end = LocalDate.parse("1970-01-01");
    }

    public AddCommand(boolean isExit, boolean isValid,
                      String header, String body, String taskTag,
                      LocalDate start, LocalDate end) {
        super(isExit, isValid, header, body);
        this.taskTag = taskTag;
        this.deadline = LocalDate.parse("1970-01-01");
        this.start = start;
        this.end = end;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        // I dunno what is the point for ui to be here...
        // to print?
        List<Task> taskList = tasks.getTaskList();
        Task theTask;
        if (this.taskTag.equals("T")) {
            // here body is just taskName
            theTask = new ToDo(this.body);
        } else if (this.taskTag.equals("D")) {
            theTask = new DeadLine(this.body, this.deadline);
        } else {
            theTask = new Event(this.body, this.start, this.end);
        }
        taskList.add(theTask);
        ui.showAdd(theTask, tasks);
        storage.save(tasks);
    }
}
