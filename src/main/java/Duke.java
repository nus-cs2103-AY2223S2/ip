import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String separator = "\t____________________________________________________________";
    public static boolean isExit = false;
    public static String [] stringSplitArray;
    public static String [] timeSplitArray;
    private static DataStorage storage = new DataStorage(System.getProperty("user.home") + "/data/data.txt");
    public static void bye() {
        System.out.println(separator
                + "\n\t Bye. Hope to see you again soon!\n"
                + separator);
        isExit = true;
    }

    public enum Commands {
        bye,
        list,
        mark,
        unmark,
        deadline,
        event,
        todo,
        delete
    }
    public static void start() throws FileNotFoundException {
        Task taskAtHand;
        String taskNumber, command;
        System.out.println(logo
                + "\n"
                + separator
                + "\n\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + separator);
        Scanner sc = new Scanner(System.in);
        storage.printCurrentTask();
        TaskList taskList = new TaskList(storage.load());
        while (!isExit) {
            try {
                Commands commands = Commands.valueOf(sc.next().toLowerCase());
                switch (commands) {
                case bye:
                    Duke.bye();
                    storage.save(taskList.getListOfTasks());
                    break;

                case list:
                    if (taskList.isEmpty()) {
                        throw new DukeException("The list is currently empty!");
                    } else {
                        taskList.printList();
                    }
                    break;

                case mark:
                    taskNumber = sc.nextLine();
                    if (taskNumber.isBlank()) {
                        throw new DukeException("Please enter the task number to mark! :D ");
                    } else {
                        int number = Integer.parseInt(taskNumber.trim());
                        taskAtHand = taskList.getListOfTasks().get(number - 1);
                        taskAtHand.markDone();
                        System.out.println(separator
                                + "\n\tNice! I've marked this task as done:\n\t"
                                + taskAtHand
                                + "\n"
                                + separator);
                    }
                    break;

                case unmark:
                    taskNumber = sc.nextLine();
                    if (taskNumber.isBlank()) {
                        throw new DukeException("Please enter the task number to unmark! :D ");
                    } else {
                        int number = Integer.parseInt(taskNumber.trim());
                        taskAtHand = taskList.getListOfTasks().get(number - 1);
                        taskAtHand.markUnDone();
                        System.out.println(separator
                                + "\n\tOK, I've marked this task as not done yet:\n\t"
                                + taskAtHand
                                + "\n"
                                + separator);
                    }
                    break;

                case deadline:
                    command = sc.nextLine();
                    if (command.isBlank()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        stringSplitArray = command.split("/by");
                        if (stringSplitArray.length == 1) {
                            throw new DukeException("Please enter a valid deadline");
                        }
                        taskAtHand = new Deadline(stringSplitArray[0], stringSplitArray[1], false);
                        taskList.addTask(taskAtHand);
                        System.out.println(separator
                                + "\n\tGot it. I've added this task:\n"
                                + "\t"
                                + taskAtHand
                                + "\n"
                                + taskList.printTaskCount()
                                + "\n"
                                + separator);
                    }
                    break;

                case event:
                    command = sc.nextLine();
                    if (command.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else {
                        stringSplitArray = command.split("/from");
                        timeSplitArray = stringSplitArray[1].split("/to");
                        if (timeSplitArray.length == 1) {
                            throw new DukeException("Please enter a valid date range!");
                        } else {
                            taskAtHand = new Event(stringSplitArray[0], timeSplitArray[0], timeSplitArray[1], false);
                            taskList.addTask(taskAtHand);
                            System.out.println(separator
                                    + "\n\tGot it. I've added this task:\n"
                                    + "\t"
                                    + taskAtHand
                                    + "\n"
                                    + taskList.printTaskCount()
                                    + "\n"
                                    + separator);
                        }
                    }
                    break;

                case todo:
                    command = sc.nextLine();
                    if (command.isBlank()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        taskAtHand = new ToDo(command.trim(), false);
                        taskList.addTask(taskAtHand);
                        System.out.println(separator
                                + "\n\tGot it. I've added this task:\n"
                                + "\t"
                                + taskAtHand
                                + "\n"
                                + taskList.printTaskCount()
                                + "\n"
                                + separator);
                    }
                    break;

                case delete:
                    taskNumber = sc.nextLine();
                    if (taskNumber.isBlank()) {
                        throw new DukeException("Please enter the task number to delete! :D ");
                    } else {
                        int number = Integer.parseInt(taskNumber.trim());
                        taskAtHand = taskList.getListOfTasks().remove(number - 1);
                        System.out.println(separator
                                + "\n\tNoted. I've removed this task:\n\t"
                                + taskAtHand
                                + "\n"
                                + taskList.printTaskCount()
                                + "\n"
                                + separator);
                    }
                    break;

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(separator
                        + "\n\t"
                        + e.getMessage()
                        + "\n"
                        + separator);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(separator
                        + "\n\t"
                        + "Please enter a valid task number!"
                        + "\n"
                        + separator);
            } catch (IllegalArgumentException e) {
                System.out.println(separator
                        + "\n\t"
                        + "Please enter a valid command!"
                        + "\n"
                        + separator);
            } catch (DateTimeParseException e) {
                System.out.println(separator
                        + "\n\t"
                        + "Please enter a date in e.g yyyy-mm-dd 23:59 format!"
                        + "\n"
                        + separator);
            
            }
        }
    }

    public static void main(String[] args) {
        try {
            Duke.start();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
