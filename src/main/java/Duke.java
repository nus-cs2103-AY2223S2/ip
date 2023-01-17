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
                p.print(taskList.addTask(text));
            }
        }
    }
}
