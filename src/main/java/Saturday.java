import collections.TaskList;
import exceptions.SaturdayException;
import models.Task;
import utilities.Extensions;

import java.util.Scanner;

public class Saturday {
    private static boolean isActive = true;
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Extensions.divider();
        Extensions.output("Hello! I'm Saturday\n\tWhat can I do for you?");
        Extensions.divider();
        Extensions.newline();

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            Extensions.divider();
            try {
                Command command = Command.getCommand(input);
                command.execute(input);
            } catch (SaturdayException e) {
                Extensions.output(e.getMessage());
            }
            Extensions.divider();
            Extensions.newline();
        }
    }

    public static void addToTaskList(Task task) {
        taskList.add(task);
        Extensions.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void markTaskList(int i) {
        taskList.mark(i);
    }

    public static void unMarkTaskList(int i) {
        taskList.unMark(i);
    }

    public static Task getTask(int i) {
        return taskList.get(i);
    }

    public static int getTaskListSize() {
        return taskList.size();
    }

    public static Task removeTask(int i) {
        return taskList.remove(i);
    }

    public static void displayList() {
        Extensions.output("Here are the tasks in your list:\n\t" + taskList.toString());
    }

    public static void exit() {
        isActive = false;
        Extensions.output("Bye. Hope to see you again soon!");
    }

}
