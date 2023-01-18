import java.util.*;
import java.io.*;

/**
 * DukeException handles exceptions specific to Duke
 */
class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

public class Duke {
    public static String TAB = "    ";
    public static String HOR_BAR = "✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";

    /**
     * This function is called at the beginning to greet the user.
     */
    public static void greetingDialogue() {
        System.out.println(TAB + HOR_BAR);
        String logo = TAB + "૮ ˶ᵔ ᵕ ᵔ˶ ა";
        System.out.println(TAB + "Hey there! I'm Berry the Bunny~\n" + logo + "\n"
                + "    What are you looking to plan today?");
        System.out.println(TAB + HOR_BAR);
    }

    /**
     * This function is called at the end to see the user off.
     */
    public static void byeDialogue() {
        System.out.println(TAB + HOR_BAR);
        System.out.println(TAB + "Bye! Please come back again ૮꒰˶• ༝ •˶꒱ა");
        System.out.println(TAB + HOR_BAR);
    }

    public static String cutDescription(int taskType, String input) throws DukeException {
        try {
            if (taskType == 1) return input.substring(5);
            else if (taskType == 2) return input.substring(9);
            else return input.substring(6);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(unreadableMessage());
        }
    }

    /**
     * This function prints a out of range message.
     */
    public static String outOfRangeMessage() {
        String message =
                Duke.TAB + Duke.HOR_BAR + "\n" +
                        Duke.TAB + "Oh no! I cannot find a task with that task number.\n" +
                        Duke.TAB + "You can check them again by asking me to 'list'\n"+
                        Duke.TAB + Duke.HOR_BAR;
        return message;
    }

    /**
     * This function prints a out of range message.
     */
    public static String unreadableMessage() {
        String message = Duke.TAB + Duke.HOR_BAR + "\n" +
                Duke.TAB + "Aww... ''૮₍  ˶•⤙•˶ ₎ა  I haven't learnt enough to do this.\n" +
                Duke.TAB + "Try these commands instead:\n" +

                Duke.TAB + "ADDING TASKS\n" +
                Duke.TAB + Duke.TAB + "todo yourTaskName " +
                                    "- Adds a todo task into your task list.\n"+
                Duke.TAB + Duke.TAB + "deadline yourTaskName /by yourDeadline " +
                                    "- Adds a deadline task into your task list with the given deadline.\n"+
                Duke.TAB + Duke.TAB + "event yourTaskName /from eventStartTime /to eventEndTime " +
                                    "- Adds an event into your task list with given start and end time.\n"+

                Duke.TAB + "MARKING TASKS\n" +
                Duke.TAB + Duke.TAB + "mark taskNumber " +
                "- Marks the task given my task number from the list as done.\n"+
                Duke.TAB + Duke.TAB + "unmark taskNumber " +
                "- Marks the task given my task number from the list as not done.\n"+

                Duke.TAB + "LISTING TASKS\n" +
                Duke.TAB + Duke.TAB + "list - lists out all your tasks\n"+
                Duke.TAB + Duke.HOR_BAR + "\n";
        return message;
    }

    public static void main(String[] args) throws IOException {
        /* Initialise BufferedReader */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /* Initialise TaskBook class */
        TaskBook tb = new TaskBook();
        /* Initialise endFlag */
        boolean endFlag = false;

        greetingDialogue();

        try {
            while (!endFlag) {
                try {
                    /* While user has not exited application */
                    String input = br.readLine();
                    String[] splitInput = input.split(" ");
                    String command = splitInput[0];
                    int index;

                    /* Handle commands */
                    switch (command) {
                        case "bye":
                            byeDialogue();
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
                                throw new DukeException(outOfRangeMessage());
                            }
                            break;
                        case "unmark":
                            index = Integer.parseInt(splitInput[1]);
                            if (tb.indexWithinRange(index)) {
                                tb.markNotDone(index);
                            } else {
                                throw new DukeException(outOfRangeMessage());
                            }
                            break;
                        default: // types of tasks
                            int taskType = 0;
                            String description = " ";

                            if (command.equals("todo")) {
                                taskType = 1; // todo
                            } else if (command.equals("deadline")) {
                                taskType = 2; // deadline
                            } else if (command.equals("event")) {
                                taskType = 3; // event
                            } else {
                                throw new DukeException(unreadableMessage());
                            }

                            description = cutDescription(taskType, input);
                            tb.addTask(taskType, description);
                            break;
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
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
    public void addTask(int taskType, String description) throws DukeException {
        numOfTasks++;
        Task t;

        try {
            if (taskType == 1)  //todo
                t = new Todo(description);
            else if (taskType == 2) // deadline
                t = new Deadline(description.split("/")[0],
                        description.split("/by")[1]);
            else if (taskType == 3)// event
                t = new Event(description.split("/")[0],
                        description.split("/from")[1].split("/to")[0],
                        description.split("/to")[1]);
            else
                t = new Task(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Duke.unreadableMessage());
        }

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

/**
 * This is a general to-do task.
 */
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * This is a task enclosing a deadline.
 */
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

/**
 * This is a task enclosing an event with a from and end time or date.
 */
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