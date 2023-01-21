import java.util.Scanner;

/** Duke chat bot.
 * @author Hee Jia Yuan
 */
public class Duke {
    /**
     * Runs the main chat program.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorage();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String firstWord = userInput.split(" ", 2)[0];

            if (userInput.equals("bye")) {
                respond("Goodbye! Have a nice day ahead.\n");
                break;

            } if (userInput.equals("list")) {
                taskStorage.listTasks();
                continue;

            } if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                String secondWord = userInput.split(" ", 3)[1];
                int taskNumber = Integer.parseInt(secondWord);
                taskStorage.updateTask(taskNumber);
                respond("The status of your specified task has been updated!");

            } else {
                respond("Added: " + userInput);
                taskStorage.storeTasks(new Task(userInput));
            }
        }
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "How may I be of assistance today? :)");
    }

    /**
     * Responds to the user, given a message.
     * @param message A String which will be the bot's response message.
     */
    public static void respond(String message) {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n";
        String botDivider = "\n~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider + "\n" + message + botDivider);
    }


}


/**
 * A storage of Tasks.
 */
class TaskStorage {
    /**
     * An array of Tasks.
     */
    Task[] storage = new Task[100];
    /**
     * Keeps track of number of Tasks stored.
     */
    int storageCount = 0;

    /**
     * Lists all the tasks stored.
     */
    public void listTasks() {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n";
        String botDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider);

        for (int i = 0; i < this.storageCount; i++) {
            String output = this.storage[i].provideDetails();
            System.out.println((i + 1) + "." + output);
        }

        System.out.println(botDivider);
    }


    /**
     * Stores a new task in storage.
     * @param task The task to be stored.
     */
    public void storeTasks(Task task) {
        this.storage[this.storageCount] = task;
        this.storageCount++;
    }

    /**
     * Updates the status of a Task.
     * @param number The number representing the task to be updated.
     */
    public void updateTask(int number) {
        this.storage[number -  1].updateTask();
    }
}


/**
 * Encapsulates a Task.
 */
class Task {
    /**
     * Status of the Task.
     */
    boolean completed = false;

    /**
     * Details of the Task.
     */
    String task;

    public Task(String task) {
        this.task = task;
    }

    /**
     * Provides Details of the Task.
     * @return String detail message of Task.
     */
    public String provideDetails() {
        return completed ? "[x] " + task
                         : "[ ] " + task;
    }

    /**
     * Flips the status of the Task.
     */
    public void updateTask() {
        this.completed = !this.completed;
    }

}
