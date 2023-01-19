import java.util.Scanner;

public class Duke {
    // Logo art generated on https://patorjk.com/software/taag/#p=display&f=Graffiti&t=PixlBot
    private static String logo = "__________.__       .__ __________        __   \n" +
            "\\______   \\__|__  __|  |\\______   \\ _____/  |_ \n" +
            " |     ___/  \\  \\/  /  | |    |  _//  _ \\   __\\\n" +
            " |    |   |  |>    <|  |_|    |   (  <_> )  |  \n" +
            " |____|   |__/__/\\_ \\____/______  /\\____/|__|  \n" +
            "                   \\/           \\/             ";

    private static String hline = "--------------------";

    // Color support obtained from https://stackoverflow.com/a/45444716
    private static final String PURPLE = "\033[0;35m";  // PURPLE
    private static final String RESET = "\033[0m";  // Text Reset

    private static void PixlPrint(String text) {
        System.out.println(PURPLE + "PixlBot: " + RESET + text);
        System.out.println(hline);
        System.out.println(hline);
    }

    public static void main(String[] args) {
        // Print the logo and welcome user.
        System.out.println("Welcome to\n" + logo);
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
    }
}
