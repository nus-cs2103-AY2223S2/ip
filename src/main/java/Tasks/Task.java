package Tasks;
import Exceptions.*;

public class Task {
    private static Task[] arr = new Task[100];
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
                System.out.println("    " + (i+1) + ") " + arr[i]);
            }
        }
    }

    public static void addTask(String command, String userInput) throws UnknownTaskException, NoTaskDescriptionException {
        String[] dates = userInput.split("/");
        switch(command.toUpperCase()) {
            case "TODO": 
                arr[curr] = new Todo(userInput);
                break;
            case "DEADLINE":
                arr[curr] = new Deadline(dates[0], dates[1]);
                break;
            case "EVENT":
                arr[curr] = new Event(dates[0], dates[1], dates[2]);
                break;
            default:
                throw new UnknownTaskException(command);
        }
        System.out.println("The following task has been added to your list: \n" + arr[curr] 
                            + "\nCurrently, your list has " + ++curr + " tasks.");
    }

    public static void markTasks(int task) {
        arr[task].isChecked = true;
        System.out.println("This task is marked as done: \n    " + arr[task]);
    }

    public static void unmarkTasks(int task) {
        arr[task].isChecked = false;
        System.out.println("Okay. This task is marked as not done yet: \n    " + arr[task]);
    }

    protected String markToString() {
        return this.isChecked ? "[X]" : "[ ]";
    }

    protected String TasktoString() {
        return markToString() + " " + this.name;
    }
}
