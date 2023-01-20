import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "How may i help you?\n");

        Scanner sc = new Scanner(System.in);

        String echo = sc.nextLine();

        List<Task> store = new ArrayList<>();

        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.print(i + 1 + ":" + store.get(i) + "\n");
                }
                echo = sc.nextLine();
            } else if ((echo.length() > 4) && (echo.substring(0,4).equals("mark"))) {
                int index = Integer.parseInt(echo.substring(5));
                index--;
                store.get(index).markAsDone();
                System.out.print("Nice! I've marked this task as done:\n" + store.get(index).toString() + "\n");
                echo = sc.nextLine();
            } else if ((echo.length() > 6) && (echo.substring(0, 6).equals("unmark"))) {
                int index = Integer.parseInt(echo.substring(7));
                index--;
                store.get(index).unmark();
                System.out.print("OK, I've marked this task as not done yet:\n" + store.get(index).toString() + "\n");
                echo = sc.nextLine();
            } else {
                store.add(new Task(echo));
                System.out.print("added:" + echo + "\n");
                echo = sc.nextLine();
            }
        }
        System.out.print("Bye, have a good day!");
    }
}