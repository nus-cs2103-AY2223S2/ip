import java.util.Scanner;

public class Duke {
    static String line_break = " \n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^ \n";
    public static void main(String[] args) {
        Scanner usr_in = new Scanner(System.in);
        greet();
        // Start user input
        while (usr_in.hasNext()) {
            String curr_in = usr_in.next();
            if (curr_in.equals("bye")) {
                end_Program();
                break;
            } else {
                echo(curr_in);
            }
        }
    }

    /*
     * Greets user on start up
     */
    private static void greet() {
        String logo = "             _____             _____   _    _              _   _ \n" +
                "     /\\     |_   _|           / ____| | |  | |     /\\     | \\ | |\n" +
                "    /  \\      | |    ______  | |      | |__| |    /  \\    |  \\| |\n" +
                "   / /\\ \\     | |   |______| | |      |  __  |   / /\\ \\   | . ` |\n" +
                "  / ____ \\   _| |_           | |____  | |  | |  / ____ \\  | |\\  |\n" +
                " /_/    \\_\\ |_____|           \\_____| |_|  |_| /_/    \\_\\ |_| \\_|\n" +
                "                                                                 \n" +
                "                                                                 ";
        System.out.println("\t Hello from\n" + logo);
        System.out.println("\t What can I do for you?" + line_break);
    }

    /*
     * Outputs an echo of user input.
     * @param in a String from user input
     */
    static void echo(String in) {
        System.out.println(line_break +
                        "\t " + in + " \n" +
                        line_break);
    }

    /*
     * Outputs simple terminating message
     */
    static void end_Program() {
        System.out.println(line_break +
                        "\t Bye. See you next time! \n" +
                        line_break);
    }
}
