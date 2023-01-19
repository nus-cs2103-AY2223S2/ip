import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static String horizontalLine = "************************";
    private static String[] storage = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(horizontalLine);
        System.out.println("HELLO! I'M DUKE!");
        System.out.println("HOW CAN I HELP?");
        System.out.println(horizontalLine);
        readInput();
    }

    private static void readInput() {
        String input = scanner.nextLine();

        if (input.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println("BYE!");
            System.out.println(horizontalLine);
        } else if (input.equals("list")) {
            System.out.println(horizontalLine);
            for (int i = 0; i < count; i++) {
                System.out.println("" + (i+1) + ". " + storage[i]);
            }
            System.out.println(horizontalLine);
            readInput();
        } else {
            System.out.println(horizontalLine);
            System.out.println("added: " + input);
            storage[count] = input;
            count++;
            System.out.println(horizontalLine);
            readInput();
        }
    }

}
