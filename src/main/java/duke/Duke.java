package duke;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Represents a chatbot that one can interact with to keep track of tasks.
 */
public class Duke {

    public static void run() {
        //Prepare components
        Ui userInterface = new Ui();
        Storage storage = new Storage("data", "tasks.txt");
        TaskList taskList = new TaskList();
        Parser parser = new Parser();


        //Prepare data file
        if (! storage.prepareFile()) {
            //Shuts down the bot as data file cannot be created successfully
            userInterface.printShutDownMessage();
            return;
        }

        //Load data from data file
        if (! storage.loadTasksFromFile(taskList)) {
            //Cannot read from data file. Start with new empty task list.
            taskList = new TaskList();
        }

        //Read in and process user commands












    }






    /**
     * Launches the chatbot.
     *
     * @param args The command line arguments that one can type.
     */
    public static void main(String[] args) {

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
            //User typed in "help"
            else if (input.equals("help")) {
                System.out.println(STRAIGHT_LINE);
                System.out.println("Supported Commands:");
                System.out.println(COMMAND_LIST);
                System.out.println(STRAIGHT_LINE);
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

                    //Checks if the deadline is a valid date and time
                    Deadline newDeadlineTask = new Deadline(taskName, deadlineOfTask, getDateObject(deadlineOfTask));
                    addTask(newDeadlineTask, tasks);
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
                    break;
                } catch (DateTimeParseException dateTimeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println("Please check that you entered a valid date, and that the date should be in "
                            + "the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
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

                    //Create new event task
                    Temporal start = getDateObject(startDate);
                    Temporal end = getDateObject(endDate);

                    if (! isValidDuration(start, end)) {
                        throw new DukeException("Start date must be before end date.");
                    }

                    Event newEventTask = new Event(taskName, startDate, endDate, start, end);
                    addTask(newEventTask, tasks);
                    saveTasks(tasks);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
                    break;
                } catch (DateTimeParseException dateTimeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println("Please check that you entered a valid date, and that the date should be in "
                            + "the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
                    System.out.println(STRAIGHT_LINE);
                    break;
                }
            case "on":
                try {
                    String dateString = input.substring(3);
                    if (dateString.equals("")) {
                        throw new DukeException("The date cannot be left blank.");
                    }
                    Temporal dateObject = getDateObject(dateString);
                    System.out.println(STRAIGHT_LINE);
                    System.out.println("Tasks on: " + Task.formatDate(dateObject));
                    int count = 1;
                    for (int t = 0; t < tasks.size(); t = t + 1) {
                        Task currTask = tasks.get(t);
                        if (currTask instanceof Deadline) {
                            if (isEqualDate(dateObject, ((Deadline) currTask).getDeadline())) {
                                System.out.println(Integer.toString(count) + ". " +
                                        currTask.getStatusOfTaskInString());
                                count += 1;
                            }
                        }
                        else if (currTask instanceof Event) {
                            if (isValidDuration(((Event) currTask).getStartDate(), dateObject) &&
                                    isValidDuration(dateObject, ((Event) currTask).getEndDate())) {
                                System.out.println(Integer.toString(count) + ". " +
                                        currTask.getStatusOfTaskInString());
                                count += 1;
                            }
                        }
                    }
                    if (count == 1) {
                        System.out.println("You have no tasks on this day.");
                    }
                    System.out.println(STRAIGHT_LINE);
                    break;
                } catch (DukeException dukeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println(dukeException.getMessage());
                    System.out.println(STRAIGHT_LINE);
                } catch (DateTimeParseException dateTimeException) {
                    System.out.println(STRAIGHT_LINE);
                    System.out.println("Please check that you entered a valid date, and that the date should be in "
                            + "the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
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
<<<<<<< HEAD
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
        File dataFile = new File(DATA_FILE_PATH);
        if (! dataFile.exists()) {
            dataFile.createNewFile();
        }
    }




    /**
     * Restores the task history stored in the data file.
     *
     * @param tasks The ArrayList that store all the tasks.
     * @return true if successful, false otherwise.
     * @throws FileNotFoundException if the data file cannot be found.
     */
    public static boolean restoreTaskHistory(ArrayList<Task> tasks) throws FileNotFoundException {
        File dataFile = new File(DATA_FILE_PATH);
        Scanner s = new Scanner(dataFile);
        boolean isSuccessful = true;
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
                    try {
                        //Checks if the deadline is a valid date and time
                        Task deadlineTask = new Deadline(currentTaskArray[2], currentTaskArray[3],
                                getDateObject(currentTaskArray[3]));
                        if (currentTaskArray[1].equals("1")) {
                            deadlineTask.setDoneStatus();
                        }
                        tasks.add(deadlineTask);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println(STRAIGHT_LINE);
                        System.out.println("Please check that you did not modify the data file. Dates must be valid,"
                                + " and in the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
                        System.out.println(STRAIGHT_LINE);
                        isSuccessful = false;
                        break;
                    }
                case "E":
                    try {
                        //Create new event task
                        Temporal start = getDateObject(currentTaskArray[3]);
                        Temporal end = getDateObject(currentTaskArray[4]);
                        if (!isValidDuration(start, end)) {
                            throw new DukeException("Start date must be before end date.");
                        }
                        Task eventTask = new Event(currentTaskArray[2], currentTaskArray[3], currentTaskArray[4],
                                start, end);
                        if (currentTaskArray[1].equals("1")) {
                            eventTask.setDoneStatus();
                        }
                        tasks.add(eventTask);
                        break;
                    } catch (DukeException dukeException) {
                        System.out.println(STRAIGHT_LINE);
                        System.out.println(dukeException.getMessage());
                        System.out.println(STRAIGHT_LINE);
                        isSuccessful = true;
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println(STRAIGHT_LINE);
                        System.out.println("Please check that you did not modify the data file. Dates must be valid,"
                                + " and in the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
                        System.out.println(STRAIGHT_LINE);
                        isSuccessful = true;
                        break;
                    }
            }
        }
        s.close();
        return isSuccessful;
    }


    /**
     * Saves the tasks that are currently stored in the list into a data file stored in hard disk.
     *
     * @param tasks The list that contains the tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            //Reset content of file
            fw.write("");
            fw.flush();
            fw = new FileWriter(DATA_FILE_PATH, true);
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
                                           + ((Deadline) currentTask).getRawDeadline()
                                   : "E | " + taskStatus + currentTask.getNameOfTask() + " | "
                                           + ((Event) currentTask).getRawStartDate() + " | "
                                                   + ((Event) currentTask).getRawEndDate();
                if (i != tasks.size() - 1) {
                    lineToAdd += "\n";
                }
                fw.write(lineToAdd);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save the tasks. The following error occurred: ");
            System.out.println(e.getMessage());
        }

    }



    /**
     * Determines if two dates specify a valid duration.
     *
     * @param start The Temporal encapsulating the start date and time.
     * @param end The Temporal encapsulating the end date and time.
     * @return true if start happens before or is equal to end, else false.
     */
    public static boolean isValidDuration(Temporal start, Temporal end) {
        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            return ((LocalDateTime) end).isAfter((LocalDateTime) start) ||
                    ((LocalDateTime) end).equals((LocalDateTime) start);
        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            return ((LocalDate) end).isAfter((LocalDate) start) || ((LocalDate) end).equals((LocalDate) start);
        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            LocalDate endDateOnly = LocalDate.of(((LocalDateTime) end).getYear(),
                    ((LocalDateTime) end).getMonthValue(), ((LocalDateTime) end).getDayOfMonth());
            return (endDateOnly.isAfter((LocalDate) start)) || (endDateOnly.equals((LocalDate) start));
        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            LocalDate startDateOnly = LocalDate.of(((LocalDateTime) start).getYear(),
                    ((LocalDateTime) start).getMonthValue(), ((LocalDateTime) start).getDayOfMonth());
            return (((LocalDate) end).isAfter(startDateOnly)) || (((LocalDate) end).equals(startDateOnly));
        }
        return true;
    }

    /**
     * Determines if one date is equal to another date, based on year, month and day.
     *
     * @param start The Temporal encapsulating the start date and time.
     * @param end The Temporal encapsulating the end date and time.
     * @return true if both refer to the same day, else false.
     */
    public static boolean isEqualDate(Temporal start, Temporal end) {
        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            return ((LocalDateTime) end).equals((LocalDateTime) start);
        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            return ((LocalDate) end).equals((LocalDate) start);
        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            LocalDate endDateOnly = LocalDate.of(((LocalDateTime) end).getYear(),
                    ((LocalDateTime) end).getMonthValue(), ((LocalDateTime) end).getDayOfMonth());
            return (endDateOnly.equals((LocalDate) start));
        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            LocalDate startDateOnly = LocalDate.of(((LocalDateTime) start).getYear(),
                    ((LocalDateTime) start).getMonthValue(), ((LocalDateTime) start).getDayOfMonth());
            return (((LocalDate) end).equals(startDateOnly));
        }
        return true;
    }
}


