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
            else if (!s.equals("list")) {
                System.out.println(String.format("added: %s", s));
                listOfAction[i] = String.format("%d. %s", i + 1, s);
                i++;
            }
            else {
                for (int j = 0; j < listOfAction.length; j ++) {
                    if (listOfAction[j] == null) {
                        break;
                    }
                    System.out.println(listOfAction[j]);
                }
            }
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
