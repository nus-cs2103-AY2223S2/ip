import java.util.Scanner;

public class Duke {
    static String processCommand(String command, TaskList taskList) {
        String commandArr[] = command.split(" ");
        if (commandArr[0].equals("list")) {
            return taskList.toString();
        } else if (commandArr[0].equals("mark")) {
            return taskList.markTask(Integer.parseInt(commandArr[1]));
        } else if (commandArr[0].equals("unmark")) {
            return taskList.unmarkTask(Integer.parseInt(commandArr[1]));
        } else {
            return taskList.addTask(command);
        }
    }

    static String greetings() {
        return "\tHello! I'm Duke\n\tWhat can I do for you?\n";
    }

    static String goodbye() {
        return "\tBye. Hope to see you soon!\n";
    }

    static String display(String response) {
        String horizontalLine = "\t______________________________________\n";
        return String.format("%s%s%s", horizontalLine, response, horizontalLine);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System .in);
        System.out.println(display(greetings()));
        String command;
        TaskList taskList = new TaskList();
        while (!(command = sc.nextLine()).equals("bye")) {
            System.out.println(display(processCommand(command, taskList)));
        }
        System.out.println(display(goodbye()));
    }
}
