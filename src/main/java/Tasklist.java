import Exceptions.OutOfBoundsException;

import java.util.ArrayList;

public class Tasklist {
    protected ArrayList<Task> taskList;

    public Tasklist(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // List methods
    public static void getList(ArrayList<Task> taskList) {
        int taskSize = taskList.size();
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskSize; i++) {
            int count = i + 1;
            String printItem = count + ". " + taskList.get(i);
            Format.indent(printItem);
        }
    }
    public static void removeTask(ArrayList<Task> taskList, int index) {
        Format.line();
        System.out.println("Noted, removing this task.");
        Format.indent("" + taskList.get(index));
        taskList.remove(index);
        Format.checkList(taskList);
    }

    public static void listTasks(ArrayList<Task> taskList) {
        if (taskList.size() > 0) {
            Format.line();
            getList(taskList);
            Format.line();
        } else {
            Format.line();
            System.out.println("HEY! You have no tasks. Hooray!");
            Format.line();
        }
    }

    public static void deleteTask(ArrayList<Task> taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                removeTask(taskList, index);
            } else {
                throw new OutOfBoundsException("");
            }
        } catch (OutOfBoundsException e) {
            Format.line();
            System.out.println(e.getMessage());
            Format.line();
        } catch (NumberFormatException e) {
            Format.line();
            System.out.println("Hey! Please enter a valid number!");
            Format.line();
        }
    }
}
