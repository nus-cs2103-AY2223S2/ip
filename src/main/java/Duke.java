import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static String processCommand(String command, TaskList taskList) throws DukeException {
        String[] commandArr = command.split(" ");
        if (commandArr[0].equals("list")) {
            return taskList.toString();
        } else if (commandArr[0].equals("mark")) {
            return taskList.markTask(Integer.parseInt(commandArr[1]));
        } else if (commandArr[0].equals("unmark")) {
            return taskList.unmarkTask(Integer.parseInt(commandArr[1]));
        } else if (commandArr[0].equals("delete")) {
            return taskList.deleteTask(Integer.parseInt(commandArr[1]));
        }
        return taskList.addTask(command);
    }

    static String greetings() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    static String goodbye() {
        return "Bye. Hope to see you soon!";
    }

    static String display(String response) {
        String horizontalLine = "\t______________________________________\n";
        String[] responseArr = response.split("\\r?\\n");
        String responseFinal = "";
        //add indentation
        for (String r: responseArr) {
            responseFinal += "\t" + r + "\n";
        }
        return String.format("%s%s%s", horizontalLine, responseFinal, horizontalLine);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(display(greetings()));
        String command = "", printable = "";
        TaskList taskList = new TaskList();
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                printable = processCommand(command, taskList);
            } catch (Exception e) {
                printable = "OOPS! " + e.getMessage();
            } finally {
                System.out.println(display(printable));
            }
        }
        System.out.println(display(goodbye()));
    }
}
