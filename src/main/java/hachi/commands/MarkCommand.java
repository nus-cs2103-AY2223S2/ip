package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Task;


public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5 || !Character.isDigit(input.charAt(5))) {
                throw new HachiExceptions("☹ Ohno! I don't know which task to mark/unmark :(");
            }
            int index = Integer.parseInt(input.substring(5));
            Task task = toDoList.get(index - 1);
            task.mark();
            storage.saveTaskList(toDoList);
            System.out.println("   good job! I've marked this task as done: " + "\n" + task);
            return true;
        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
            return false;
        }
        catch (IndexOutOfBoundsException e1) {
            System.out.println(" There is no task to be marked");
            return false;
        }

    }
}
