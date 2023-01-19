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

    public static void main(String[] args) {
        System.out.println("Welcome to\n" + logo);
        System.out.println("Enter a command to start:\n");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("\t" + command);
            System.out.println(hline);
            command = scanner.nextLine();
        }

        System.out.println("Goodbye!");
        System.out.println(hline + "\n" + hline);
    }
}
