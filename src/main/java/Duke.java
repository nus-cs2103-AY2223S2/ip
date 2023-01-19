import java.util.Scanner;

/** Duke chat bot.
 * @author Hee Jia Yuan
 */
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "How may I be of assistance today? :)");
        String divider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String userInput = sc.next();
            if (userInput.equals("bye")) {
                System.out.println(divider + "\n" + "Goodbye! Have a nice day ahead. " + "\n" + divider);
                break;
            } else System.out.println(divider + "\n" + userInput + "\n" + divider);
        }
    }
}
