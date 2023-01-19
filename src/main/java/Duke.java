import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String[] listOfAction = new String[100];
        System.out.println("\nHello, I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String s = "";
        int i = 0;
        while (!bye.equals(s)) {
            s = sc.next();
            if (s.equals("bye")) {
                break;
            }
            else if ((!s.equals("list")) && !s.equals("mark") && !s.equals("unmark")) {
                System.out.println(String.format("added: %s", s));
                listOfAction[i] = String.format("[ ] %s", s);
                i++;
            }
            else if (s.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int j = 0; j < listOfAction.length; j ++) {
                    if (listOfAction[j] == null) {
                        break;
                    }
                    System.out.println(String.format("%d.%s", j + 1,listOfAction[j]));
                }
            } else if (s.equals("mark")) {
                int num = Integer.parseInt(sc.next()) - 1;
                if (listOfAction[num] != null) {
                    System.out.println("Nice! I've marked this task as done:");
                    String original = listOfAction[num];
                    listOfAction[num] = String.format("[X] %s", original.substring(4));
                    System.out.println(listOfAction[num]);
                }
            } else if (s.equals("unmark")) {
                int num = Integer.parseInt(sc.next()) - 1;
                if (listOfAction[num] != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    String original = listOfAction[num];
                    listOfAction[num] = String.format("[ ] %s", original.substring(4));
                    System.out.println(listOfAction[num]);
                }
            }
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
