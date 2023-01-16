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
    public static void start(){
        Task taskAtHand;
        int taskNumber;
        System.out.println(logo
                + "\n"
                + separator
                + "\n\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + separator);
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            String command = sc.next();;
            switch(command) {
                case "bye":
                    Duke.bye();
                    break;
                case "list":
                    Duke.toList();
                    break;

                case "mark":
                    taskNumber = sc.nextInt();
                    taskAtHand = listOfTasks.get(taskNumber - 1);
                    taskAtHand.markDone();
                    System.out.println(separator
                            + "\n\tNice! I've marked this task as done:\n\t"
                            + taskAtHand.toString()
                            + "\n"
                            + separator);
                    break;

                case "unmark":
                    taskNumber = sc.nextInt();
                    taskAtHand = listOfTasks.get(taskNumber - 1);
                    taskAtHand.markUnDone();
                    System.out.println(separator
                            +"\n\tOK, I've marked this task as not done yet:\n\t"
                            + taskAtHand.toString()
                            + "\n"
                            + separator);
                    break;

                case "deadline":
                    command = sc.nextLine();
                    stringSplitArray = command.split("/by");
                    taskAtHand = new Deadline(stringSplitArray[0], stringSplitArray[1]);
                    listOfTasks.add(taskAtHand);
                    System.out.println(separator
                            + "\n\tGot it. I've added this task:\n"
                            + "\t"
                            + taskAtHand.toString()
                            + "\n"
                            + Duke.printTaskCount()
                            + "\n"
                            + separator);
                    break;

                case "event":
                    command = sc.nextLine();
                    stringSplitArray = command.split("/from");
                    timeSplitArray = stringSplitArray[1].split("/to");
                    taskAtHand = new Event(stringSplitArray[0], timeSplitArray[0], timeSplitArray[1]);
                    listOfTasks.add(taskAtHand);
                    System.out.println(separator
                            + "\n\tGot it. I've added this task:\n"
                            + "\t"
                            + taskAtHand.toString()
                            + "\n"
                            + Duke.printTaskCount()
                            + "\n"
                            + separator);
                    break;

                case "todo":
                    command = sc.nextLine();
                    taskAtHand = new ToDo(command);
                    listOfTasks.add(taskAtHand);
                    System.out.println(separator
                            + "\n\tGot it. I've added this task:\n"
                            + "\t"
                            + taskAtHand.toString()
                            + "\n"
                            + Duke.printTaskCount()
                            + "\n"
                            + separator);
                    break;

                default:
                    command = command + sc.nextLine();
                    Task task = new Task(command);
                    System.out.println(separator
                            + "\n\t"
                            + "added: "
                            + command
                            + "\n"
                            + separator);
                    listOfTasks.add(task);
            }

        }
    }
    public static void main(String[] args) {
        Duke.start();
    }
}
