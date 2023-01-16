import java.util.Scanner;

public class Duke {
    private static final int INDENT_LEVEL = 4;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Duke.say("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        whileLoop:
        while (true) {
            String input = scanner.nextLine();
        
            switch (input) {
                case "quit":
                case "exit":
                case "bye":
                    Duke.say("Bye. Hope to see you again soon!");
                    break whileLoop;
                default:
                    Duke.say(input);
                    break;
            }
        }
        
        scanner.close();
    }

    private static void say(String whatToSay) {
        String indentation = " ".repeat(Duke.INDENT_LEVEL);
        String horizontalLine = "_".repeat(60);
        String indentedInput = whatToSay.replaceAll("(?<=^|\n)", indentation);
        
        System.out.println(indentation + horizontalLine);
        System.out.println(indentedInput);
        System.out.println(indentation + horizontalLine + '\n');
    }
}
