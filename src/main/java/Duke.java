import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a chatbot that one can interact with to keep track of tasks.
 */
public class Duke {
    /** Logo for the name of the chatbot. **/
    private static final String logo = " |          ______    ______\n"
                                     + " | ____    |      |  |      |\n"
                                     + " |      |  |      |  |      |\n"
                                     + " | ____ |  |______|  |______|\n";

    /** Straight line that separates commands. **/
    private static final String straightLine =
            "_______________________________________________________________________________________________";

    /** Commands that supported by the chatbot. */
    private static final String commandList =
            "1. list -> Provides a list of existing tasks.\n"
                    + "2. mark X -> Marks task number X as done.\n"
                    + "3. unmark X -> Marks task number X as undone.\n"
                    + "4. todo taskName -> Creates a todo task with name taskName.\n"
                    + "5. deadline taskName /by date -> Creates a deadline task with name taskName and deadline date.\n"
                    + "6. event taskName /from startDate /to endDate -> Creates an event task with name taskName,\n"
                    + "   start date startDate, and end date endDate.\n"
                    + "7. delete X -> Deletes task number X from the list.\n"
                    + "8. help -> Gets the list of commands supported by this bot.\n\n"
                    + "Please enter dates in the format of either yyyy-MM-dd hh:mm or yyyy-MM-dd.";

    /**
     * Launches the chatbot.
     *
     * @param args The command line arguments that one can type.
     */
    public static void main(String[] args) {
        //Stores user input
        ArrayList<Task> taskStorage = new ArrayList<Task>();

        printIntroductoryMessage();

        //Prepare input source
        Scanner sc = new Scanner(System.in);

        //Keep taking in user input line by line
        while (true) {
            String input = sc.nextLine();

            //Determine if user types in a single word or multiple words
            String[] inputArray = input.split(" ");


            //User typed in "bye".
            if (input.equals("bye")) {
                break;
            }
            //User typed in "list"
            else if (input.equals("list")) {
                printUserTasks(taskStorage);
                continue;
            }
            //User typed in "help"
            else if (input.equals("help")) {
                System.out.println(straightLine);
                System.out.println("Supported Commands:");
                System.out.println(commandList);
                System.out.println(straightLine);
                continue;
            }

            String firstWord = inputArray[0];
            switch (firstWord) {
            case "mark":
                try {
                    if (inputArray.length != 2) {
                        throw new DukeException("The mark command must be followed by a single number.");
                    }
                    if (! isInteger(inputArray[1])) {
                        throw new DukeException("The mark command must be followed by a single integer.");
                    }
                    int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                    if (! (indexOfTask <= taskStorage.size() - 1 && indexOfTask >= 0)) {
                        throw new DukeException("Please enter a valid task number. You currently have " +
                                Integer.toString(taskStorage.size()) + " tasks.");
                    }
                    markAsDone(taskStorage.get(indexOfTask));
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(straightLine);
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    break;
                }
            case "unmark":
                try {
                    if (inputArray.length != 2) {
                        throw new DukeException("The unmark command must be followed by a single number.");
                    }
                    if (! isInteger(inputArray[1])) {
                        throw new DukeException("The unmark command must be followed by a single integer.");
                    }
                    int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                    if (! (indexOfTask <= taskStorage.size() - 1 && indexOfTask >= 0)) {
                        throw new DukeException("Please enter a valid task number. You currently have " +
                                Integer.toString(taskStorage.size()) + " tasks.");
                    }
                    markAsUndone(taskStorage.get(indexOfTask));
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(straightLine);
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    break;
                }
            case "delete":
                try {
                    if (inputArray.length != 2) {
                        throw new DukeException("The delete command must be followed by a single number.");
                    }
                    if (! isInteger(inputArray[1])) {
                        throw new DukeException("The delete command must be followed by a single integer.");
                    }
                    int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                    if (! (indexOfTask <= taskStorage.size() - 1 && indexOfTask >= 0)) {
                        throw new DukeException("Please enter a valid task number. You currently have "
                                + Integer.toString(taskStorage.size()) + " tasks.");
                    }
                    deleteTask(indexOfTask, taskStorage);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(straightLine);
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    break;
                }
            case "todo":
                try {
                    if (inputArray.length == 1) {
                        throw new DukeException("The todo command cannot be left blank.");
                    }
                    int indexOfType = input.indexOf("todo");
                    String taskName = input.substring(indexOfType + 5);
                    ToDo newToDoTask = new ToDo(taskName);
                    addTask(newToDoTask, taskStorage);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(straightLine);
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    break;
                }
            case "deadline":
                try {
                    int indexOfType = input.indexOf("deadline");
                    if (indexOfType + 8 > input.length() - 1) {
                        throw new DukeException("The deadline command cannot be left blank.");
                    }
                    int indexOfBy = input.indexOf("/by");
                    if (indexOfBy == -1) {
                        throw new DukeException("The deadline cannot be left blank.");
                    }
                    //deadline/by
                    if (indexOfType + 8 == indexOfBy) {
                        throw new DukeException("There seems to be a missing task name.");
                    }
                    if (indexOfBy + 4 > input.length() - 1) {
                        throw new DukeException("The deadline cannot be left blank.");
                    }
                    if (indexOfType + 9 > indexOfBy - 1) {
                        throw new DukeException("There seems to be a missing task name.");
                    }
                    String taskName = input.substring(indexOfType + 9, indexOfBy - 1);
                    String deadlineOfTask;
                    if (input.charAt(indexOfBy + 3) == ' ') {
                        deadlineOfTask = input.substring(indexOfBy + 4);
                    }
                    else {
                        deadlineOfTask = input.substring(indexOfBy + 3);
                    }
                    if (taskName.isBlank()) {
                        throw new DukeException("The task name cannot be left blank.");
                    }
                    if (deadlineOfTask.isBlank()) {
                        throw new DukeException("The deadline cannot be left blank.");
                    }
                    //Checks if the deadline is a valid date and time
                    Deadline newDeadlineTask = new Deadline(taskName, getDateObject(deadlineOfTask));
                    addTask(newDeadlineTask, taskStorage);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(straightLine);
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    break;
                } catch (DateTimeParseException dateTimeException) {
                    System.out.println(straightLine);
                    System.out.println("Please check that you entered a valid date, and that the date should be in "
                            + "the format of yyyy-MM-dd hh:mm or yyyy-MM-dd");
                    System.out.println(straightLine);
                    break;
                }
            case "event":
                try {
                    int indexOfType = input.indexOf("event");
                    if (indexOfType + 5 > input.length() - 1) {
                        throw new DukeException("The event command cannot be left blank.");
                    }

                    int indexOfFrom = input.indexOf("/from");
                    if (indexOfFrom == -1) {
                        throw new DukeException("There seems to be a missing from date.");
                    }

                    int indexOfTo = input.indexOf("/to");
                    if (indexOfTo == -1) {
                        throw new DukeException("There seems to be a missing to date.");
                    }

                    //Check taskName
                    if ((indexOfType + 6 > indexOfFrom - 1)) {
                        throw new DukeException("There seems to be a missing task name.");
                    }

                    String taskName = input.substring(indexOfType + 6, indexOfFrom - 1);
                    if (taskName.isBlank()) {
                        throw new DukeException("The task name cannot be left blank.");
                    }

                    //Check startDate
                    if (indexOfFrom + 6 > indexOfTo - 1) {
                        throw new DukeException("There seems to be a missing start date.");
                    }

                    String startDate = input.substring(indexOfFrom + 6, indexOfTo - 1);
                    if (startDate.isBlank()) {
                        throw new DukeException("The start date cannot be left blank.");
                    }

                    //Check endDate
                    if (indexOfTo + 4 > input.length() - 1) {
                        throw new DukeException("There seems to be a missing end date.");
                    }

                    String endDate = input.substring(indexOfTo + 4);
                    if (endDate.isBlank()) {
                        throw new DukeException("The end date cannot be left blank.");
                    }
                    //Create new event task
                    Event newEventTask = new Event(taskName, getDateObject(startDate), getDateObject(endDate));
                    addTask(newEventTask, taskStorage);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(straightLine);
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    break;
                } catch (DateTimeParseException dateTimeException) {
                    System.out.println(straightLine);
                    System.out.println("Please check that you entered a valid date, and that the date should be in "
                            + "the format of yyyy-MM-dd hh:mm or yyyy-MM-dd");
                    System.out.println(straightLine);
                    break;
                }
            default:
                printInvalidMessage();
            }
        }

        //Exits the bot with an exit message
        printExitMessage();
    }


    /**
     * Prints the introductory message.
     */
    public static void printIntroductoryMessage() {
        System.out.println(logo);
        System.out.println(straightLine);
        System.out.println("Boo! Nice to meet you.");
        System.out.println("I am here to scare all your problems away by keeping track of your tasks.");
        System.out.println("What can I help you with today?\n");
        System.out.println("Supported Commands:");
        System.out.println(commandList);
        System.out.println(straightLine);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println(straightLine);
        System.out.println("Goodbye. Hope that I have managed to scare all your problems away.");
        System.out.println("Have a great day! :)");
        System.out.println(straightLine);
    }

    /**
     * Prints out all the user tasks that have been entered by the user thus far.
     *
     * @param taskStorage The ArrayList that stores the user tasks to be printed out.
     */
    public static void printUserTasks(ArrayList<Task> taskStorage) {
        System.out.println(straightLine);
        if (taskStorage.size() == 0) {
            System.out.println("There are currently no tasks in your list.");
            System.out.println(straightLine);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int numberOfTasks= taskStorage.size();
        //Process each task in the storage
        for (int i = 0; i < numberOfTasks; i = i + 1) {
            String numbering = Integer.toString(i + 1) + ". ";
            String output = numbering + taskStorage.get(i).getStatusOfTaskInString();
            System.out.println(output);
        }
        System.out.println(straightLine);
    }

    /**
     * Adds user task into storage and informs the user.
     *
     * @param taskToAdd The task to be added to storage.
     * @param taskStorage The ArrayList that stores the tasks.
     */
    public static void addTask(Task taskToAdd, ArrayList<Task> taskStorage) {
        taskStorage.add(taskToAdd);
        System.out.println(straightLine);
        System.out.println("Added task to list:");
        System.out.println(taskToAdd.getStatusOfTaskInString());
        if (taskStorage.size() == 1) {
            System.out.println("Currently, there is 1 task in your list.");
        } else {
            System.out.println("Currently, there are " + Integer.toString(taskStorage.size())
                    + " tasks in your list.");
        }
        System.out.println(straightLine);
    }

    /**
     * Marks a task as done and informs the user.
     *
     * @param currentTask The task to be marked as done.
     */
   public static void markAsDone(Task currentTask) {
       currentTask.setDoneStatus();
       System.out.println(straightLine);
       System.out.println("Poof! One less worry. The following task is now marked as done:");
       System.out.println(currentTask.getStatusOfTaskInString());
       System.out.println(straightLine);
   }

    /**
     * Marks a task as undone and informs the user.
     *
     * @param currentTask The task to be marked as undone.
     */
    public static void markAsUndone(Task currentTask) {
        currentTask.setUndoneStatus();
        System.out.println(straightLine);
        System.out.println("Alright! The following task is now marked as undone. I will help you keep an eye on it.");
        System.out.println(currentTask.getStatusOfTaskInString());
        System.out.println(straightLine);
    }

    /**
     * Prints a message indicating to the user that the command is invalid.
     */
    public static void printInvalidMessage() {
        System.out.println(straightLine);
        System.out.println("Sorry. I do not understand this command. Please try again.");
        System.out.println(straightLine);
    }

    /**
     * Deletes a task from the given list of task, and informs the user.
     *
     * @param indexOfTask Index of the task in the list that is to be deleted.
     * @param taskStorage List containing all the tasks.
     */
    public static void deleteTask(int indexOfTask, ArrayList<Task> taskStorage) {
        Task taskToBeDeleted = taskStorage.get(indexOfTask);
        taskStorage.remove(indexOfTask);
        System.out.println(straightLine);
        System.out.println("Ta-da! The following task has been deleted.");
        System.out.println(taskToBeDeleted.getStatusOfTaskInString());
        System.out.println("Current number of tasks is: " + Integer.toString(taskStorage.size()) + ".");
        System.out.println(straightLine);
    }


    /**
     * Checks if a string can be converted into an Integer.
     *
     * @param stringToCheck String to check whether the conversion is possible.
     * @return true if it can be converted, else return false.
     */
    public static boolean isInteger(String stringToCheck) {
        try {
            int intVersion = Integer.parseInt(stringToCheck);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    /**
     * Returns a Temporal that encapsulates date and or time information.
     *
     * @param rawDateString The raw String that contains date and or time information.
     * @return the Temporal with the date and or time information.
     * @throws DateTimeParseException if the raw String is not of the correct date format
     *                                as requested in the command list of the bot.
     */
    public static Temporal getDateObject(String rawDateString)
            throws DateTimeParseException {
        //Possible formats, with and without time
        String timePatternOne = "yyyy-MM-dd HH:mm";
        String timePatternTwo = "yyyy-MM-dd";
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(timePatternOne);
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern(timePatternTwo);

        //Determine which format
        boolean hasTime = (rawDateString.length() > timePatternTwo.length());
        DateTimeFormatter formatterToUse = (hasTime)
                ? formatterWithTime
                : formatterWithoutTime;
        if (hasTime) {
            //A date with time
            return LocalDateTime.parse(rawDateString, formatterToUse);
        } else {
            //A date without time
            return LocalDate.parse(rawDateString, formatterToUse);
        }
    }
}


