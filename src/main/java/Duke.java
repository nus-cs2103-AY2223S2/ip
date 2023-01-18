import java.io.*;
import java.util.*;

/**
 * This class represents a chatbot that one can interact with to keep track of tasks.
 *
 * @version CS2103T AY22/23 Sem 2 Individual Project
 * @author A0233828Y Eugene Tang
 */
public class Duke {
    private static final String logo = " |          ______    ______\n"
                                     + " | ____    |      |  |      |\n"
                                     + " |      |  |      |  |      |\n"
                                     + " | ____ |  |______|  |______|\n";
    private static final String straightLine = "_______________________________________________________________________________________________";


    public static void main(String[] args) {
        //Stores user input
        ArrayList<Task> taskStorage = new ArrayList<Task>();

        printIntroductoryMessage();

        //Prepare input and output sources
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //Keep taking in user input line by line
        while (true) {
            String input;
            try {
                input = br.readLine();
            }
            catch (IOException ie) {
                pw.println("Error encountered in receiving input.");
                pw.println("The error message is: ");
                pw.flush();
                ie.printStackTrace();
                break;
            }

            //Determine if user types in a single word or multiple words
            String[] inputArray = input.split(" ");


            //User typed in "bye".
            if (input.equals("bye")) {
                break;
            }
            //User typed in "list"
            else if (input.equals("list")) {
                printUserTasks(taskStorage);
            }
            //User typed in "mark":
            else if (inputArray[0].equals("mark")) {
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
                } catch (DukeException dukeException) {
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    continue;
                }
            }
            //User typed in "unmark":
            else if (inputArray[0].equals("unmark")) {
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
                } catch (DukeException dukeException) {
                    System.out.println(dukeException.getMessage());
                    System.out.println(straightLine);
                    continue;
                }
            }
            //User typed in "to-do"
            else if (inputArray[0].equals("todo")) {
                int indexOfType = input.indexOf("todo");
                String taskName = input.substring(indexOfType + 5);
                ToDo newToDoTask = new ToDo(taskName);
                addTask(newToDoTask, taskStorage);
            }
            //User typed in "deadline"
            else if (inputArray[0].equals("deadline")) {
                int indexOfType = input.indexOf("deadline");
                int indexOfBy = input.indexOf("/by");
                String taskName = input.substring(indexOfType + 9, indexOfBy - 1);
                String deadlineOfTask = input.substring(indexOfBy + 4);
                Deadline newDeadlineTask = new Deadline(taskName, deadlineOfTask);
                addTask(newDeadlineTask, taskStorage);
            }
            //User typed in "event"
            else if (inputArray[0].equals("event")) {
                int indexOfType = input.indexOf("event");
                int indexOfFrom = input.indexOf("/from");
                int indexOfTo = input.indexOf("/to");
                String taskName = input.substring(indexOfType + 6, indexOfFrom - 1);
                String startDate = input.substring(indexOfFrom + 6, indexOfTo - 1);
                String endDate = input.substring(indexOfTo + 4);
                Event newEventTask = new Event(taskName, startDate, endDate);
                addTask(newEventTask, taskStorage);
            }
            //User did not type in a valid command
            else {
                printInvalidMessage();
            }
        }

        //Exits the bot with an exit message
        printExitMessage();
        pw.close();
    }


    /**
     * Prints the introductory message.
     */
    public static void printIntroductoryMessage() {
        System.out.println(logo);
        System.out.println(straightLine);
        System.out.println("Boo! Nice to meet you.");
        System.out.println("I am here to scare all your problems away by keeping track of your tasks.");
        System.out.println("What can I help you with today?");
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
        }
        else {
            System.out.println("Currently, there are " + Integer.toString(taskStorage.size()) + " tasks in your list.");
        }
        System.out.println(straightLine);
    }

    /**
     * Marks a task as done and informs the user.
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
     * Checks if a string can be converted into an Integer.
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
}


