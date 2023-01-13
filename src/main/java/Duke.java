import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Jarvis";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from " + BOT_NAME);

        Echo echo = new Echo(BOT_NAME);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String response = echo.onCommand(command);
            if (response == null) {
                break;
            } else {
                System.out.println("\t" + response);
            }
        }
        System.out.println("\tGoodbye!");
    }
}
