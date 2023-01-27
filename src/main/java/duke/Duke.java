package duke;
import java.util.Scanner;

/**
 * Main Duke Class
 */
public class Duke {
    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        Ui.intro();
        Scanner scan = new Scanner(System.in);
        TaskList tasks = new TaskList();

        String input = scan.nextLine();
        while (!input.equals("bye")) {
            try {
                Parser.parseRawString(input, tasks);
            } catch (IllegalArgumentException exception) {
                Ui.invalidCommand();
            } catch (IndexOutOfBoundsException exception) {
                Ui.missingArgs();
            }
            input = scan.nextLine();
        }
        Ui.close();
        scan.close();
    }
}
