import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Paths;

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
    private static final String STRAIGHT_LINE =
            "_______________________________________________________________________________________________";

    /** File path where the data file should be stored in. **/
    private static final String dataFilePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt")
            .toString();

    /**
     * Launches the chatbot.
     *
     * @param args The command line arguments that one can type.
     */
    public static void main(String[] args) {
        //Prints introduction
        printIntroductoryMessage();

        //Checks if user has a data file that stores task history
        if (isFirstRun()) {
            //User's first time running the bot. Create relevant folder and file.
            try {
                createFile();
            } catch (IOException e) {
                System.out.println("Could not create data file to store task history. The following error occurred:");
                System.out.println(e.getMessage());
                return;
            }
        }

        //Restores task history if present
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            restoreTaskHistory(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Data file is missing. The following error occurred: ");
            System.out.println(e.getMessage());
        }



        //Prepare input source
        Scanner sc = new Scanner(System.in);

        //Keep taking in user input line by line
        while (true) {
            //Read in user command
            String input = sc.nextLine();

            //Determine if user types in a single word or multiple words
            String[] inputArray = input.split(" ");


            //User typed in "bye".
            if (input.equals("bye")) {
                break;
            }
            //User typed in "list"
            else if (input.equals("list")) {
                printUserTasks(tasks);
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
                    if (! (indexOfTask <= tasks.size() - 1 && indexOfTask >= 0)) {
                        throw new DukeException("Please enter a valid task number. You currently have " +
                                Integer.toString(tasks.size()) + " tasks.");
                    }
                    markAsDone(tasks.get(indexOfTask));
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
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
                    if (! (indexOfTask <= tasks.size() - 1 && indexOfTask >= 0)) {
                        throw new DukeException("Please enter a valid task number. You currently have " +
                                Integer.toString(tasks.size()) + " tasks.");
                    }
                    markAsUndone(tasks.get(indexOfTask));
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
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
                    if (! (indexOfTask <= tasks.size() - 1 && indexOfTask >= 0)) {
                        throw new DukeException("Please enter a valid task number. You currently have "
                                + Integer.toString(tasks.size()) + " tasks.");
                    }
                    deleteTask(indexOfTask, tasks);
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
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
                    addTask(newToDoTask, tasks);
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
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
                    Deadline newDeadlineTask = new Deadline(taskName, deadlineOfTask);
                    addTask(newDeadlineTask, tasks);
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
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
                    Event newEventTask = new Event(taskName, startDate, endDate);
                    addTask(newEventTask, tasks);
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
                    break;
                }
            default:
                printInvalidMessage();
            }
        }


        //Exits the bot after printing exit message
        printExitMessage();
        sc.close();
    }


    /**
     * Prints the introductory message.
     */
    public static void printIntroductoryMessage() {
        System.out.println(logo);
        System.out.println(STRAIGHT_LINE);
        System.out.println("Boo! Nice to meet you.");
        System.out.println("I am here to scare all your problems away by keeping track of your tasks.");
        System.out.println("What can I help you with today?\n");
        System.out.println("Supported Commands:");
        String commandList =
                "1. list -> Provides a list of existing tasks.\n"
                + "2. mark X -> Marks task number X as done.\n"
                + "3. unmark X -> Marks task number X as undone.\n"
                + "4. todo taskName -> Creates a todo task with name taskName.\n"
                + "5. deadline taskName /by date -> Creates a deadline task with name taskName and deadline date.\n"
                + "6. event taskName /from startDate /to endDate -> Creates an event task with name taskName,\n"
                + "   start date startDate, and end date endDate.\n"
                + "7. delete X -> Deletes task number X from the list.";
        System.out.println(commandList);



        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("Goodbye. Hope that I have managed to scare all your problems away.");
        System.out.println("Have a great day! :)");
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Prints out all the user tasks that have been entered by the user thus far.
     *
     * @param tasks The ArrayList that stores the user tasks to be printed out.
     */
    public static void printUserTasks(ArrayList<Task> tasks) {
        System.out.println(STRAIGHT_LINE);
        if (tasks.size() == 0) {
            System.out.println("There are currently no tasks in your list.");
            System.out.println(STRAIGHT_LINE);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int numberOfTasks= tasks.size();
        //Process each task in the storage
        for (int i = 0; i < numberOfTasks; i = i + 1) {
            String numbering = Integer.toString(i + 1) + ". ";
            String output = numbering + tasks.get(i).getStatusOfTaskInString();
            System.out.println(output);
        }
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Adds user task into storage and informs the user.
     *
     * @param taskToAdd The task to be added to storage.
     * @param tasks The ArrayList that stores the tasks.
     */
    public static void addTask(Task taskToAdd, ArrayList<Task> tasks) {
        tasks.add(taskToAdd);
        System.out.println(STRAIGHT_LINE);
        System.out.println("Added task to list:");
        System.out.println(taskToAdd.getStatusOfTaskInString());
        if (tasks.size() == 1) {
            System.out.println("Currently, there is 1 task in your list.");
        } else {
            System.out.println("Currently, there are " + Integer.toString(tasks.size())
                    + " tasks in your list.");
        }
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Marks a task as done and informs the user.
     *
     * @param currentTask The task to be marked as done.
     */
   public static void markAsDone(Task currentTask) {
       currentTask.setDoneStatus();
       System.out.println(STRAIGHT_LINE);
       System.out.println("Poof! One less worry. The following task is now marked as done:");
       System.out.println(currentTask.getStatusOfTaskInString());
       System.out.println(STRAIGHT_LINE);
   }

    /**
     * Marks a task as undone and informs the user.
     *
     * @param currentTask The task to be marked as undone.
     */
    public static void markAsUndone(Task currentTask) {
        currentTask.setUndoneStatus();
        System.out.println(STRAIGHT_LINE);
        System.out.println("Alright! The following task is now marked as undone. I will help you keep an eye on it.");
        System.out.println(currentTask.getStatusOfTaskInString());
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message indicating to the user that the command is invalid.
     */
    public static void printInvalidMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("Sorry. I do not understand this command. Please try again.");
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Deletes a task from the given list of task, and informs the user.
     *
     * @param indexOfTask Index of the task in the list that is to be deleted.
     * @param tasks List containing all the tasks.
     */
    public static void deleteTask(int indexOfTask, ArrayList<Task> tasks) {
        Task taskToBeDeleted = tasks.get(indexOfTask);
        tasks.remove(indexOfTask);
        System.out.println(STRAIGHT_LINE);
        System.out.println("Ta-da! The following task has been deleted.");
        System.out.println(taskToBeDeleted.getStatusOfTaskInString());
        System.out.println("Current number of tasks is: " + Integer.toString(tasks.size()) + ".");
        System.out.println(STRAIGHT_LINE);
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
     * Determines if it is the first time that the user uses the chatbot by checking for
     * the existence of a data file.
     *
     * @return true if it is the user's first time running the chatbot,
     *         else return false.
     */
    public static boolean isFirstRun() {
        //Check if the path to the data file is valid
        java.nio.file.Path desiredPath =  Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        return ! java.nio.file.Files.exists(desiredPath);
    }

    /**
     * Creates the data file for the user to store task history.
     *
     * @throws IOException if an I/O error occurred during file creation.
     */
    public static void createFile() throws IOException {
        //Make the directory
        java.nio.file.Path desiredPath =  Paths.get(System.getProperty("user.dir"), "data");
        File directory = new File(desiredPath.toString());
        if (! directory.exists()) {
            directory.mkdir();
        }

        //Create the file
        File dataFile = new File(dataFilePath);
        if (! dataFile.exists()) {
            dataFile.createNewFile();
        }
    }


    /**
     * Restores the task history stored in the data file.
     *
     * @param tasks The ArrayList that store all the tasks.
     * @throws FileNotFoundException if the data file cannot be found.
     */
    public static void restoreTaskHistory(ArrayList<Task> tasks) throws FileNotFoundException {
        File dataFile = new File(dataFilePath);
        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            String currentTask = s.nextLine();
            String[] currentTaskArray = currentTask.split(" \\| ");
            String commandType = currentTaskArray[0];
            //Adds each task stored in the data file into tasks
            switch (commandType) {
            case "T":
                Task toDoTask = new ToDo(currentTaskArray[2]);
                if (currentTaskArray[1].equals("1")) {
                    toDoTask.setDoneStatus();
                }
                tasks.add(toDoTask);
                break;
            case "D":
                Task deadlineTask = new Deadline(currentTaskArray[2], currentTaskArray[3]);
                if (currentTaskArray[1].equals("1")) {
                    deadlineTask.setDoneStatus();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                Task eventTask = new Event(currentTaskArray[2], currentTaskArray[3], currentTaskArray[4]);
                if (currentTaskArray[1].equals("1")) {
                    eventTask.setDoneStatus();
                }
                tasks.add(eventTask);
                break;
            }
        }
    }


    /**
     * Saves the tasks that are currently stored in the list into a data file stored in hard disk.
     *
     * @param tasks The list that contains the tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File dataFile = new File(dataFilePath);
            FileWriter fw = new FileWriter(dataFilePath);
            //Reset content of file
            fw.write("");
            fw = new FileWriter(dataFilePath, true);
            //Append new content into file
            for (int i = 0; i < tasks.size(); i = i + 1) {
                Task currentTask = tasks.get(i);
                String taskStatus = (currentTask.getStatusOfTask())
                                    ? "1 | "
                                    : "0 | ";
                String lineToAdd = (currentTask instanceof ToDo)
                                   ? "T | " + taskStatus + currentTask.getNameOfTask()
                                   : (currentTask instanceof Deadline)
                                   ? "D | " + taskStatus + currentTask.getNameOfTask() + " | "
                                           + ((Deadline) currentTask).getDeadlineOfTask()
                                   : "E | " + taskStatus + currentTask.getNameOfTask() + " | "
                                           + ((Event) currentTask).getStartDate() + " | "
                                                   + ((Event) currentTask).getEndDate();
                if (i != tasks.size() - 1) {
                    lineToAdd += "\n";
                }
                fw.write(lineToAdd);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save the tasks. The following error occurred: ");
            System.out.println(e.getMessage());
        }

    }


}


