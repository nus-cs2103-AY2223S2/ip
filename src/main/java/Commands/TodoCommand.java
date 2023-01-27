package Commands;

import entities.Task;
import entities.TaskList;
import entities.Todo;
import Ui.Ui;

public class TodoCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(TaskList list, String desc, Ui ui) {
        Todo task = new Todo(desc);
        list.addTask(task);
        ui.print(String.format("alright, I've added the following task:\n %s", task.toString()));
        ui.print(String.format("Now you have %d tasks in the list.", list.getSize()));
        ui.printDivider();
    }

}
