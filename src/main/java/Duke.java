import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static ArrayList<Task> loadData(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(file);

            // very messy, need to fix way of creating tasks!!
            // don't pass input string into constructor. settle outside first
            while (sc.hasNext()) {
                String line = sc.nextLine();
                char taskType = line.charAt(line.indexOf(". [") + 3);
                String fullDesc = line.substring(line.indexOf("] ") + 2);
                if (taskType == 'T') {
                    tasks.add(new ToDo(fullDesc));
                } else if (taskType =='D') {
                    String desc = fullDesc.substring(0, fullDesc.indexOf(" ("));
                    String deadline = fullDesc.substring(fullDesc.indexOf("by: ") + 4, fullDesc.length() - 1);
                    tasks.add(new Deadline(desc + " /by " + deadline));
                } else { // taskType == 'E'
                    String desc = fullDesc.substring(0, fullDesc.indexOf(" ("));
                    String start = fullDesc.substring(fullDesc.indexOf("from: ") + 6, fullDesc.indexOf(" to:"));
                    String end = fullDesc.substring(fullDesc.indexOf("to: ") + 4, fullDesc.length() - 1);
                    tasks.add(new Event(desc + " /from " + start + " /to " + end));
                }
            }
            System.out.println("I'm so happy we're meeting again!");
        } catch (FileNotFoundException err) {
            System.out.println("I don't seem to know anything about you! First time meeting? :D");
        }
        return tasks;
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {

        String dirPath = "./data/";
        String filePath = dirPath + "tasks.txt";

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = loadData(filePath);

        while (true) {
            String input = sc.nextLine();
            String[] chunked = input.split(" ");
            // code for abstraction + error handling adapted from EvitanRelta's comment:
            // https://github.com/nus-cs2103-AY2223S2/forum/issues/20#issuecomment-1396557797
            try {
                if (input.equals("bye")) {
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
                    }
                } else if (chunked[0].equals("unmark")) {
                    if (chunked.length == 1) {
                        throw new DukeInvalidCommandException("Huh? You didn't give me a task to unmark!");
                    } else {
                        Duke.unmark(tasks, Integer.parseInt(chunked[1]));
                    }
                } else if (chunked[0].equals("delete")) {
                    if (chunked.length == 1) {
                        throw new DukeInvalidCommandException("Huh? You didn't give me a task to delete!");
                    } else {
                        Duke.delete(tasks, Integer.parseInt(chunked[1]));
                    }
                } else if (input.equals("list")) {
                    Duke.list(tasks);
                } else if (chunked[0].equals("todo")) {
                    Duke.addToDo(tasks, input);
                } else if (chunked[0].equals("deadline")) {
                    Duke.addDeadline(tasks, input);
                } else if (chunked[0].equals("event")) {
                    Duke.addEvent(tasks, input);
                } else {
                    throw new DukeInvalidCommandException("Huh? Sorry, I don't know what this means :(");
                }
            }
            catch (DukeException err) {
                System.out.println(err.getMessage());
            }
        }

        sc.close();

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
            writeToFile(filePath, generateList(tasks));
        } catch (IOException err) {
            System.out.println("Something went wrong when trying to save your list!");
            System.out.println(err.getMessage());
        }

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
            System.out.println(generateList(tasks));
        }
    }

    static String generateList(ArrayList<Task> tasks) {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            str += String.format("%s. %s", i + 1, t);
            if (i != tasks.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }

    static void addToDo(ArrayList<Task> tasks, String input) throws DukeException {
        String rest = input.substring(4);
        if (rest.isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
        } else {
            Duke.addTask(tasks, new ToDo(input.substring(5)));
        }
    }

    static void addDeadline(ArrayList<Task> tasks, String input) throws DukeException {
        // todo: add datetime input validation, should be date format
        String rest = input.substring(8);
        if (rest.isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
        } else if (!rest.contains("/by") || rest.substring(rest.indexOf("/by") + 3).isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't mention a deadline D:");
        } else {
            Duke.addTask(tasks, new Deadline(input.substring(9)));
        }
    }

    static void addEvent(ArrayList<Task> tasks, String input) throws DukeException {
        // to clean up
        // todo: add datetime input validation, should be date format
        String rest = input.substring(8);
        if (rest.isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
        } else if (!rest.contains("/from") || (rest.contains("/to") &&
                rest.substring(rest.indexOf("/from") + 5, rest.indexOf("/to")).isEmpty()) ||
                rest.substring(rest.indexOf("/from") + 5).isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't mention a start time D:");
        } else if (!rest.contains("/to") || rest.substring(rest.indexOf("/to") + 3).isEmpty()) {
            throw new DukeInvalidArgumentException("Hey! You didn't mention an end time D:");
        } else {
            Duke.addTask(tasks, new Event(input.substring(6)));
        }
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