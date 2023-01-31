package duke.commands;

import duke.database.Database;
import duke.task.Deadline;
import duke.exception.blankfieldexceptions.BlankFieldDeadlineException;
import duke.exception.includeexceptions.IncludeByException;
import duke.exception.InvalidDateException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AddDeadlineCommand extends Command {

    private final String commandBody;

    public AddDeadlineCommand(String commandBody) {
        super();
        this.commandBody = commandBody;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws IncludeByException, BlankFieldDeadlineException, InvalidDateException {
        // Extract deadline date and duke.task item.
        String[] lines = this.commandBody.split(" ");
        boolean by = false;
        StringBuilder task = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        for (String line : lines) {
            if (Objects.equals(line, "/by")) {
                by = true;
            } else if (!by) {
                task.append(" ").append(line);
            } else {
                deadline.append(" ").append(line);
            }
        }

        if (!by) {
            throw new IncludeByException();
        }
        if (task.toString().trim().isEmpty() || deadline.toString().trim().isEmpty()) {
            throw new BlankFieldDeadlineException();
        }

        try {
            Deadline newDeadline = new Deadline(task.toString(), deadline.toString().stripLeading());
            taskList.addTask(newDeadline);
            ui.response(FRAME + "\n" +
                    "     Got it. I've added this task:" + "\n" +
                    "     " + newDeadline.status() + "\n" +
                    "     Now you have " + taskList.length() + " tasks in the list" + "\n" +
                    FRAME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
