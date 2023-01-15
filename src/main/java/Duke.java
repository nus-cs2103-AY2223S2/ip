import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> listOfTasks = new ArrayList<>(100);
        boolean isExit = false;
        int taskNumber = 0;
        Task taskAtHand;
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String separator = "\t____________________________________________________________";
        System.out.println(separator
                + "\n\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                + separator);
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            String command = sc.nextLine();
            if(command.startsWith("mark")){
                String [] stringArray = command.split(" ");
                command = "mark";
                taskNumber = Integer.parseInt(stringArray[1]);

            }
            if(command.startsWith("unmark")){
                String [] stringArray = command.split(" ");
                command = "unmark";
                taskNumber = Integer.parseInt(stringArray[1]);
            }
            switch(command) {
                case "bye":
                    System.out.println(separator
                            + "\n\t Bye. Hope to see you again soon!\n"
                            + separator);
                    isExit = true;
                    break;
                case "list":
                    int i = 1;
                    System.out.println(separator);
                    System.out.println("\tHere are the tasks in your list:");
                    for(Task task : listOfTasks) {
                        System.out.println("\t"
                                + i
                                + ". "
                                + task.printStatus());
                        i++;
                    }
                    System.out.println(separator);
                    break;

                case "mark":
                    taskAtHand = listOfTasks.get(taskNumber - 1);
                    taskAtHand.markDone();
                    System.out.println(separator);
                    System.out.println("\tNice! I've marked this task as done:\n\t"
                            + taskAtHand.printStatus());
                    System.out.println(separator);
                    break;

                case "unmark":
                    taskAtHand = listOfTasks.get(taskNumber - 1);
                    taskAtHand.markUnDone();
                    System.out.println(separator);
                    System.out.println("\tOK, I've marked this task as not done yet:\n\t"
                            + taskAtHand.printStatus());
                    System.out.println(separator);
                    break;

                default:
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
}
