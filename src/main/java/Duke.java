import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] storeTasks = new Task[100];
        int numElem = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "How may I be of service to you? \nEnter your command:");
        String commandToEcho = sc.nextLine();
        while (!commandToEcho.equals("bye")) {
            if(commandToEcho.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numElem; i++) {
                    System.out.println(String.format("%d. %s",i+1,storeTasks[i]));
                }
            } else if (commandToEcho.length() >=4 && commandToEcho.substring(0, 4).equals("mark") ) {
                    int intTaskIndex = getTaskIndex(commandToEcho);
                    storeTasks[intTaskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n " + storeTasks[intTaskIndex].toString());
            } else if (commandToEcho.length() >=6 && commandToEcho.substring(0, 6).equals("unmark")) {
                int intTaskIndex = getTaskIndex(commandToEcho);
                storeTasks[intTaskIndex].markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n " + storeTasks[intTaskIndex].toString());
            } else {
                storeTasks[numElem] = new Task(commandToEcho);
                numElem++;
                System.out.println("added: " + commandToEcho);
            }
            commandToEcho = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

    static int getTaskIndex(String commandToEcho) {
        String taskIndex = "";
        int toMinus = 1;
        char fromBack = commandToEcho.charAt(commandToEcho.length() - toMinus);
        while (fromBack != (' ')) {
            taskIndex = fromBack + taskIndex;
            toMinus++;
            fromBack = commandToEcho.charAt(commandToEcho.length() - toMinus);
        }
        int intTaskIndex = Integer.parseInt(taskIndex) - 1;
        return intTaskIndex;
    }
}
