import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    protected static ArrayList<Task> taskList;
    public static void main(String[] args) {
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
        while(!(userInput = scan.nextLine()).equals("bye")) {
            String[] splitCheck = userInput.split(" ", 2);
            try {
                Command inputCommand = validateCommand(splitCheck[0])
                        ? Command.valueOf(splitCheck[0].toUpperCase())
                        : Command.INVALID;
                switch(inputCommand) {
                case LIST:
                    listTask();
                    break;
                case MARK:
                    mark(splitCheck);
                    break;
                case UNMARK:
                    unmark(splitCheck);
                    break;
                case DELETE:
                    delete(splitCheck);
                    break;
                case TODO:
                    todo(splitCheck);
                    break;
                case DEADLINE:
                    deadline(splitCheck);
                    break;
                case EVENT:
                    event(splitCheck);
                    break;
                default:
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } //end of switch-case

            } catch (DukeException de) {
                dukeSpeak(de.getMessage());
            }
        } // end of while-loop

        dukeSpeak("Bye. Hope to see you again soon!");

    }
    public static void todo(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo tempTodo = new Todo(input[1]);
        taskList.add(tempTodo);
        String message = "Got it. I've added this task:\n " + tempTodo.toString();
        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        dukeSpeak(message);
    }

    public static void deadline(String[] input) throws DukeException {
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
            taskList.add(tempDeadline);
            String message = "Got it. I've added this task:\n " + tempDeadline.toString();
            message += "\nNow you have " + taskList.size() + " tasks in the list.";
            dukeSpeak(message);
        } catch(DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        }

    }

    public static void event(String[] input) throws DukeException {
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
            taskList.add(tempEvent);
            String message = "Got it. I've added this task:\n " + tempEvent.toString();
            message += "\nNow you have " + taskList.size() + " tasks in the list.";
            dukeSpeak(message);

        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        }


    }

    public static void listTask() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            Task oneTask = taskList.get(i);
            message += "\n" + (i + 1) + ". " + oneTask.toString();
        }
        dukeSpeak(message);
    }

    public static void mark(String[] input) throws DukeException{
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to mark.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (taskNum > taskList.size() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        Task oneTask = taskList.get(taskNum - 1);
        oneTask.markTask();
        String message = "Nice! I've marked this task as done:\n " + oneTask.toString();
        dukeSpeak(message);
    }

    public static void unmark(String[] input) throws DukeException{
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to unmark.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (taskNum > taskList.size() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        Task oneTask = taskList.get(taskNum - 1);
        oneTask.unmarkTask();
        String message = "OK! I've marked this task as not done yet:\n " + oneTask.toString();
        dukeSpeak(message);
    }

    public static void delete(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to delete.");
        }
        int taskNum = Integer.parseInt(input[1]);
        if (taskNum > taskList.size() || taskNum <= 0) {
            throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
        }
        Task delTask = taskList.get(taskNum-1);
        taskList.remove(taskNum-1);
        String message = "Noted. I've removed this task:\n " + delTask.toString();
        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        dukeSpeak(message);
    }

    public static boolean validateCommand(String command) {
        Command[] allCommands = Command.values();
        for (int i = 0; i < allCommands.length; i++) {
            if (allCommands[i].toString().equals(command.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    public static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
