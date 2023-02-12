package duke;

import java.util.Scanner;

import duke.tasks.Task;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static String showWelcome() {
        return "Hello! I'm TheshBot \nWhat can I do for you?";
    }

    public static String showBye() {
        return "That was fast! Hope to see you again soon!";
    }

    public static String showError(Exception e) {
        return e.toString();
    }
    
    public static String showList(TaskList listOfTasks) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            result += String.format("%d.%s\n", i + 1, currentTask.toString());
        }
        return result;
    }
    public static String showFind(TaskList filteredList) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < filteredList.size(); i++) {
            Task currentTask = filteredList.get(i);
            result += String.format("%d.%s\n", i + 1, currentTask.toString());
        }
        return result;
    }

    public void showCreateNewFile() {
        System.out.println("Creating new file...");
    }
    
    public String[] readLine() {
        String currentInput = scanner.nextLine();
        String[] currentInputArray = currentInput.split(" ", 2);
        return currentInputArray;
    }
    
    
    public static String showAdd(TaskList listOfTasks, String taskName) {
        String result = "Got it. I've added this task:\n" +
        taskName +
        String.format("\nNow you have %d tasks in the list.", listOfTasks.size());
        return result;
    }
    public static String showDelete(Task deletedTask, TaskList listOfTasks) {
        String result = "OK. I've removed this task:\n" + 
                String.format("%s\n", deletedTask) + 
                String.format("Now you ahve %d tasks in your list", listOfTasks.size());
        return result;
    }

    public static String showUnmark(String taskName) {
        return "OK, I've marked this task as not done yet:\n" + taskName;
    }

    public static String showMark(String taskName) {
        return "Nice! I've marked this task as done:\n" + taskName;
    }
}
