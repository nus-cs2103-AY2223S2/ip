package duke.commands;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.database.Database;
import duke.exception.InvalidDateException;
import duke.exception.blankfieldexceptions.BlankFieldDeadlineException;
import duke.exception.includeexceptions.IncludeByException;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * Represents a command to add a deadline task to the TaskList.
 */
public class AddDeadlineCommand extends Command {

    private final String commandBody;

    /**
     * Represents a command to add a deadline task to the TaskList.
     *
     * @param commandBody Parameters of the command, pre-parsed.
     */
    public AddDeadlineCommand(String commandBody) {
        super();
        this.commandBody = commandBody;
    }

    /**
     * Executes the generated AddDeadlineCommand by adding a new Deadline task into the TaskList and gives a response
     * to the Ui.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     * @throws IncludeByException /by was not included in the command.
     * @throws BlankFieldDeadlineException No task and/or date was included in the command.
     * @throws InvalidDateException Date given in the command is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws IncludeByException,
            BlankFieldDeadlineException, InvalidDateException {
        assert this.isActive();
        // Extract deadline date and duke.task item.
        String[] lines = this.commandBody.split(" ");
        boolean hasByKeyword = false;
        StringBuilder task = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        for (String line : lines) {
            if (Objects.equals(line, "/by")) {
                hasByKeyword = true;
            } else if (!hasByKeyword) {
                task.append(" ").append(line);
            } else {
                deadline.append(" ").append(line);
            }
        }

        if (!hasByKeyword) {
            throw new IncludeByException();
        }
        if (task.toString().trim().isEmpty() || deadline.toString().trim().isEmpty()) {
            throw new BlankFieldDeadlineException();
        }

        try {
            Deadline newDeadline = new Deadline(task.toString(), deadline.toString().stripLeading());
            taskList.addTask(newDeadline);
            ui.response(FRAME + "\n"
                    + "Got it. I've added this task:" + "\n"
                    + newDeadline.getStatus() + "\n"
                    + "Now you have " + taskList.length() + " tasks in the list" + "\n"
                    + FRAME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
