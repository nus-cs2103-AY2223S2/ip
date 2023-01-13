import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line_break = " \n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^ \n";
    static ArrayList<String> items;
    public static void main(String[] args) {
        Scanner usr_in = new Scanner(System.in);
        items = new ArrayList<>();
        greet();
        // Start user input
        while (usr_in.hasNextLine()) {
            String curr_in = usr_in.nextLine();
            if (curr_in.equals("bye")) {
                end();
                break;
            } else if (curr_in.equals("list")) {
                print();
            } else {
                //echo(curr_in);
                add(curr_in);
            }
        }
    }

    private static void greet() {
        String logo = "             _____             _____   _    _              _   _ \n" +
                "     /\\     |_   _|           / ____| | |  | |     /\\     | \\ | |\n" +
                "    /  \\      | |    ______  | |      | |__| |    /  \\    |  \\| |\n" +
                "   / /\\ \\     | |   |______| | |      |  __  |   / /\\ \\   | . ` |\n" +
                "  / ____ \\   _| |_           | |____  | |  | |  / ____ \\  | |\\  |\n" +
                " /_/    \\_\\ |_____|           \\_____| |_|  |_| /_/    \\_\\ |_| \\_|\n" +
                "                                                                 \n" +
                "                                                                 ";
        System.out.println("\t Hello I'm\n" + logo);
        System.out.println("\t What can I do for you?" + line_break);
    }

//    /*
//     * Outputs an echo of user input.
//     * @param in a String from user input
//     */
//    static void echo(String in) {
//        System.out.println(line_break +
//                        "\t " + in + " \n" +
//                        line_break);
//    }

    /*
     * adds item to items list
     * @param item a string from user input to be added to items list
     */
    static void add(String item) {
        items.add(item);
        System.out.printf("%s \t added: %s \n %s", line_break, item, line_break);
    }

    /*
     * prints out list of items
     */
    static void print() {
        System.out.println(line_break);
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            System.out.printf("\t %d. %s\n", i + 1, item);
        }
        System.out.println(line_break);
    }

    static void end() {
        System.out.println(line_break +
                        "\t Bye. See you next time! \n" +
                        line_break);
    }
}
