import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    static final String dirPath = "./data/";
    static final String filePath = dirPath + "tasks.txt";

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static ArrayList<Task> loadData() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");
                if (line[0].equals("todo")) {
                    ToDo t = new ToDo(sc.nextLine());
                    if (line[1].equals("true")) {
                        t.mark();
                    }
                    tasks.add(t);
                } else if (line[0].equals("deadline")) {
                    Deadline d = new Deadline(sc.nextLine(), line[2], line[3]);
                    if (line[1].equals("true")) {
                        d.mark();
                    }
                    tasks.add(d);
                } else { // event
                    Event e = new Event(sc.nextLine(), line[2], line[3], line[4], line[5]);
                    if (line[1].equals("true")) {
                        e.mark();
                    }
                    tasks.add(e);
                }
            }
            System.out.println("I'm so happy we're meeting again!");
        } catch (FileNotFoundException err) {
            System.out.println("I don't seem to know anything about you! First time meeting? :D");
        }
        return tasks;
    }

    static String generateDetailsToSave(ArrayList<Task> tasks) {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            str += t.getDetailsToSave();
            if (i != tasks.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }

    static void saveTasks(ArrayList<Task> tasks) {

        File file = new File(filePath);
        File dir = new File(dirPath);

        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        try {
            writeToFile(generateDetailsToSave(tasks));
        } catch (IOException err) {
            System.out.println("Something went wrong when trying to save your list!");
            System.out.println(err.getMessage());
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException, IOException {

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = loadData();

        while (true) {
            String input = sc.nextLine();
            String[] chunked = input.split(" ");
            // code for abstraction + error handling adapted from EvitanRelta's comment:
            // https://github.com/nus-cs2103-AY2223S2/forum/issues/20#issuecomment-1396557797
            try {
                if (input.equals("bye")) {
                    saveTasks(tasks);
                    Duke.quit();
                    break;
                }
                System.out.println("(\\ (\\\n" +
                        "(„• ֊ •„) ♡\n" +
                        "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                if (chunked[0].equals("mark")) {
                    if (chunked.length == 1) {
                        throw new DukeInvalidCommandException("Huh? You didn't give me a task to mark!");
                    } else {
                        Duke.mark(tasks, Integer.parseInt(chunked[1]));
                        saveTasks(tasks);
                    }
                } else if (chunked[0].equals("unmark")) {
                    if (chunked.length == 1) {
                        throw new DukeInvalidCommandException("Huh? You didn't give me a task to unmark!");
                    } else {
                        Duke.unmark(tasks, Integer.parseInt(chunked[1]));
                        saveTasks(tasks);
                    }
                } else if (chunked[0].equals("delete")) {
                    if (chunked.length == 1) {
                        throw new DukeInvalidCommandException("Huh? You didn't give me a task to delete!");
                    } else {
                        Duke.delete(tasks, Integer.parseInt(chunked[1]));
                        saveTasks(tasks);
                    }
                } else if (input.equals("list")) {
                    Duke.list(tasks);
                } else if (chunked[0].equals("todo")) {
                    Duke.addToDo(tasks, input);
                    saveTasks(tasks);
                } else if (chunked[0].equals("deadline")) {
                    Duke.addDeadline(tasks, input);
                    saveTasks(tasks);
                } else if (chunked[0].equals("event")) {
                    Duke.addEvent(tasks, input);
                    saveTasks(tasks);
                } else {
                    throw new DukeInvalidCommandException("Huh? Sorry, I don't know what this means :(");
                }
            }
            catch (DukeException err) {
                System.out.println(err.getMessage());
            }
        }

        sc.close();

    }

    static void quit() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    static void mark(ArrayList<Task> tasks, int num) throws DukeException {
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.get(num - 1);
            if (t.isDone()) {
                throw new DukeInvalidArgumentException("Huh? You've already done this task!");
            } else {
                t.mark();
                System.out.println("Okie! I've marked this task as done:");
                System.out.println(t);
            }
        }
    }

    static void unmark(ArrayList<Task> tasks, int num) throws DukeException {
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.get(num - 1);
            if (!t.isDone()) {
                throw new DukeInvalidArgumentException("Huh? You haven't even done this task!");
            } else {
                t.unmark();
                System.out.println("Okie! I've marked this task as not done yet:");
                System.out.println(t);
            }
        }
    }

    static void delete(ArrayList<Task> tasks, int num) throws DukeException {
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.get(num - 1);
            tasks.remove(num - 1);
            System.out.println("Okie! I've removed this task:");
            System.out.println(t);
            if (tasks.size() == 1) { // grammar
                System.out.println("Now you have 1 task on your list.");
            } else {
                System.out.println(String.format("Now you have %s tasks on your list.", tasks.size()));
            }
        }
    }

    static void list(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is currently empty!");
        } else {
            System.out.println("Here are all the things on your list!");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println(String.format("%s. %s", i + 1, t));
            }
        }
    }

    static void addToDo(ArrayList<Task> tasks, String input) throws DukeException {
        String rest = input.substring(4).trim();
        if (rest.isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
        } else {
            Duke.addTask(tasks, new ToDo(rest));
        }
    }

    static void addDeadline(ArrayList<Task> tasks, String input) throws DukeException {
        String rest = input.substring(8).trim();
        if (rest.isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
        }
        String[] d = rest.split(" /by ");
        if (d.length != 2) {
            throw new DukeInvalidArgumentException("Hmm! You're missing a deadline D:");
        }
        String[] datetime = d[1].split(" ");
        if (datetime.length != 2) {
            throw new DukeInvalidArgumentException("Hmm! You're missing either a date or a time D:");
        }
        String date = datetime[0];
        String[] ddmmyyyy = date.split("/");
        if (ddmmyyyy.length != 3) {
            throw new DukeInvalidArgumentException("Hey, get your date format right!");
        }
        String time = datetime[1];
        if (time.length() != 4) {
            throw new DukeInvalidArgumentException("Hey, get your time format right!");
        }
        Duke.addTask(tasks, new Deadline(d[0], date, time));
    }

    static void addEvent(ArrayList<Task> tasks, String input) throws DukeException {

        String rest = input.substring(5).trim();

        if (rest.isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
        }
        String[] d = rest.split(" /from ");
        if (d.length != 2) {
            throw new DukeInvalidArgumentException("Hmm! You're missing a from parameter D:");
        }
        String[] fromTo = d[1].split(" /to ");
        if (fromTo.length != 2) {
            throw new DukeInvalidArgumentException("Hmm! You're missing a to parameter D:");
        }

        String from = fromTo[0];
        String to = fromTo[1];

        String[] datetimeFrom = from.split(" ");
        if (datetimeFrom.length != 2) {
            throw new DukeInvalidArgumentException("Hmm! You're missing either a from date or a from time D:");
        }

        String[] datetimeTo = to.split(" ");
        if (datetimeTo.length != 2) {
            throw new DukeInvalidArgumentException("Hmm! You're missing either a to date or a to time D:");
        }

        String[] dateFrom = datetimeFrom[0].split("/");
        if (dateFrom.length != 3) {
            throw new DukeInvalidArgumentException("Hey, get your from date format right!");
        }
        String timeFrom = datetimeFrom[1];
        if (timeFrom.length() != 4) {
            throw new DukeInvalidArgumentException("Hey, get your from time format right!");
        }

        String[] dateTo = datetimeTo[0].split("/");
        if (dateTo.length != 3) {
            throw new DukeInvalidArgumentException("Hey, get your to date format right!");
        }
        String timeTo = datetimeTo[1];
        if (timeTo.length() != 4) {
            throw new DukeInvalidArgumentException("Hey, get your to time format right!");
        }

        Duke.addTask(tasks, new Event(d[0], datetimeFrom[0], timeFrom, datetimeTo[0], timeTo));
    }

    static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
        System.out.println("Alright! I've added this task:");
        System.out.println(t);
        if (tasks.size() == 1) { // grammar
            System.out.println("Now you have 1 task on your list.");
        } else {
            System.out.println(String.format("Now you have %s tasks on your list.", tasks.size()));
        }
    }
}