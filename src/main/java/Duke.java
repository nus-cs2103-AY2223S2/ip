import java.util.Scanner;

public class Duke {

    public static String LOGO = "______     ______     __     __    \n" +
                                "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n" +
                                "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n" +
                                " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n" +
                                "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";

    /**
     *
     * @param cmd user command
     * @return true if program should exit
     */
    public boolean handleCommand(String cmd) {
        if (cmd.equals("bye")) {
            System.out.println("Till next time...");
            return true;
        } else {
            System.out.println(cmd);
            return false;
        }
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
