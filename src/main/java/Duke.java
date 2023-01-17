import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String separator = "\t____________________________________________________________";
    public static boolean isExit = false;
    public static ArrayList<Task> listOfTasks = new ArrayList<>(100);

    public static String [] stringSplitArray;
    public static String [] timeSplitArray;
    public static void bye(){
        System.out.println(separator
                + "\n\t Bye. Hope to see you again soon!\n"
                + separator);
        isExit = true;
    }

    public static String printTaskCount(){
        return "\tNow you have "
                + listOfTasks.size()
                + " tasks in the list.";
    }

    public static void toList(){
        int i = 1;
        System.out.println(separator
                + "\n\tHere are the tasks in your list:");
        for(Task task : listOfTasks) {
            System.out.println("\t"
                    + i
                    + ". "
                    + task.toString());
            i++;
        }
        System.out.println(separator);
    }
    public static void start() {
        Task taskAtHand;
        String taskNumber;
        System.out.println(logo
                + "\n"
                + separator
                + "\n\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + separator);
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String command = sc.next();
                switch (command) {
                    case "bye":
                        Duke.bye();
                        break;

                    case "list":
                        if (listOfTasks.size() == 0) {
                            throw new DukeException("The list is currently empty!");
                        } else {
                            Duke.toList();
                        }
                        break;

                    case "mark":
                        taskNumber = sc.nextLine();
                        if (taskNumber.isBlank()) {
                            throw new DukeException("Please enter the task number to mark! :D ");
                        } else {
                            int number = Integer.parseInt(taskNumber.trim());
                            taskAtHand = listOfTasks.get(number - 1);
                            taskAtHand.markDone();
                            System.out.println(separator
                                    + "\n\tNice! I've marked this task as done:\n\t"
                                    + taskAtHand
                                    + "\n"
                                    + separator);
                        }
                        break;

                    case "unmark":
                        taskNumber = sc.nextLine();
                        if (taskNumber.isBlank()) {
                            throw new DukeException("Please enter the task number to unmark! :D ");
                        } else {
                            int number = Integer.parseInt(taskNumber.trim());
                            taskAtHand = listOfTasks.get(number - 1);
                            taskAtHand.markUnDone();
                            System.out.println(separator
                                    + "\n\tOK, I've marked this task as not done yet:\n\t"
                                    + taskAtHand
                                    + "\n"
                                    + separator);
                        }
                        break;

                    case "deadline":
                        command = sc.nextLine();
                        if (command.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            stringSplitArray = command.split("/by");
                            if (stringSplitArray.length == 1) {
                                throw new DukeException("Please enter a valid deadline");
                            }
                            taskAtHand = new Deadline(stringSplitArray[0], stringSplitArray[1]);
                            listOfTasks.add(taskAtHand);
                            System.out.println(separator
                                    + "\n\tGot it. I've added this task:\n"
                                    + "\t"
                                    + taskAtHand
                                    + "\n"
                                    + Duke.printTaskCount()
                                    + "\n"
                                    + separator);
                        }
                        break;

                    case "event":
                        command = sc.nextLine();
                        if (command.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        } else {
                            stringSplitArray = command.split("/from");
                            timeSplitArray = stringSplitArray[1].split("/to");
                            if (timeSplitArray.length == 1) {
                                throw new DukeException("Please enter a valid date range!");
                            } else {
                                taskAtHand = new Event(stringSplitArray[0], timeSplitArray[0], timeSplitArray[1]);
                                listOfTasks.add(taskAtHand);
                                System.out.println(separator
                                        + "\n\tGot it. I've added this task:\n"
                                        + "\t"
                                        + taskAtHand
                                        + "\n"
                                        + Duke.printTaskCount()
                                        + "\n"
                                        + separator);
                            }
                        }
                        break;

                    case "todo":
                        command = sc.nextLine();
                        if (command.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            taskAtHand = new ToDo(command);
                            listOfTasks.add(taskAtHand);
                            System.out.println(separator
                                    + "\n\tGot it. I've added this task:\n"
                                    + "\t"
                                    + taskAtHand
                                    + "\n"
                                    + Duke.printTaskCount()
                                    + "\n"
                                    + separator);
                        }
                        break;

                    case "delete":
                        taskNumber = sc.nextLine();
                        if (taskNumber.isBlank()) {
                            throw new DukeException("Please enter the task number to delete! :D ");
                        } else {
                            int number = Integer.parseInt(taskNumber.trim());
                            taskAtHand = listOfTasks.remove(number - 1);
                            System.out.println(separator
                                    + "\n\tNoted. I've removed this task:\n\t"
                                    + taskAtHand
                                    + "\n"
                                    + Duke.printTaskCount()
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
            } catch (NumberFormatException e) {
                System.out.println(separator
                        + "\n\t"
                        + "Please enter a valid task number!"
                        + "\n"
                        + separator);
            } catch (IndexOutOfBoundsException e){
                System.out.println(separator
                        + "\n\t"
                        + "Please enter a valid task number!"
                        + "\n"
                        + separator);
            }
        }
    }
    
    public static void main(String[] args) {
        Duke.start();
    }
}
