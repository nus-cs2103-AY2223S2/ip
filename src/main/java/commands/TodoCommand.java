package commands;

import main.HachiExceptions;
import main.Storage;
import main.TaskList;
import main.Ui;
import tasks.Todo;

public class TodoCommand extends Command {
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5) {
                throw new HachiExceptions("☹ Ohno! The description cannot be empty.");
            }
            Todo tdTask = new Todo(input.substring(5, input.length()));
            System.out.println("   okie dokie. I've added this task:" + "\n" + tdTask);
            toDoList.add(tdTask);
            storage.saveTaskList(toDoList);
            System.out.println("   Now you have " + toDoList.size() + " hachi.tasks in the list.");
        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
