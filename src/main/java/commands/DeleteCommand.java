package commands;

import main.HachiExceptions;
import main.Storage;
import main.TaskList;
import main.Ui;
import tasks.Task;


public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() < 7) {
                throw new HachiExceptions("☹ Ohno! I don't know which task to delete :(");
            }
            int index_de = Integer.parseInt(input.substring(7));
            Task task = toDoList.get(index_de - 1);
            toDoList.remove(index_de - 1);
            storage.saveTaskList(toDoList);
            System.out.println("   okie dokie. I've removed this task:\n" + task);
            System.out.println("   Now you have " + toDoList.size() + " hachi.tasks in the list.");

        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("  There is no task to be deleted..");
        }
    }
}