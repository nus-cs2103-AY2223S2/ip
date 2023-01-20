import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

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
                System.out.println(String.format("%s.%s", i + 1, t));
            }
        }
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