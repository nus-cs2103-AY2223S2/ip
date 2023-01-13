import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line_break = " \n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^ \n";
    static ArrayList<Task> items;
    public static void main(String[] args) {
        Scanner usr_in = new Scanner(System.in);
        items = new ArrayList<>();
        greet();
        // Start user input
        while (usr_in.hasNextLine()) {
            String curr_in = usr_in.nextLine();
            String[] curr = curr_in.split(" ");
            if (curr_in.equals("bye")) {
                end();
                break;
            } else if (curr_in.equals("list")) {
                print();
            } else if (curr[0].equals("mark")) {
                mark(Integer.parseInt(curr[1]));
            } else if (curr[0].equals("unmark")) {
                unmark(Integer.parseInt(curr[1]));
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

    /*
     * adds item to items list
     * @param item a string from user input to be added to items list
     */
    static void add(String item) {
        Task task = new Task(item);
        items.add(task);
        System.out.println(line_break + " \t added: " + item + " \n " + line_break);
    }

    static void mark(int idx) {
        idx = idx - 1;
        items.get(idx).mark();
        System.out.println(line_break + "\t Great job completing your task! :)");
        System.out.println("\t\t" + items.get(idx) + line_break);
    }

    static void unmark(int idx) {
        idx = idx - 1;
        items.get(idx).unmark();
        System.out.println(line_break + "\t I see you want to redo this task...");
        System.out.println("\t\t" + items.get(idx) + line_break);
    }

    /*
     * prints out list of items
     */
    static void print() {
        System.out.println(line_break + "Here are all your tasks, good luck!");
        for (int i = 0; i < items.size(); i++) {
            Task item = items.get(i);
            System.out.println("\t " + (i + 1) + ". " + item);
        }
        System.out.println(line_break);
    }

    static void end() {
        System.out.println(line_break +
                        "\t Bye. See you next time! \n" +
                        line_break);
    }
}
