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
                       System.out.println(TAB + "Bye! Please come back again ૮꒰˶• ༝ •˶꒱ა");
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
                   default: // types of tasks
                       int taskType;
                       String description;

                       if (command.equals("todo")) {
                           description = input.substring(5);
                           taskType = 0; // todo
                       } else if (command.equals("deadline")) {
                           description = input.substring(9);
                           taskType = 1; // deadline
                       } else {
                           description = input.substring(6);
                           taskType = 2; // event
                       }

                       tb.addTask(taskType, description);
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
        System.out.println(Duke.TAB + "Here's what I have for you:");
        for (Task t : listOfTasks) {
            System.out.print(Duke.TAB + Duke.TAB + counter++ + ". ");
            System.out.print(t.toString() + "\n");
        }
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /** This function adds a new task into listOfTasks, and updates the number of tasks in the TaskBook.
     *
     * @param taskType
     * @param description
     */
    public void addTask(int taskType, String description) {
        numOfTasks++;
        Task t;
        if (taskType == 0)  //todo
            t = new Todo(description);
        else if (taskType == 1) // deadline
            t = new Deadline(description.split("/")[0],
                    description.split("/by")[1]);
        else // event
            t = new Event(description.split("/")[0],
                    description.split("/from")[1].split("/to")[0],
                    description.split("/to")[1]);

        listOfTasks.add(t);

        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Done and ready to go! I've added this task for ya:");
        System.out.println(Duke.TAB + Duke.TAB + t.toString());
        printNumberOfTasks();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /** This function marks the given task at index as done.
     *
     * @param index The index of the task in listOfTasks, starting from index 1.
     */
    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Alright~ I'll set the task as done! ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა");
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
        System.out.println(Duke.TAB + "Okay! I'll set the task as not done. ૮₍ ˃ ⤙ ˂ ₎ა");
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

    public void printNumberOfTasks() {
        System.out.println(Duke.TAB + "You now have " + numOfTasks + " tasks in the list ૮꒰ˊᗜˋ* ꒱ა");
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
    protected boolean isDone;
    protected String description;

    /** This constructor creates a Task instance.
     *
     * @param description The name of the task to be created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** This function marks the task as done */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(Duke.TAB + Duke.TAB + this.toString());
    }

    /** This function marks the task as not done */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println(Duke.TAB + Duke.TAB + this.toString());
    }

    /** This function returns a string denoting if task is done or not.
     *
     * @return [ ]: Marked as not done
     *      or [X]: Marked as done.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String toString() {
        String output = getStatusIcon() + " " + description;
        return output;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to +")";
    }
}

