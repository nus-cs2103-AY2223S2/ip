import java.util.Scanner;

public class Duke {

    private static void PixlPrint(String text) {
        System.out.println(Values.PURPLE + "PixlBot: " + Values.RESET + text);
        System.out.println(Values.HLINE);
    }

    public static void main(String[] args) {
        // Print the logo and welcome user.
        System.out.println("Welcome to\n" + Values.LOGO);
        System.out.println("Enter a command to start.\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("You: ");
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            PixlPrint(command);
            System.out.print("You: ");
            command = scanner.nextLine();
        }

        PixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
    }
}
