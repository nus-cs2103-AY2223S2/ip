import java.util.Arrays;
import java.util.Scanner;

/**
 * Project Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying as many
 * Java and SE techniques as possible along the way.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Duke {

    /**
     * The list to store whatever tasks entered by the user.
     */
    private static Task[] taskList = new Task[100];

    /**
     * A pointer to keep track of taskList.
     */
    private static int listPointer = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        //Keeps taking user input until 'bye' command entered
        takeCommand();

        bidFarewell();
    }

    /**
     * Waits for user input and calls makeResponse() if the input is not 'bye'. Else, exits
     * the program.
     *
     */
    public static void takeCommand() {

        Scanner inputTaker = new Scanner(System.in);

        while (inputTaker.hasNextLine()) {
            String userInput = inputTaker.nextLine();
            makeSeperation();

            if (userInput.equals("bye")) {
                return;
            }

            makeResponse(userInput);
        }

    }

    /**
     * Handles the logic of user input, command. If command != 'list', add
     * task to dedicated array. Else, print out everything in the array.
     *
     * @param command The user input received.
     */
    public static void makeResponse(String command) {
        String processedCommand = checkForCommand(command);

        //Handle "list" command
        if (processedCommand.equals("list")) {
            for (int i = 0; i < listPointer; i++) {
                String index = "\t" + Integer.toString(i+1) + ". ";
                System.out.println(index + taskList[i]);
            }
            makeSeperation();
        }

        //Handle "mark" command
        else if (processedCommand.equals("mark")) {
            int index = getMarkIndex(command) - 1;
            Task curTask = taskList[index];
            curTask.markAsDone();

            System.out.println("\tNice! I've marked this task as done:\n\t  "
                    + curTask.toString());
            makeSeperation();
        }

        //Handle "unmark" command
        else if (processedCommand.equals("unmark")) {
            int index = getMarkIndex(command) - 1;
            Task curTask = taskList[index];
            curTask.markAsNotDone();

            System.out.println("\tOK, I've marked this task as not done yet:\n\t  "
                    + curTask.toString());
            makeSeperation();
        }

        //Handle normal task
        else {
            Task current_task = createTask(command);

            //TODO better error handling
            if (current_task == null) {return;}

            taskList[listPointer] = current_task;
            listPointer += 1;

            System.out.println("\tGot it. I've added this task:\n\t  " + current_task
            + "\n\t" + String.format("Now you have %d tasks in the list.", listPointer));
            makeSeperation();
        }

    }

    /**
     * Takes in a String command from the user and creates the corresponding task.
     *
     * @param command A string input of a task with relevant information.
     *
     * @return The Task object based on command.
     */
    public static Task createTask(String command) {
        String[] splitted = command.split(" ");

        if (splitted[0].equals("todo")) {
            //Get 'ToDo' description
            String[] descriptionArray = Arrays.copyOfRange(splitted, 1, splitted.length);
            String description = String.join(" ", descriptionArray);

            //Make Todo
            ToDo curToDO = new ToDo(description);
            return curToDO;
        }

        else if (splitted[0].equals("deadline")) {
            //Get 'Deadline' description and 'by' index
            int byStartIndex = -1;

            for (int i = 0; i < splitted.length; i++) {
                String curString = splitted[i];

                if (curString.equals("/by")) {
                    byStartIndex = i;
                }
            }
            //TODO: handle invalid byStartIndex (-1)

            //Make description and by string
            String[] descriptionArray = Arrays.copyOfRange(splitted, 1, byStartIndex);
            String[] byArray = Arrays.copyOfRange(splitted, byStartIndex + 1,
                    splitted.length);
            String description = String.join(" ", descriptionArray);
            String by = String.join(" ", byArray);

            //Make Deadline
            Deadline deadline = new Deadline(description, by);
            return deadline;
        }

        else if (splitted[0].equals("event")) {
            //Get 'Deadline' description, 'from' and 'to' index
            int fromStartIndex = -1;
            int toStartIndex = -1;

            for (int i = 0; i < splitted.length; i++) {
                String curString = splitted[i];

                if (curString.equals("/from")) {
                    fromStartIndex = i;
                }

                else if (curString.equals("/to")) {
                    toStartIndex = i;
                }
            }
            //TODO: handle invalid from/toStartIndex (-1)

            //Make description and by string
            String[] descriptionArray = Arrays.copyOfRange(splitted, 1, fromStartIndex);
            String[] fromArray = Arrays.copyOfRange(splitted, fromStartIndex + 1,
                    toStartIndex);
            String[] toArray = Arrays.copyOfRange(splitted, toStartIndex + 1,
                    splitted.length);
            String description = String.join(" ", descriptionArray);
            String from = String.join(" ", fromArray);
            String to = String.join(" ", toArray);

            //Make Event
            Event event = new Event(description, from, to);
            return event;
        }

        else {
            //TODO: better error handling
            System.out.println("Error: unknown Task type");
            return null;
        }
    }

    /**
     * Checks whether user input is a task or a special command.
     *
     * @param command User input to be checked.
     *
     * @return command in String representation if input was a valid command, "none" if task.
     */
    public static String checkForCommand(String command) {
        String[] splitted = command.split(" ");
        if (splitted[0].equals("list")) {
            return "list";
        } else if (splitted[0].equals("mark")) {
            return "mark";
        } else if (splitted[0].equals("unmark")) {
            return "unmark";
        } else {
            return "task";
        }
    }

    /**
     * Returns the index of task in task list to be marked or unmarked.
     *
     * @param command The 'mark' or 'unmark' command with an index.
     *
     * @return int, the index of the 'mark' or 'unmark' command.
     */
    public static int getMarkIndex(String command) {
        String[] splitted = command.split(" ");
        return Integer.parseInt(splitted[1]);
    }

    /**
     * Prints out the greeting message and a line separation.
     */
    public static void greet() {
        System.out.println("\tHello! I'm Duke\n" +
                "\tWhat can I do for you?");
        makeSeperation();
    }

    /**
     * Prints out the goodbye message and a line separation.
     */
    public static void bidFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
        makeSeperation();
    }

    /**
     * Prints out a line separation.
     */
    public static void makeSeperation() {
        System.out.println("\t____________________________________________________________");
    }
}
