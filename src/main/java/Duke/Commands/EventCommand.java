package Duke.Commands;

import Duke.entities.Event;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

public class EventCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(TaskList list, String desc, String from, String to, Ui ui) {
        Event event = new Event(desc, from, to);
        list.addTask(event);
        ui.print(String.format("Sure!, I've added the following events:\n %s", event.toString()));
        ui.print(String.format("Now you have %d tasks in the list.", list.getSize()));
        ui.printDivider();
    }
}
