package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DueCommand extends Command {
    private static final String DATE_PARSE = "yyyy-MM-dd";
    private final LocalDate byDate;

    public DueCommand(String byDate) {
        DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_PARSE);
        this.byDate = LocalDate.parse(byDate, formatterParse);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.showTasksDue(byDate, taskList);
    }

    @Override
    public String toString() {
        return "due";
    }
}
