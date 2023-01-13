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

        Scanner sc = new Scanner(System.in);

        convo: while (true) {
            System.out.print("You : ");
            String input = sc.nextLine().toLowerCase();
            switch (input) {
                case "bye":
                    System.out.println("Duke: Bye. Hope to see you again soon!");
                    break convo;
                case "":
                    break;
                default:
                    System.out.println("Duke: " + input);
                    break;
            }
        }

        sc.close();
    }
}
