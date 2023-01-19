package tasks;

import java.util.ArrayList;
import exceptions.*;

public class Task {
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int curr = 0;

    protected String name;
    protected Boolean isChecked = false;

    protected Task(String name, String type) throws NoTaskDescriptionException {
        if (name.isBlank()) {
            throw new NoTaskDescriptionException(type);
        } 
        this.name = name;
    }
    
    public static void listTasks() {
        if (curr == 0) {
            System.out.println("There are currently no items in your list. ");
        } else {
            System.out.println("Here are the items in your list: \n");
            for (int i = 0; i < curr; i++) {
                System.out.println("    " + (i+1) + ") " + arr.get(i));
            }
        }
    }

    public static void addTask(String command, String userInput) throws UnknownTaskException, NoTaskDescriptionException {
        String[] dates = userInput.split("/");
        switch(command.toUpperCase()) {
            case "TODO": 
                arr.add(new Todo(userInput));
                break;
            case "DEADLINE":
                arr.add(new Deadline(dates[0], dates[1]));
                break;
            case "EVENT":
                arr.add(new Event(dates[0], dates[1], dates[2]));
                break;
            default:
                throw new UnknownTaskException(command);
        }
        System.out.println("The following task has been added to your list: \n    " + arr.get(curr) 
                            + "\n \nCurrently, your list has " + ++curr + (curr== 1 ? " task" : " tasks."));
    }

    public static void deleteTask(int task) {
        System.out.println("The following task has been removed: \n    " + arr.get(task) 
                            + "\n \nCurrently, you have " + --curr + (curr==1 ? " task" : " tasks") + " left in your list.");
        arr.remove(task);
    }

    public static void markTasks(int task) {
        arr.get(task).isChecked = true;
        System.out.println("This task is marked as done: \n    " + arr.get(task));
    }

    public static void unmarkTasks(int task) {
        arr.get(task).isChecked = false;
        System.out.println("Okay. This task is marked as not done yet: \n    " + arr.get(task));
    }

    protected String markToString() {
        return this.isChecked ? "[X]" : "[ ]";
    }

    protected String TasktoString() {
        return markToString() + " " + this.name;
    }
}
