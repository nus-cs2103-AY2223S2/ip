package Nerd.Commands;

import Nerd.entities.Deadline;
import Nerd.entities.Event;
import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents the Duke.Commands.DeadlineCommand of the Chat bot.
 */
public class ReminderCommand extends Command {
    private int rangeOfDays;
    private LocalDate currentDate;

    /**
     * Constructor for a Reminder command.
     *
     * @param rangeOfDays Days remaining before actual task
     */
    public ReminderCommand(int rangeOfDays) {
        this.rangeOfDays = rangeOfDays;
        this.currentDate = LocalDate.now();
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a Reminder command.
     *
     * @param list The TaskList object that stores tasks.
     * @param ui   User interface of the Chat bot.
     * @return A string output for adding a deadline task.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        String output = "REMINDERS!:\n";
        for (int i = 0; i < list.getSize(); i++) {
            Task currTask = list.getTask(i);
            if (currTask instanceof Deadline) {
                LocalDate deadline = ((Deadline) currTask).getBy();
                Period difference = Period.between(currentDate, deadline);
                int days = difference.getDays();
                if (days >= 0 && days <= rangeOfDays) {
                    output += String.format("%d days to Deadline:\n%s\n", days, currTask.toString());
                }
            }
            if (currTask instanceof Event) {
                LocalDate startDate = ((Event) currTask).getStartDate();
                Period difference = Period.between(currentDate, startDate);
                int days = difference.getDays();
                if (days >= 0 && days <= rangeOfDays) {
                    output += String.format("%d days to Event:\n%s\n", days, currTask.toString());
                }
            }
        }
        return output;
    }
}
