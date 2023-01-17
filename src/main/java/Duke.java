import java.util.*;
import java.io.*;

public class Duke {
    public static String TAB = "    ";
    public static String HOR_BAR = "✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";

    /**
     * This function is called at the beginning to greet the user.
     */
    public static void greeting() {
        System.out.println(TAB + HOR_BAR);
        String logo = TAB + "૮ ˶ᵔ ᵕ ᵔ˶ ა";
        System.out.println(TAB + "Hey there! I'm Berry the Bunny~\n" + logo + "\n"
                + "    What are you looking to plan today?");
        System.out.println(TAB + HOR_BAR);
    }

    public static void main(String[] args) throws IOException {
        /* Initialise BufferedReader */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /* Initialise TaskBook class */
        TaskBook tb = new TaskBook();
        /* Initialise endFlag */
        boolean endFlag = false;

        try {
           greeting();
           /* While user has not exited application */
           while (!endFlag) {
               String input = br.readLine();
               String[] splitInput = input.split(" ");
               String command = splitInput[0];
               int index;

               /* Handle commands */
               switch (command) {
                   case "bye":
                       System.out.println(TAB + HOR_BAR);
                       System.out.println(TAB + "Bye! Please come back again ><!");
                       System.out.println(TAB + HOR_BAR);
                       endFlag = true;
                       break;
                   case "list":
                       tb.listTasks();
                       break;
                   case "mark":
                       index = Integer.parseInt(splitInput[1]);
                       if (tb.indexWithinRange(index)) {
                           tb.markDone(index);
                       } else {
                           tb.printOutOfRangeDialogue();
                       }
                       break;
                   case "unmark":
                       index = Integer.parseInt(splitInput[1]);
                       if (tb.indexWithinRange(index)) {
                           tb.markNotDone(index);
                       } else {
                           tb.printOutOfRangeDialogue();
                       }
                       break;
                   default:
                       tb.addTask(input);
                       break;
               }
           }
        } finally {
            /* Close the BufferedReader */
            br.close();
        }
    }
}

/** This class manages the Task(s) in a TaskBook recording the number of tasks
 * and task names.
 */
class TaskBook {
    private static int numOfTasks;
    public static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    /** This function lists out all the tasks in listOfTasks by order of
     * its addition into listOfTasks */
    public void listTasks() {
        int counter = 1;
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        for (Task t : listOfTasks) {
            System.out.print(Duke.TAB + counter++ + ". ");
            printTaskWithStatus(t);
        }
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /** This function prints out a line of the task with its status.
     *
     * @param t The Task to print its status and name.
     */
    public void printTaskWithStatus(Task t) {
        System.out.print(t.doneStatus() + " ");
        t.printTaskName();
        System.out.print("\n");
    }

    /** This function adds a new task into listOfTasks, and updates the number of tasks in the TaskBook.
     *
      * @param taskName The name of the task to add to listOfTasks.
     */
    public void addTask(String taskName) {
        numOfTasks++;
        Task t = new Task(taskName);
        listOfTasks.add(t);

        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "added: " + taskName);
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /** This function marks the given task at index as done.
     *
     * @param index The index of the task in listOfTasks, starting from index 1.
     */
    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Alright~ I'll set the task as done!");
        t.markAsDone();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /** This function marks the given task at index as not done.
     *
     * @param index The index of the task in listOfTasks, starting from index 1.
     */
    public void markNotDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR );
        System.out.println(Duke.TAB + "Okay! I'll set the task as not done.");
        t.markAsNotDone();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /** This function checks against the number of tasks in the task book and checks if it is out of range.
     *
     * @param index The index of the task in listOfTasks, starting from index 1.
     * @return true if index is in range
     */
    public boolean indexWithinRange(int index) {
        return index > 0 && index <= numOfTasks;
    }

    /** This function prints a out of range dialogue.
     */
    public void printOutOfRangeDialogue() {
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Oh no! I cannot find a task with task number :< \n" +
                Duke.TAB + "You can check your task number by asking me to 'list'");
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }
}

class Task {
    private boolean isDone;
    private String taskName;

    /** This constructor creates a Task instance.
     *
     * @param taskName The name of the task to be created.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /** This function marks the task as done */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(Duke.TAB + " [X] " + taskName);
    }

    /** This function marks the task as not done */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println(Duke.TAB + " [ ] " + taskName);
    }

    /** This function prints out the task name. */
    public void printTaskName() {
        System.out.print(taskName);
    }

    /** This function returns a string denoting if task is done or not.
     *
     * @return [ ]: Marked as not done
     *      or [X]: Marked as done.
     */
    public String doneStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
}

