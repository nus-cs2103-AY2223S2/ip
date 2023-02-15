package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.main.Storage;
import hachi.tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a user instruction to add a task with deadline to the list
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * DeadlineCommand constructor.
     *
     * @param input The user's input string.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index_ddl = input.indexOf("/");
            if (input.length() > 9 && !input.contains("/")) {
                throw new HachiExceptions(ui.noDeadlineMessage());
            }
            if (index_ddl - 1 < 9) {
                throw new HachiExceptions(ui.emptyDescription());
            }
            Deadline ddlTask = new Deadline(input.substring(9, index_ddl - 1), LocalDate.parse(input.substring(index_ddl + 4, input.length()), DateTimeFormatter.ofPattern("dd MMM yyyy")));
            tasks.add(ddlTask);

            return ui.showAdded(tasks, ddlTask);
        } catch (HachiExceptions e) {
            return e.getMessage();
        } catch (DateTimeParseException e1) {
            return ui.wrongDeadlineFormat();
        }
    }
}

