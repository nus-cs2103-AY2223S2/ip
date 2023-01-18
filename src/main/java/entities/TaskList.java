package entities;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList {
    private static final List<Task> taskList = new ArrayList<>();

    public static void filter(Predicate<? super Task> predicate) {
        List<Task> filteredList = taskList.stream().filter(predicate).collect(Collectors.toList());
        if (filteredList.size() == 0) {
            System.out.println("There are no active tasks on this date!");
            return;
        }
        filteredList.forEach(System.out::println);
    }

    public static void addTask(Task task, boolean print) {
        taskList.add(task);
        if (print) {
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public static void addTask(Task task) {
        addTask(task, true);
    }

    public static Optional<Task> getTask(Integer key) {
        if (!isValidKey(key)) {
            System.out.println("This task don't exists! Please select one from the list.");
            return Optional.empty();
        }
        // accounts for 0-based indexing
        return Optional.of(taskList.get(key - 1));
    }

    private static boolean isValidKey(Integer key) {
        return (key <= taskList.size() && key > 0);
    }

    public static void deleteTask(Integer key) {
        if (isValidKey(key)) {
            Task task = taskList.get(key - 1);
            taskList.remove(key - 1);
            System.out.println("Noted. I've removed the task:");
            System.out.println("\t" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("This task don't exists! Please select one from the list.");
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public static String listTasks() {
        if (taskList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            ListIterator<Task> it = taskList.listIterator();
            while (it.hasNext()) {
                sb.append(it.nextIndex() + 1).append(". ").append(it.next()).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } else {
            return "There are no outstanding tasks!";
        }
    }

    @Override
    public String toString() {
        return listTasks();
    }
}
