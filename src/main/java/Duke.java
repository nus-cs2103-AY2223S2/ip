import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Printer p = new Printer();
        String text;
        String terminate = "bye";
        Scanner s = new Scanner(System.in);
        Tasklist taskList = new Tasklist();
        boolean flag = true;

        p.printWelcome();

        while (flag) {
            text = s.nextLine();
            if (text.equals(terminate)) {
                p.printExit();
                flag = false;
            } else if (text.equals("list")) {
                p.print(taskList.listTasks());
            } else if (text.split(" ", 2)[0].equals("mark")) {
                p.print(taskList.markTask(Integer.parseInt(text.split(" ", 2)[1])));
            } else if (text.split(" ", 2)[0].equals("unmark")) {
                p.print(taskList.unmarkTask(Integer.parseInt(text.split(" ", 2)[1])));
            } else {
                if (text.split(" ", 2)[0].equals("todo")) {
                    String input = text.split(" ", 2)[1];
                    p.print(taskList.addTask(input));
                } else if (text.split(" ", 2)[0].equals("deadline")) {
                    String[] input = text.split(" ", 2)[1].split(" /by ", 2); // input[0] is description of task input[1] is by
                    p.print(taskList.addTask(input[0], input[1]));
                } else if (text.split(" ", 2)[0].equals("event")) {
                    String[] input = text.split(" ", 2)[1].split("/", 3); // input[0] is description of task input[1] is from, [2] is to
                    p.print(taskList.addTask(input[0], input[1].substring(5), input[2].substring(3)));
                } else {
                    p.print("Invalid command");
                }
            }
        }
    }
}
