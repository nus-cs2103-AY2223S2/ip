package iris.command;

import iris.TaskList;
import iris.Ui;
import iris.TaskStore;
import iris.exception.DateTimeException;
import iris.exception.IrisException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FilterCommand extends Command {
    LocalDateTime start;
    LocalDateTime end;

    public FilterCommand(String str) throws DateTimeException {
        try {
            start = LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay().minusNanos(1);
            end = LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy")).plusDays(1).atStartOfDay();
        } catch (DateTimeParseException e) {
            Ui.output(e.getMessage());
            throw new DateTimeException();
        }
    }


    public FilterCommand(String str1, String str2) throws DateTimeException {
        try {
            start = LocalDateTime.parse(str1, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")).minusNanos(1);
            end = LocalDateTime.parse(str2, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")).plusNanos(1);
        } catch (DateTimeParseException e) {
            Ui.output(e.getMessage());
            throw new DateTimeException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        Ui.output(tasks.dateFilter(start, end));
    }
}
