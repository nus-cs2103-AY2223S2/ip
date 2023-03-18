package jeo.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jeo.database.TaskList;
import jeo.ui.Ui;

/**
 * Represents a Due Command to get tasks due on a specified date.
 * @author Goh Jun How
 * @version 0.3
 */
public class DueCommand extends Command {
    private static final String DATE_PARSE = "yyyy-MM-dd";
    private final LocalDate byDate;

    /**
     * Creates a DueCommand object.
     * @param byDate input to be parsed as LocalDate
     */
    public DueCommand(String byDate) {
        DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_PARSE);
        this.byDate = LocalDate.parse(byDate, formatterParse);
    }

    /**
     * Executes the Due Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.showTasksDue(byDate, taskList);
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "due";
    }
}
