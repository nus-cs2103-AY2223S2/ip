import java.util.*;
import java.io.*;

public class Duke {
    public static String TAB = "    ";
    public static String HOR_BAR = "✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";

    public static void greeting() {
        /* Greetings from application */
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
           while (!endFlag) {
               String input = br.readLine();
               String[] splitInput = input.split(" ");
               String command = splitInput[0];
               int index;

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

               }
           }
        } finally {
            br.close();
        }
    }
}

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

    /** This function prints a line of status denoted by [ ]: Marked as not done
     *  or [X]: Marked as done, followed by the task name.
     */
    public void printTaskWithStatus(Task t) {
        System.out.print(t.doneStatus() + " ");
        t.printTaskName();
        System.out.print("\n");
    }

    public void addTask(String taskName) {
        numOfTasks++;
        Task t = new Task(taskName);
        listOfTasks.add(t);

        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "added: " + taskName);
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Alright~ I'll set the task as done!");
        t.markAsDone();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    public void markNotDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR );
        System.out.println(Duke.TAB + "Okay! I'll set the task as not done.");
        t.markAsNotDone();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    public boolean indexWithinRange(int index) {
        return index > 0 && index <= numOfTasks;
    }

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

    public void printTaskName() {
        System.out.print(taskName);
    }

    public String doneStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
}

