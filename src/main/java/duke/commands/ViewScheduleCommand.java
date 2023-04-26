package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that let users view schedule for a particular date.
 */
public class ViewScheduleCommand extends Command {
    protected String userInput;

    public ViewScheduleCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(userInput);
            ArrayList<Task> schedule = tasks.getTasks(date);
            if (schedule.size() == 0) {
                ui.showToUser("Your schedule is empty for " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } else {
                ui.showToUser("Here is your schedule for "
                        + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
            }

            for (int i = 0; i < schedule.size(); i++) {
                ui.showToUser((i + 1) + "." + schedule.get(i).toString());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date!!");
        }
    }
}
