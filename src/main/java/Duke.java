import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    private static final int MAX_TASKS_SIZE = 100;
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS_SIZE);

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

    private static void commandTodo(String description) {
        addTask(new ToDo(description));
    }

    private static void commandDeadline(String description, String deadline) {
        addTask(new Deadline(description, deadline));
    }

    private static void commandEvent(String description, String from, String to) {
        addTask(new Event(description, from, to));
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

    private static void commandMark(int taskNo) {
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
    }

    private static void commandUnmark(int taskNo) {
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
    }

    private static void commandExit() {
        printHorizontal();
        printText("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void main(String[] args) {
        printStartup();

        Pattern commandPattern = Pattern.compile("^[a-zA-Z]+");
        Pattern todoCommandPattern = Pattern.compile("^todo (.*)$");
        Pattern deadlineCommandPattern = Pattern.compile("^deadline (.*) \\/by (.*)$");
        Pattern eventCommandPattern = Pattern.compile("^event (.*) \\/from (.*) \\/to (.*)$");
        Pattern markUnmarkCommandPattern = Pattern.compile("^(mark|unmark) ([0-9]+)$");

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

            Matcher commandMatcher = commandPattern.matcher(input);
            if (!commandMatcher.find()) {
                continue;
            }
            String command = commandMatcher.group(0);

            if (command.equals("todo")) {
                Matcher todoCommandMatcher = todoCommandPattern.matcher(input);
                if (todoCommandMatcher.find()) {
                    String description = todoCommandMatcher.group(1);

                    commandTodo(description);
                }
            } else if (command.equals("deadline")) {
                Matcher deadlineCommandMatcher = deadlineCommandPattern.matcher(input);
                if (deadlineCommandMatcher.find()) {
                    String description = deadlineCommandMatcher.group(1);
                    String by = deadlineCommandMatcher.group(2);

                    commandDeadline(description, by);
                }
            } else if (command.equals("event")) {
                Matcher eventCommandMatcher = eventCommandPattern.matcher(input);
                if (eventCommandMatcher.find()) {
                    String description = eventCommandMatcher.group(1);
                    String from = eventCommandMatcher.group(2);
                    String to = eventCommandMatcher.group(3);

                    commandEvent(description, from, to);
                }
            } else if (command.equals("list")) {
                commandListTasks();
            } else if (command.equals("mark")) {
                Matcher markCommandMatcher = markUnmarkCommandPattern.matcher(input);
                if (markCommandMatcher.find()) {
                    int taskNumber = Integer.parseInt(markCommandMatcher.group(2));

                    commandMark(taskNumber);
                }
            } else if (command.equals("unmark")) {
                Matcher unmarkCommandMatcher = markUnmarkCommandPattern.matcher(input);
                if (unmarkCommandMatcher.find()) {
                    int taskNumber = Integer.parseInt(unmarkCommandMatcher.group(2));

                    commandUnmark(taskNumber);
                }
            } else if (command.equals("bye")) {
                commandExit();
                isExit = true;
            }
        }
    }
}
