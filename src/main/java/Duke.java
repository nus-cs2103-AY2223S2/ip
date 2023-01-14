import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line_break = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    static ArrayList<Task> items;
    public static void main(String[] args) {
        Scanner usr_in = new Scanner(System.in);
        items = new ArrayList<>();
        greet();
        // Start user input
        start(usr_in);
    }

    private static void start(Scanner usr_in) {
        while (usr_in.hasNextLine()) {
            try {
               parse_cmds(usr_in);
            } catch (DukeException e) {
                System.out.println(line_break + "\t " + e.getMessage() + "\n" + line_break);
            }
        }
    }

    private static void parse_cmds(Scanner usr_in) throws DukeException {
        String curr_in = usr_in.nextLine().trim();
        String[] curr = curr_in.split("/"); //split into title and time-related
        String[] curr_title = curr[0].split(" "); //split title by word
        if (curr_in.equals("bye")) {
            end();
        } else if (curr_in.equals("list")) {
            print();
        } else if (curr_title[0].equals("mark")) {
            try {
                mark(Integer.parseInt(curr_title[1]));
            } catch (AlreadyMarkedException e) {
                throw new DukeException(e.getMessage());
            } catch (Exception e) {
                throw new DukeException("Please give a valid index between 1 and " + items.size());
            }
        } else if (curr_title[0].equals("unmark")) {
            try {
                unmark(Integer.parseInt(curr_title[1]));
            } catch (AlreadyUnmarkedException e) {
                throw new DukeException(e.getMessage());
            } catch (Exception e) {
                throw new DukeException("Please give a valid index between 1 and " + items.size());
            }
        } else if (curr_title[0].equals("todo")) {
            String todo = curr[0].substring(5).trim();
            if (todo.isBlank()) {
                throw new DukeException("You need something to do");
            } else { add(new ToDo(todo)); }
        } else if (curr_title[0].equals("deadline")) {
            try {
                String descr = curr[0].substring(9).trim();
                String by = curr[1].substring(3).trim();
                add(new Deadline(descr, by));
            } catch (Exception e) {
                throw new DukeException("You need to fill in a deadline with a description and by date");
            }
        } else if (curr_title[0].equals("event")) {
            try {
                String descr = curr[0].substring(6).trim();
                String from = curr[1].substring(5).trim();
                String to = curr[2].substring(3).trim();
                add(new Event(descr, from, to));
            } catch (Exception e) {
                throw new DukeException("You need to fill in an event with a description, from and to timing");
            }
        } else {
            throw new DukeException("Hmmm, I don't understand what you want to do");
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

    static void add(Task task) {
        items.add(task);
        System.out.println(line_break + "\t Adding the task:\n\t\t" + task +
                            "\n\t You now have " + items.size() + " task(s)." + line_break);
    }

    static void mark(int idx) throws AlreadyMarkedException {
        idx = idx - 1;
        if (items.get(idx).isMarked()) {
            throw new AlreadyMarkedException("Already Marked!");
        } else {
            items.get(idx).mark();
            System.out.println(line_break + "\t Great job completing your task! :)");
            System.out.println("\t\t" + items.get(idx) + line_break);
        }
    }

    static void unmark(int idx) throws AlreadyUnmarkedException {
        idx = idx - 1;
        if (!items.get(idx).isMarked()) {
            throw new AlreadyUnmarkedException("Already Unmarked!");
        } else {
            items.get(idx).unmark();
            System.out.println(line_break + "\t I see you want to redo this task...");
            System.out.println("\t\t" + items.get(idx) + line_break);
        }
    }

    /*
     * prints out list of items
     */
    static void print() {
        System.out.println(line_break + "\tHere are all your tasks, good luck!");
        for (int i = 0; i < items.size(); i++) {
            Task item = items.get(i);
            System.out.println("\t " + (i + 1) + ". " + item);
        }
        System.out.println(line_break);
    }

    static void end() {
        System.out.println(line_break +
                        "\t Bye. See you next time! :)\n" +
                        line_break);
        System.exit(0);
    }
}
