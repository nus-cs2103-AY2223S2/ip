import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final int MAX_TASKS_SIZE = 100;
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS_SIZE);
    private static Pattern todoCommandPattern = Pattern.compile("^todo (.*)$");
    private static Pattern deadlineCommandPattern = Pattern.compile("^deadline (.*) \\/by (.*)$");
    private static Pattern eventCommandPattern = Pattern.compile("^event (.*) \\/from (.*) \\/to (.*)$");
    private static Pattern markUnmarkCommandPattern = Pattern.compile("^(mark|unmark) ([0-9]+)$");

    private static void printText(String text) {
        System.out.printf("     %s\n", text);
    }

    private static void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printStartup() {
        String logo =
                " /\\_/\\\n" +
                        "( o.o )   ~meow~\n" +
                        " > ^ <";
        System.out.println(logo);
        printHorizontal();
        printText("Hello! I'm Duke");
        printText("What can I do for you?");
        printHorizontal();
    }

    private static void commandTodo(String input) throws DukeException {
        Matcher todoCommandMatcher = todoCommandPattern.matcher(input);
        if (todoCommandMatcher.find()) {
            String description = todoCommandMatcher.group(1);
            addTask(new ToDo(description));
        } else {
            throw new DukeException("You are missing the description.\n     It's 'todo <description>'.");
        }
    }

    private static void commandDeadline(String input) throws DukeException {
        Matcher deadlineCommandMatcher = deadlineCommandPattern.matcher(input);
        if (deadlineCommandMatcher.find()) {
            String description = deadlineCommandMatcher.group(1);
            String by = deadlineCommandMatcher.group(2);

            addTask(new Deadline(description, by));
        } else {
            throw new DukeException("You are missing either the description or deadline.\n     It's 'deadline <description> /by <deadline>'.");
        }
    }

    private static void commandEvent(String input) throws DukeException {
        Matcher eventCommandMatcher = eventCommandPattern.matcher(input);
        if (eventCommandMatcher.find()) {
            String description = eventCommandMatcher.group(1);
            String from = eventCommandMatcher.group(2);
            String to = eventCommandMatcher.group(3);

            addTask(new Event(description, from, to));
        } else {
            throw new DukeException("You are missing either the description, from or to.\n     It's 'event <description> /from <from> /to <to>'.");
        }
    }

    private static void addTask(Task task) {
        printHorizontal();
        if (tasks.size() == MAX_TASKS_SIZE) {
            printText("Task list is full!");
        } else {
            tasks.add(task);
            printText("Got it. I've added this task:");
            printText(String.format("  %s", task.toString()));
            printText(String.format("Now you have %d tasks in the list.", tasks.size()));
        }
        printHorizontal();
    }

    private static void commandListTasks() {
        printHorizontal();
        printText("Here are the tasks in your list:");
        for (int index = 0; index < tasks.size(); index++) {
            printText(String.format("%d. %s", index + 1, tasks.get(index).toString()));
        }
        printHorizontal();
    }

    private static void commandMark(String input) throws DukeException {
        Matcher markCommandMatcher = markUnmarkCommandPattern.matcher(input);
        if (markCommandMatcher.find()) {
            int taskNo = Integer.parseInt(markCommandMatcher.group(2));

            if (taskNo > 0 && taskNo <= tasks.size()) {
                Task task = tasks.get(taskNo - 1);
                task.setIsDone(true);

                printHorizontal();
                printText("Nice! I've marked this task as done:");
                printText(task.toString());
                printHorizontal();
            } else {
                printHorizontal();
                printText("Invalid task number!");
                printHorizontal();
            }
        } else {
            throw new DukeException("Please enter a proper integer.");
        }
    }

    private static void commandUnmark(String input) throws DukeException {
        Matcher markCommandMatcher = markUnmarkCommandPattern.matcher(input);
        if (markCommandMatcher.find()) {
            int taskNo = Integer.parseInt(markCommandMatcher.group(2));

            if (taskNo > 0 && taskNo <= tasks.size()) {
                Task task = tasks.get(taskNo - 1);
                task.setIsDone(false);

                printHorizontal();
                printText("OK, I've marked this task as not done yet:");
                printText(task.toString());
                printHorizontal();
            } else {
                printHorizontal();
                printText("Invalid task number!");
                printHorizontal();
            }
        } else {
            throw new DukeException("Please enter a proper integer.");
        }
    }

    private static void commandExit() {
        printHorizontal();
        printText("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void main(String[] args) {
        printStartup();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

            String command = input.split(" ")[0];

            // Try executing command from input
            try {
                if (command.equals("todo")) {
                    commandTodo(input);
                } else if (command.equals("deadline")) {
                    commandDeadline(input);
                } else if (command.equals("event")) {
                    commandEvent(input);
                } else if (command.equals("list")) {
                    commandListTasks();
                } else if (command.equals("mark")) {
                    commandMark(input);
                } else if (command.equals("unmark")) {
                    commandUnmark(input);
                } else if (command.equals("bye")) {
                    commandExit();
                    isExit = true;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException exception) {
                printHorizontal();
                printText(exception.getMessage());
                printHorizontal();
            }
        }
    }
}
