import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(greeting());

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            String output = echo(userInput);
            System.out.println(output);
            userInput = sc.nextLine();
        }

        System.out.println(ending());
    }

    public static String echo(String input) {
        return input;
    }

    public static String ending() {
        return "Bye~ Hope to see you again soon:)";
    }

    public static String greeting() {
        return "Hi~ I'm Duke>_< \nWhat can I do for you?";
    }
}
