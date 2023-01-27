package Duke.Commands;

import Duke.entities.Deadline;
import Duke.entities.Event;
import Duke.entities.Task;
import Duke.entities.TaskList;

import java.time.LocalDate;

import Duke.Ui.Ui;

public class SameDateCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

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
                if (now.equals(((Event) currTask).getTo()) || now.equals(((Event) currTask).getFrom())) {
                    ui.print(currTask.toString());
                }
            }
        }
        ui.printDivider();
    }
}
