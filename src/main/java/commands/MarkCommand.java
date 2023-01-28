package commands;

import main.HachiExceptions;
import main.Storage;
import main.TaskList;
import main.Ui;
import tasks.Task;


public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5 || !Character.isDigit(input.charAt(5))) {
                throw new HachiExceptions("☹ Ohno! I don't know which task to mark/unmark :(");
            }
            int index = Integer.parseInt(input.substring(5));
            Task task = toDoList.get(index - 1);
            task.mark();
            storage.saveTaskList(toDoList);
            System.out.println("   good job! I've marked this task as done: " + "\n" + task);
        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
        }
        catch (IndexOutOfBoundsException e1) {
            System.out.println(" There is no task to be marked");
        }
    }
}
