import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Default welcome message and message border
        String border = "_______________________________\n";
        System.out.println(border + "Sup, Duke here.\nWhat do you want from me?\n" + border);

        // while LoopEnd = true loop to accept user input
        boolean LoopEnd = false;
        while (LoopEnd == false) {
            Scanner UserScan = new Scanner(System.in);
            String UserInput = UserScan.nextLine();

            // switch case for future commands
            switch (UserInput) {

                // loop breaks, ending program if input is "bye"
                case ("bye"):
                    System.out.println(border + "Goodbye, then!\n" + border);
                    LoopEnd = true;
                    break;

                // Duke repeats input by default
                default:
                    System.out.println(border + UserInput + "\n" + border);
            }
        }
    }
}
