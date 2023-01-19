import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
public class Duke {
    private static String printList(ArrayList<Task> toDoList) {
        String res = "";
        res += "here are your tasks\n";
        for (int i = 0; i < toDoList.size(); i++) {
            res += String.format("%d.%s\n", i + 1, toDoList.get(i));
        }
        return res;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        /* project starts here */
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();
        while (true) {
            String command = sc.nextLine();
            switch (command.split(" ")[0]) {
                case "list":
                    System.out.println(printList(toDoList));
                    break;
                case "mark":
                    int indexToMark = Character.getNumericValue
                            (command.charAt(command.length() - 1)) - 1;
                    toDoList.get(indexToMark).markDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "    " +
                            toDoList.get(indexToMark));
                    break;
                case "unmark":
                    indexToMark = Character.getNumericValue
                            (command.charAt(command.length() - 1)) - 1;
                    toDoList.get(indexToMark).markUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "    " +
                            toDoList.get(indexToMark));
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                    return;
                default:
                    Task curTask = new Task(command);
                    toDoList.add(curTask);
                    System.out.println("added: " + command);
            }
        }
    }
}
