import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
public class Duke {
    protected static ArrayList<Task> taskList;
    protected static File dukeFile;
    protected static Scanner dukeScanner;
    public static void main(String[] args) {
        try {
            setupDuke();
        } catch (IOException io) {
            dukeSpeak("Something went wrong... :(");
            return;
        }

        Scanner scan = new Scanner(System.in);
        taskList = new ArrayList<>();


        String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        String userInput = null;
        while (!(userInput = scan.nextLine()).equals("bye")) {
            String[] splitInput = userInput.split(" ", 2);
            try {
                Command inputCommand = validateCommand(splitInput[0])
                        ? Command.valueOf(splitInput[0].toUpperCase()) : Command.INVALID;
                switch (inputCommand) {
                case LIST:
                    listTask();
                    break;
                case MARK:
                    markTask(splitInput);
                    break;
                case UNMARK:
                    unmarkTask(splitInput);
                    break;
                case DELETE:
                    deleteTask(splitInput);
                    break;
                case TODO:
                    addTodo(splitInput);
                    break;
                case DEADLINE:
                    addDeadline(splitInput);
                    break;
                case EVENT:
                    addEvent(splitInput);
                    break;
                default:
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } //end of switch-case

            } catch (DukeException de) {
                dukeSpeak(de.getMessage());

            }
        } // end of while-loop

        // Clean-up
        scan.close();
        dukeScanner.close();

        dukeSpeak("Bye. Hope to see you again soon!");

    }

    private static void setupDuke() throws IOException {
        dukeFile = new File("data/duke.txt");
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            dukeFile.createNewFile();
        } else if (!dukeFile.exists()) {
            dukeFile.createNewFile();
        }
        dukeScanner = new Scanner(dukeFile);

        taskList = new ArrayList<Task>();
        while (dukeScanner.hasNext()) {
            String[] oneTaskString = dukeScanner.nextLine().split(" \\| ");
            Task tempTask = null;
            if (oneTaskString[0].equals("T")) {
                tempTask = new Todo(oneTaskString[2]);
            } else if (oneTaskString[0].equals("D")) {
                LocalDateTime tempDateTime = LocalDateTime.parse(oneTaskString[3]);
                tempTask = new Deadline(oneTaskString[2], tempDateTime);
            } else if (oneTaskString[0].equals("E")) {
                String[] dateSplit = oneTaskString[3].split("-");
                LocalDateTime startDateTime = LocalDateTime.parse(dateSplit[0]);
                LocalDateTime endDateTime = LocalDateTime.parse(dateSplit[1]);
                tempTask = new Event(oneTaskString[2], startDateTime, endDateTime);
            }
            if (tempTask != null) {
                if (oneTaskString[1].equals("1")) {
                    tempTask.markTask();
                }
                taskList.add(tempTask);
            }
        } // end of while-loop


    } // end of setupDuke()

    protected static void saveToFile(String entry) throws IOException {
        // Maybe should just accept String, leave format to caller
        FileWriter dukeWriter = new FileWriter(dukeFile, true);
        dukeWriter.write(entry + "\n");
        dukeWriter.close();
    }

    protected static void deleteFromFile(int taskId) throws IOException {
        // Delete based on line number, accept integers as argument
        dukeScanner = new Scanner(dukeFile);
        File tempFile = new File("data/temp-duke.txt");
        Files.deleteIfExists(tempFile.toPath());
        tempFile.createNewFile();
        FileWriter tempWriter = new FileWriter(tempFile, true);
        int counter = 0;
        while (dukeScanner.hasNext()) {
            String taskEntry = dukeScanner.nextLine();
            if (counter != taskId) {
                tempWriter.write(taskEntry + "\n");
            }
            counter++;
        }
        tempWriter.close();
        Files.deleteIfExists(dukeFile.toPath());
        tempFile.renameTo(dukeFile);
        dukeFile = new File("data/duke.txt");
    }
    protected static void markEntryInFile(int taskId, boolean isMark) throws IOException {
        // Delete based on line number, accept integers as argument
        dukeScanner = new Scanner(dukeFile);
        File tempFile = new File("data/temp-duke.txt");
        Files.deleteIfExists(tempFile.toPath());

        tempFile.createNewFile();
        FileWriter tempWriter = new FileWriter(tempFile, true);
        int counter = 0;

        while (dukeScanner.hasNext()) {
            String taskEntry = dukeScanner.nextLine();
            if (counter == taskId) {
                String target = isMark ? "| 0 |" : "| 1 |";
                String replacement = isMark ? "| 1 |" : "| 0 |";
                taskEntry = taskEntry.replace(target, replacement);

            }

            tempWriter.write(taskEntry + "\n");
            counter++;
        }
        tempWriter.close();
        Files.deleteIfExists(dukeFile.toPath());
        tempFile.renameTo(new File("data/duke.txt"));
        dukeFile = new File("data/duke.txt");
    }
    protected static void addTodo(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo tempTodo = new Todo(input[1]);
        String saveString = "T | 0 | " + tempTodo.getDescription();
        try {
            saveToFile(saveString);
        } catch(IOException io) {
            throw new DukeException("Unsuccessful... Something went wrong... :(");
        }
        taskList.add(tempTodo);

        String message = "Got it. I've added this task:\n " + tempTodo.toString();
        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        dukeSpeak(message);
    }

    protected static void addDeadline(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description and due date of a deadline cannot be empty.");
        }
        String[] dlString = input[1].split(" /by ");
        if (dlString.length == 1 || dlString[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description and due date of a deadline cannot be empty.");
        }

        try {
            String[] dateTimeString = dlString[1].split(" ");
            String timeString = "00:00";
            if (dateTimeString.length == 2) {
                timeString = dateTimeString[1].substring(0, 2) + ":" + dateTimeString[1].substring(2);
            }
            LocalDateTime dueDate = LocalDateTime.parse(dateTimeString[0] + "T" + timeString); // format: 2007-12-03T10:15:30
            Deadline tempDeadline = new Deadline(dlString[0], dueDate);
            String saveString = "D | 0 | " + tempDeadline.getDescription() + " | " + tempDeadline.getByDate();
            saveToFile(saveString);
            taskList.add(tempDeadline);
            String message = "Got it. I've added this task:\n " + tempDeadline.toString();
            message += "\nNow you have " + taskList.size() + " tasks in the list.";
            dukeSpeak(message);
        } catch(DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        } catch(IOException io) {
            throw new DukeException("Unsuccessful... Something went wrong... :(");
        }

    }

    protected static void addEvent(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventString = input[1].split(" /from ");
        if (eventString.length == 1 || eventString[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The start & end time of an event cannot be empty.");
        }
        String[] timeSplit = eventString[1].split(" /to ");
        if (timeSplit.length == 1 || timeSplit[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The start & end time of an event cannot be empty.");
        }
        try {
            String[] dateTimeString = timeSplit[0].split(" ");
            String startTimeString = "00:00";
            if (dateTimeString.length == 2) {
                startTimeString = dateTimeString[1].substring(0, 2) + ":" + dateTimeString[1].substring(2);
            }
            LocalDateTime startDate = LocalDateTime.parse(dateTimeString[0] + "T" + startTimeString); // format: 2007-12-03T10:15:30
            String endTimeString = timeSplit[1].substring(0, 2) + ":" + timeSplit[1].substring(2);
            LocalDateTime endDate = LocalDateTime.parse(dateTimeString[0] + "T" + endTimeString);

            Event tempEvent = new Event(eventString[0], startDate, endDate);
            String saveString = "E | 0 | " + tempEvent.getDescription() + " | "
                    + tempEvent.getFromDate() + "-" + tempEvent.getToDate();
            saveToFile(saveString);

            taskList.add(tempEvent);
            String message = "Got it. I've added this task:\n " + tempEvent.toString();
            message += "\nNow you have " + taskList.size() + " tasks in the list.";
            dukeSpeak(message);

        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        } catch(IOException io) {
            throw new DukeException("Unsuccessful... Something went wrong... :(");
        }



    }

    protected static void listTask() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            Task oneTask = taskList.get(i);
            message += "\n" + (i + 1) + ". " + oneTask.toString();
        }
        dukeSpeak(message);
    }

    protected static void markTask(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to mark.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (taskNum > taskList.size() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        try {
            markEntryInFile(taskNum - 1, true);
        } catch(IOException io) {
            System.out.println(io.getMessage());
            throw new DukeException("Unsuccessful... Something went wrong... :(");
        }
        Task oneTask = taskList.get(taskNum - 1);
        oneTask.markTask();
        String message = "Nice! I've marked this task as done:\n " + oneTask.toString();
        dukeSpeak(message);
    }

    protected static void unmarkTask(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to unmark.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (taskNum > taskList.size() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        try {
            markEntryInFile(taskNum - 1, false);
        } catch(IOException io) {
            throw new DukeException("Unsuccessful... Something went wrong... :(");
        }
        Task oneTask = taskList.get(taskNum - 1);
        oneTask.unmarkTask();
        String message = "OK! I've marked this task as not done yet:\n " + oneTask.toString();
        dukeSpeak(message);
    }

    protected static void deleteTask(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to delete.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (taskNum > taskList.size() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        try {
            deleteFromFile(taskNum-1);
        } catch (IOException io) {
            throw new DukeException("Unsuccessful... Something went wrong... :(");
        }
        Task delTask = taskList.get(taskNum-1);
        taskList.remove(taskNum-1);
        String message = "Noted. I've removed this task:\n " + delTask.toString();
        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        dukeSpeak(message);
    }

    protected static boolean validateCommand(String command) {
        Command[] allCommands = Command.values();
        for (int i = 0; i < allCommands.length; i++) {
            if (allCommands[i].toString().equals(command.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    protected static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
