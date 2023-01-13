import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "What can I do for you?\n";
        System.out.println(greeting);

        List<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        convo: while (true) {
            System.out.print("You : ");
            String input = sc.nextLine().toLowerCase();
            switch (input) {
                case "bye":
                    System.out.println("Duke: Bye. Hope to see you again soon!");
                    break convo;
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, list.get(i)));
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("Duke: added " + input);
                    list.add(input);
                    break;
            }
        }

        sc.close();
    }
}
