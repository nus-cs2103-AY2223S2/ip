package Duke.Commands;

import Duke.entities.Deadline;
import Duke.entities.Event;
import Duke.entities.Task;
import Duke.entities.TaskList;

import java.time.LocalDate;

import Duke.Ui.Ui;

/**
 * Represents the Duke.Commands.SearchCommand of the Chat bot.
 */
public class SearchDateCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a SearchCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param date The date to be searched for in the TaskList.
     * @param ui   User interface of the Chat bot.
     */
    public void processCommand(TaskList list, String date, Ui ui) {
        LocalDate now = LocalDate.parse(date.trim());
        ui.print("Tasks occurring on " + now.toString() + ":");
        for (int i = 0; i < list.getSize(); i++) {
            Task currTask = list.getTask(i);
            if (currTask instanceof Deadline) {
                if (now.equals(((Deadline) currTask).getBy())) {
                    ui.print(currTask.toString());
                }
            } else if (currTask instanceof Event) {
                if (now.equals(((Event) currTask).getEndDate()) || now.equals(((Event) currTask).getStartDate())) {
                    ui.print(currTask.toString());
                }
            }
        }
        ui.printDivider();
    }
}
