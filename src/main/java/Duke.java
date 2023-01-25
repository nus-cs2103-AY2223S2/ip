import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;


public class Duke {
    private final static String[] COMMANDS_LIST =
            new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline", "delete"};

    enum Commands {
        start, list, bye, todo, mark, unmark, event, deadline, delete
    }

    public static void markTask(ArrayList<Task> taskList, Storage dataStorage, int index) throws DukeException {
        try {
            Task unmarkedTask = taskList.get(index);
            Task markedTask = unmarkedTask.markTask();
            taskList.set(index, markedTask);
            dataStorage.writeFile(taskList);
        } catch (NumberFormatException err) {
            throw new DukeException("The task number given is not numeric!");
        }
    }

    public static void unmarkTask(ArrayList<Task> taskList, Storage dataStorage, int index) throws DukeException {
        try {
            Task markedTask = taskList.get(index);
            Task unmarkedTask = markedTask.unmarkTask();
            taskList.set(index, unmarkedTask);
            dataStorage.writeFile(taskList);
        } catch (DukeException err) {
            throw new DukeException("The task number given is not numeric!");
        }
    }

    public static void addTask(ArrayList<Task> taskList, Storage dataStorage, Task newTask) {
        taskList.add(newTask);
        dataStorage.writeFile(taskList);
        System.out.println("Got it, I've added this task:");
        System.out.println(newTask);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }

    public static void deleteTask(ArrayList<Task> taskList, Storage dataStorage, int index) throws DukeException {
        try {
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            dataStorage.writeFile(taskList);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask);
            System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        } catch (NumberFormatException err) {
            throw new DukeException("The task number given is not numeric!");
        }
    }

    public static String checkCommand(String command) throws DukeException {
        for (String cmd : COMMANDS_LIST) {
            if (cmd.equals(command)) {
                return command;
            }
        }
        throw new DukeException("I don't know what this command means!");
    }

    public static String getTaskNumber(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("No task number was given!");
        } else {
            return splitInput[1];
        }
    }


    public static int checkTaskNumber(ArrayList<Task> taskList, String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new DukeException("The task number given does not exist!");
            } else {
                return index;
            }
        } catch (NumberFormatException err) {
            throw new DukeException("The task number given is not numeric!");
        }
    }

    public static void checkDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("There was no task description given!");
        }
    }

    public static int checkDeadline(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("There was no deadline given!");
        } else {
            return byIndex;
        }
    }

    public static int checkStarting(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/from");
        if (byIndex == -1) {
            throw new DukeException("Please indicate a starting period!");
        } else {
            return byIndex;
        }
    }

    public static int checkEnding(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/to");
        if (byIndex == -1) {
            throw new DukeException("Please indicate an ending period!");
        } else {
            return byIndex;
        }
    }

    public static LocalDateTime convertTimestamp(String timestamp) throws DukeException {
        if (timestamp.equals("")) {
            throw new DukeException("There was no time period given!");
        }
        try {
            String[] dateTime = timestamp.split(" ");
            LocalDate date = LocalDate.parse(dateTime[0]);
            int hour = Integer.parseInt(dateTime[1].substring(0, 2));
            int min = Integer.parseInt(dateTime[1].substring(2, 4));
            LocalTime time = LocalTime.of(hour, min);
            return LocalDateTime.of(date, time);
        } catch (DateTimeParseException err) {
            throw new DukeException("Date formatting is wrong! Must be yyyy-MM-dd");
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Time not stated!");
        } catch (StringIndexOutOfBoundsException | NumberFormatException err) {
            throw new DukeException("Time must be in HHmm format!");
        } catch (DateTimeException err) {
            throw new DukeException("Time given is not valid!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Storage dataStorage = new Storage();
        ArrayList<Task> taskList = dataStorage.readFile();
        String input;
        Commands command = Commands.start;
        while (!command.equals(Commands.bye)) {
            // split command into each word
            input = sc.nextLine();
            String[] splitInput = input.split(" ");
            String cmd = splitInput[0];
            try {
                command = Commands.valueOf(checkCommand(cmd));
                String taskNumber;
                int index;
                String description;
                Task newTask;
                switch (command) {
                case delete:
                    try {
                        // taskNumber in 1-indexing
                        taskNumber = getTaskNumber(splitInput);
                        // index in 0-indexing
                        index = checkTaskNumber(taskList, taskNumber);
                        deleteTask(taskList, dataStorage, index);
                    } catch (DukeException err) {
                        System.out.println(err.getErrorMessage());
                    }
                    break;
                case list:
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        System.out.printf("%d.%s%n", i + 1, task);
                    }
                    break;
                case bye:
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case mark:
                    try {
                        // taskNumber in 1-indexing
                        taskNumber = getTaskNumber(splitInput);
                        // index in 0-indexing
                        index = checkTaskNumber(taskList, taskNumber);
                        markTask(taskList, dataStorage, index);
                    } catch (DukeException err) {
                        System.out.println(err.getErrorMessage());
                    }
                    break;
                case unmark:
                    try {
                        taskNumber = getTaskNumber(splitInput);
                        index = checkTaskNumber(taskList, taskNumber);
                        unmarkTask(taskList, dataStorage, index);
                    } catch (DukeException err) {
                        System.out.println(err.getErrorMessage());
                    }
                    break;
                case todo:
                    try {
                        description = String.join(" ",
                                Arrays.copyOfRange(splitInput, 1, splitInput.length));
                        checkDescription(description);
                        newTask = new Todo(description);
                        addTask(taskList, dataStorage, newTask);
                    } catch (DukeException err) {
                        System.out.println(err.getErrorMessage());
                    }
                    break;
                case deadline:
                    try {
                        int byIndex = checkDeadline(splitInput);
                        description = String.join(" ", Arrays.copyOfRange(splitInput, 1, byIndex));
                        checkDescription(description);
                        String deadline = String.join(" ", Arrays.copyOfRange(splitInput,
                                byIndex + 1, splitInput.length));
                        LocalDateTime formattedDeadline = convertTimestamp(deadline);
                        newTask = new Deadline(description, formattedDeadline);
                        addTask(taskList, dataStorage, newTask);
                    } catch (DukeException err) {
                        System.out.println(err.getErrorMessage());
                    }
                    break;
                case event:
                    try {
                        int fromIndex = checkStarting(splitInput);
                        int toIndex = checkEnding(splitInput);
                        description = String.join(" ", Arrays.copyOfRange(splitInput, 1, fromIndex));
                        checkDescription(description);
                        String from = String.join(" ",
                                Arrays.copyOfRange(splitInput, fromIndex + 1, toIndex));
                        LocalDateTime formattedFrom = convertTimestamp(from);
                        String to = String.join(" ",
                                Arrays.copyOfRange(splitInput, toIndex + 1, splitInput.length));
                        LocalDateTime formattedTo = convertTimestamp(to);
                        newTask = new Event(description, formattedFrom, formattedTo);
                        addTask(taskList, dataStorage, newTask);
                    } catch (DukeException err) {
                        System.out.println(err.getErrorMessage());
                    }
                    break;
                }
            } catch (DukeException err) {
                System.out.println(err.getErrorMessage());
            }
        }
    }
}
