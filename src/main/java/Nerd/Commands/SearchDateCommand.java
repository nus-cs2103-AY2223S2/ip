package Nerd.Commands;

import Nerd.entities.Deadline;
import Nerd.entities.Event;
import Nerd.entities.Task;
import Nerd.entities.TaskList;

import java.time.LocalDate;

import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.SearchCommand of the Chat bot.
 */
public class SearchDateCommand extends Command {

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a SearchCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param date The date to be searched for in the TaskList.
     * @param ui   User interface of the Chat bot.
     */
    public String processCommand(TaskList list, String date, Ui ui) {
        LocalDate now = LocalDate.parse(date.trim());
        String output = "Tasks occurring on " + now.toString() + ":\n";
        for (int i = 0; i < list.getSize(); i++) {
            Task currTask = list.getTask(i);
            if (currTask instanceof Deadline) {
                if (now.equals(((Deadline) currTask).getBy())) {
                    output = output + currTask.toString() + "\n";
                }
            } else if (currTask instanceof Event) {
                if (now.equals(((Event) currTask).getEndDate()) || now.equals(((Event) currTask).getStartDate())) {
                    output = output + currTask.toString() + "\n";
                }
            }
        }
        return output;
    }
}
