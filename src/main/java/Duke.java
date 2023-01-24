import java.util.Scanner;
import javafx.stage.Stage;
import java.io.PrintWriter;
public class Duke {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);




        horizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello there! I am: \n" + logo + "\nWhat can I help you with!");
        System.out.println("You can let me know by typing it below, please!");
        horizontalLine();

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            horizontalLine();
            System.out.println("    " + userInput);
            horizontalLine();
            userInput = scanner.nextLine();
        }

        System.out.println("Bye for now! Hope to see you again!");
    }

    public static void horizontalLine() {


        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }


        System.out.print("\n");

    }
}
