import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            // sanitize the input
            input.trim();
            if (input.equals("bye")) {
                System.out.println('\t' + "Bye, hope to see you again!");
                break;
            } else {
                System.out.println('\t' + input);
            }
        }

    }
}
