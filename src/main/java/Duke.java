import java.util.Scanner;

public class Duke {

    private static String[] list = new String[Values.LISTMAX];
    private static int listIndex = 0;

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
            if (command.equals("list")) {
                String output = "List:\n";
                for (int i = 0; i < listIndex; i++) {
                    output += "\t" + (i + 1) + ". " + list[i] + "\n";
                }
                PixlPrint(output);
            } else if (listIndex < Values.LISTMAX) {
                list[listIndex] = command;
                listIndex++;
                PixlPrint("added: " + command);
            } else {
                PixlPrint("Too many list items.");
            }
            System.out.print("You: ");
            command = scanner.nextLine();
        }

        PixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
    }
}
