import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line_break = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    static ArrayList<Task> items;
    public static void main(String[] args) {
        Scanner usr_in = new Scanner(System.in);
        items = new ArrayList<>();
        greet();
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
        // Start user input
        start(usr_in);
    }

    private static void start(Scanner usr_in) {
        while (usr_in.hasNextLine()) {
            try {
                parse_cmds(usr_in);
            } catch (Exception e) {
                System.out.println(line_break + "\t " + e.getMessage() + "\n" + line_break);
            }
        }
    }

    private static void parse_cmds(Scanner usr_in) throws Exception {
        String curr_in = usr_in.nextLine().trim();
        String[] curr = curr_in.split("/"); //split into title and time-related
        String[] curr_title = curr[0].split(" "); //split title by word
        if (curr_in.equals("bye")) {
            end();
        } else if (curr_in.equals("list")) {
            print();
        } else if (curr_title[0].equals("mark")) {
            mark(curr_title);
        } else if (curr_title[0].equals("unmark")) {
            unmark(curr_title);
        } else if (curr_title[0].equals("todo")) {
            addToDo(curr);
        } else if (curr_title[0].equals("deadline")) {
            addDeadline(curr);
        } else if (curr_title[0].equals("event")) {
            addEvent(curr);
        } else if (curr_title[0].equals("delete")) {
            deleteTask(curr_title);
        } else {
            throw new DukeException("Hmmm, I don't understand what you want to do");
        }
        writeToData("data/duke.txt", itemsToData());
    }

    public static void loadData() throws IOException {
        try {
            Scanner in = new Scanner(Paths.get("data", "duke.txt"));
            while(in.hasNextLine()) {
                String line = in.nextLine();
                parse_data(line);
            }
        } catch (IOException e) {
            File file = new File("data/duke.txt");
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            fw.close();
        }
    }

    public static void parse_data(String line) {
        String[] parts = line.split("\\|");
        if (parts[0].trim().equals("[T]")) {
            items.add(new ToDo(parts[2].trim(), parts[1].trim().equals("1")));
        } else if (parts[0].trim().equals("[D]")) {
            items.add(new Deadline(parts[2].trim(), parts[3].trim(), parts[1].trim().equals("1")));
        } else if (parts[0].trim().equals("[E]")) {
            String[] fromAndTo = parts[3].trim().split("-");
            items.add(new Event(parts[2].trim(), fromAndTo[0], fromAndTo[1], parts[1].trim().equals("1")));
        }
    }

    private static void addEvent(String[] curr) throws DukeException {
        try {
            String descr = curr[0].substring(6).trim();
            String from = curr[1].substring(5).trim();
            String to = curr[2].substring(3).trim();
            add(new Event(descr, from, to));
        } catch (Exception e) {
            throw new DukeException("You need to fill in an event with a description, from and to timing");
        }
    }

    private static void addDeadline(String[] curr) throws DukeException {
        try {
            String descr = curr[0].substring(9).trim();
            String by = curr[1].substring(3).trim();
            add(new Deadline(descr, by));
        } catch (Exception e) {
            throw new DukeException("You need to fill in a deadline with a description and by date");
        }
    }

    private static void addToDo(String[] curr) throws DukeException {
        String todo = curr[0].substring(5).trim();
        if (todo.isBlank()) {
            throw new DukeException("You need something to do");
        } else { add(new ToDo(todo)); }
    }

    static void add(Task task) {
        items.add(task);
        System.out.println(line_break + "\t Adding the task:\n\t\t" + task +
                "\n\t You now have " + items.size() + " task(s)." + line_break);
    }

    static void deleteTask(String[] curr_title) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (idx >= items.size() || idx < 0) {
                throw new DukeException();
            } else {
                System.out.println(line_break + "\t 1 less task! :)");
                System.out.println("\t\t" + items.get(idx) + "\n\tNow you have " + (items.size() - 1) + " tasks left!" + line_break);
                items.remove(idx);
            }
        } catch (Exception e) {
            throw new DukeException("Please give a valid index between 1 and " + items.size());
        }
    }

    static void mark(String[] curr_title) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (items.get(idx).isMarked()) {
                throw new AlreadyMarkedException();
            } else {
                items.get(idx).mark();
                System.out.println(line_break + "\t Great job completing your task! :)");
                System.out.println("\t\t" + items.get(idx) + line_break);
            }
        } catch (AlreadyMarkedException e) {
            throw new AlreadyMarkedException("Already Marked!");
        } catch (Exception e) {
            throw new DukeException("Please give a valid input with index between 1 and " + items.size());
        }
    }

    static void unmark(String[] curr_title) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (!items.get(idx).isMarked()) {
                throw new AlreadyUnmarkedException();
            } else {
                items.get(idx).unmark();
                System.out.println(line_break + "\t I see you want to redo this task...");
                System.out.println("\t\t" + items.get(idx) + line_break);
            }
        } catch (AlreadyUnmarkedException e) {
            throw new AlreadyUnmarkedException("Already unmarked!");
        } catch (Exception e) {
            throw new DukeException("Please give a valid input with index between 1 and " + items.size());
        }
    }

    public static void writeToData(String path, String newData) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(newData);
        fw.close();
    }

    public static String itemsToData() {
        String data = "";
        for (int i = 0; i < items.size(); i++) {
            data += items.get(i).toData();
            data += "\n";
        }
        return data;
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


    static void end() {
        System.out.println(line_break +
                        "\t Bye. See you next time! :)\n" +
                        line_break);
        System.exit(0);
    }
}
