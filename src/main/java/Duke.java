import java.util.*;
import java.io.*;

/**
 * This exception - DukeException handles exceptions specific to Duke
 */
class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

/**
 * This class handles the main chatbot line interface
 */
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
        System.out.println(TAB + "Bye! I hope Berry was helpful to you ૮꒰˶• ༝ •˶꒱ა");
        System.out.println(TAB + HOR_BAR);
    }

    /**
     * This function cuts out the task name and returns it.
     * @param taskType The type of command includes 'todo', 'deadline', and 'event'.
     * @param input The input given by the user.
     * @return The task name.
     * @throws DukeException when the input command has not been added to be handled by the chatbot.
     */
    public static String cutDescription(String taskType, String input) throws DukeException {
        String description;
        try {
            if (taskType.equals("todo")) {
                description = input.substring(5);
            } else if (taskType.equals("deadline")) {
                description = input.substring(9);
            } else
                description = input.substring(6);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(missingInfosMessage(taskType));
        }
        return description;
    }

    /**
     * This function prints a out of range message.
     * @return A message string to indicate out of range task numbers.
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
     * This function prints a general message for unreadable instructions.
     * @return A message string to indicate commands which cannot be handled.
     */
    public static String unreadableMessage() {
        String message = Duke.TAB + Duke.HOR_BAR + "\n" +
                Duke.TAB + "Aww... ''૮₍  ˶•⤙•˶ ₎ა  I can't help you with this.\n" +
                Duke.TAB + "key in 'help' for the help menu~\n" +
                Duke.TAB + Duke.HOR_BAR;
        return message;
    }

    /**
     * This function prints a message for missing information in creating known tasks.
     * @param taskType The type of command includes 'todo', 'deadline', and 'event'.
     * @return A message string to indicate missing informations in tasks.
     */
    public static String missingInfosMessage(String taskType) {
        String message =
                Duke.TAB + Duke.HOR_BAR + "\n" +
                        Duke.TAB + "There seems to be missing information(s) in your " + taskType + " statement.\n" +
                        Duke.TAB + "If you need info, you can ask me to 'help' !\n"+
                        Duke.TAB + Duke.HOR_BAR;
        return message;
    }

    /**
     * This function prints a message for specific blank inputs in command.
     * @param missingInfo A string to indicate the blank clause.
     * @return A message string to indicate blank information in tasks.
     */
    public static String blankInfoMessage(String missingInfo) {
        String message =
                Duke.TAB + Duke.HOR_BAR + "\n" +
                        Duke.TAB + "Your " + missingInfo + " cannot be left empty!\n" +
                        Duke.TAB + "If you need info, you can ask me to 'help' !\n"+
                        Duke.TAB + Duke.HOR_BAR;
        return message;
    }

    /**
     * This function prints out a help menu for known instructions for the chatbot to execute.
     * @return A help menu.
     */
    public static String helpMenu() {
        String message = Duke.TAB + Duke.HOR_BAR + "\n" +
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
                Duke.TAB + Duke.TAB + "list - lists out all your tasks\n" +
                Duke.TAB + Duke.HOR_BAR;
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
                        case "help":
                            System.out.println(helpMenu());
                            break;
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
                        case "delete":
                            index = Integer.parseInt(splitInput[1]);
                            if (tb.indexWithinRange(index)) {
                                tb.deleteTask(index);
                            } else {
                                throw new DukeException(outOfRangeMessage());
                            }
                            break;
                        default: // types of tasks
                            String description = " ";

                            if (! (command.equals("todo")
                                    || command.equals("deadline")
                                    || command.equals("event"))) {
                                throw new DukeException(unreadableMessage());
                            }

                            description = cutDescription(command, input);
                            tb.addTask(command, description);
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

/**
 * This class manages the Task(s) in a TaskBook recording the number of tasks
 * and task names.
 */
class TaskBook {
    private static int numOfTasks;
    public static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    /**
     * This function lists out all the tasks in listOfTasks by order of
     * its addition into listOfTask.
     */
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

    /**
     * This function adds a new task into listOfTasks, and updates the number of tasks in the TaskBook.
     *
     * @param taskType The type of command includes 'todo', 'deadline', and 'event'.
     * @param description The input given by the user to describe the taskType.
     * @throws DukeException when there are missing information, thus unable to create a new task.
     */
    public void addTask(String taskType, String description) throws DukeException {
        Task t = new Task(description);

        try {
            if (taskType.equals("todo")) {
                if (description.isBlank()) throw new DukeException("task name");

                t = new Todo(description);

            } else if (taskType.equals("deadline")) {
                String desc = description.split("/by")[0];
                if (desc.isBlank()) throw new DukeException("task name");
                String by = description.split("/by")[1];
                if (by.isBlank()) throw new DukeException("'by' clause");

                t = new Deadline(description.split("/")[0], by);

            } else if (taskType.equals("event")) {// event
                String desc = description.split("/from")[0];
                if (desc.isBlank()) throw new DukeException("task name");
                String from = description.split("/from")[1].split("/to")[0];
                if (from.isBlank()) throw new DukeException("'from' clause");
                String to = description.split("/to")[1];
                if (to.isBlank()) throw new DukeException("'to' clause");

                t = new Event(description.split("/")[0], from, to);
            }
        } catch (DukeException e) {
            throw new DukeException(Duke.blankInfoMessage(e.getMessage()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Duke.missingInfosMessage(taskType));
        }

        listOfTasks.add(t);

        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Done and ready to go! I've added this task for ya:");
        System.out.println(Duke.TAB + Duke.TAB + t.toString());
        numOfTasks++;
        printNumberOfTasks();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /**
     * This function deletes an existing task in listOfTasks, and updates the number of tasks in the TaskBook.
     * @param index The index of the task in listOfTasks, starting from index 1.
     */
    public void deleteTask(int index){
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Here you go! I've deleted this task for ya:");
        System.out.println(Duke.TAB + Duke.TAB + listOfTasks.get(index - 1).toString());
        listOfTasks.remove(index - 1);
        numOfTasks--;
        printNumberOfTasks();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }


    /**
     * This function marks the given task at index as done.
     * @param index The index of the task in listOfTasks, starting from index 1.
     */
    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR);
        System.out.println(Duke.TAB + "Alright~ I'll set the task as done! ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა");
        t.markAsDone();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /**
     * This function marks the given task at index as not done.
     * @param index The index of the task in listOfTasks, starting from index 1.
     */
    public void markNotDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(Duke.TAB + Duke.HOR_BAR );
        System.out.println(Duke.TAB + "Okay! I'll set the task as not done. ૮₍ ˃ ⤙ ˂ ₎ა");
        t.markAsNotDone();
        System.out.println(Duke.TAB + Duke.HOR_BAR);
    }

    /**
     * This function checks against the number of tasks in the task book and checks if it is out of range.
     * @param index The index of the task in listOfTasks, starting from index 1.
     * @return true if index is in range
     */
    public boolean indexWithinRange(int index) {
        return index > 0 && index <= numOfTasks;
    }

    /**
     * This function prints out the number of tasks in the task book currently.
     */
    public void printNumberOfTasks() {
        System.out.println(Duke.TAB + "You now have " + numOfTasks + " tasks in the list ૮꒰ˊᗜˋ* ꒱ა");
    }
}

/**
 * This is a general task class which describes a task by its name.
 */
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