package controllers;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.time.LocalDate;
import java.util.regex.Matcher;

public class DateCommand extends Command {
    private final String args;

    public DateCommand(String args) {
        super(CommandType.DATE);
        this.args = args;
    }

    @Override
    public void execute() throws DukeException {
        Matcher matcher = DATE_FORMAT.matcher(args.strip());
        if (matcher.find()) {
            TaskList.filter(task -> task.activeOn(LocalDate.parse(args.split(" ")[1])));
        } else {
            throw new DukeException("Invalid Date Format. Please follow: date [yyyy-mm-dd].");
        }
    }
}
