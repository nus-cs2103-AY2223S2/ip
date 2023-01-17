import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String LOGO = "______     ______     __     __    \n" +
                                "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n" +
                                "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n" +
                                " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n" +
                                "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";

    private final ArrayList<String> inputStore = new ArrayList<>();

    /**
     * Accepts a string that represents the user command, returns a boolean to
     * determine if the program should terminate immediately
     * @param cmd user command
     * @return true if program should exit
     */
    public boolean handleCommand(String cmd) {
        if (cmd.equals("bye")) {
            System.out.println("Till next time...");
            return true;
        } else if (cmd.equals("list")) {
            for (int i = 0; i < inputStore.size(); i++) {
                System.out.printf("%d. %s\n", i, inputStore.get(i));
            }
        } else {
            inputStore.add(cmd);
            System.out.printf("added: %s\n", cmd);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println("Hello, I am \n" + Duke.LOGO);
        System.out.println("How shall I assist you today?");

        boolean toExit = false;
        while (!toExit) {
            System.out.print("\n:> ");
            String cmd = scanner.nextLine();
            toExit = duke.handleCommand(cmd);
        }
    }
}
