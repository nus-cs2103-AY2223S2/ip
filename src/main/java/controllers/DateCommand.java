package controllers;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.regex.Matcher;

public class DateCommand extends Command {
    private final String args;

    public DateCommand(String args) {
        super(CommandType.DATE);
        this.args = args;
    }

    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher matcher = DATE_FORMAT.matcher(args.strip());
        if (matcher.find()) {
            store.filter(task -> task.activeOn(LocalDate.parse(args.split(" ")[1])),
                    "There are no active tasks on this date!");
        } else {
            String DATE_FORMAT_ERROR = "Invalid Date Format. Please follow: date [yyyy-mm-dd].";
            throw new DukeException(DATE_FORMAT_ERROR);
        }
    }
}
