import java.util.Scanner;

public class Duke {

    public static void printer(String[] input) {
        System.out.println("\t____________________________________________________________");
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void printer(String input) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + input);
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String[] strings = { "Hello I'm Duke", "What can I do for you?" };
        printer(strings);

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            printer(userInput);
            userInput = sc.nextLine();

        }
        sc.close();
        printer("\tBye. Hope to see you again soon!");
    }
}
