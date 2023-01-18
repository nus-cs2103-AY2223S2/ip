import java.util.Scanner;

import input.Parser;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

public class Duke {
    private static void printWithDecorations(String input) {
        System.out.println("\nOwO OwO OwO OwO OwO OwO");
        System.out.println(input);
        System.out.println("OwO OwO OwO OwO OwO OwO\n");
    }

    private static void welcome() {
        String welcomeString = 
                "Hello from\n"
                +" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        Duke.printWithDecorations(welcomeString);
    }

    private static void bye() {
        String bye = "Bye. Hope to see you again soon!";
        Duke.printWithDecorations(bye);
    }

    private static void displayTasks(Task[] tasks, int numTasks) {
        String s;

        if (numTasks <= 0) {
            s = "No task found! Please add a task.";
        } else if (numTasks == 1) {
            s = "Here is the task in your list:\n";
        } else {
            s = "Here are the tasks in your list:\n";
        }

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < numTasks; i++) {
            sb.append(String.format("%d.%s\n", i+1, tasks[i].toString()));
        }
        Duke.printWithDecorations(sb.toString());
    }

    private static boolean isValidMark(Task[] tasks, int index) {
        if (index < 0 || index >= tasks.length) {
            Duke.printWithDecorations("Index out of bounds!");
            return false;
        } else if (tasks[index] == null) {
            Duke.printWithDecorations("Task not initialised!");
            return false;
        } else {
            return true;
        }
    }

    private static void printAddedConfirmation(Task task, int numTasks) {
        String grammar = numTasks <= 1 ? "task" : "tasks";
        String s = String.format("Got it. I've added this task:\n   %s\nNow you have %d %s in the list.", task.toString(), numTasks, grammar);
        Duke.printWithDecorations(s);
    }

    private static Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.matches("^mark\\s\\d+$")) {
            return Command.MARK;
        } else if (input.matches("^unmark\\s\\d+$")) {
            return Command.UNMARK;
        } else if (input.matches("^deadline\\s.+/by\\s.+$")) {
            return Command.DEADLINE;
        } else if (input.matches("^event\\s.+/from\\s.+/to\\s.+$")) {
            return Command.EVENT;
        } else if (input.matches("^todo\\s.+$")) {
            return Command.TODO;
        } else {
            return Command.ADD;
        }
    }

    public static void main(String[] args) {
        Duke.welcome();
        Task[] tasks = new Task[100];
        int numTasks = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        while (!input.equals("bye")) {
            switch (Duke.getCommand(input)) {
                case LIST:
                    Duke.displayTasks(tasks, numTasks);
                    break;
                case DEADLINE:
                    String[] dt = Parser.parseDeadline(input);
                    Deadline dl = new Deadline(dt[0], dt[1]);
                    tasks[numTasks] = dl;
                    numTasks++;
                    Duke.printAddedConfirmation(dl, numTasks);
                    break;
                case EVENT:
                    String[] et = Parser.parseEvent(input);
                    Event e = new Event(et[0], et[1], et[2]);
                    tasks[numTasks] = e;
                    numTasks++;
                    Duke.printAddedConfirmation(e, numTasks);
                    break;
                case TODO:
                    Todo td = new Todo(Parser.parseTodo(input));
                    tasks[numTasks] = td;
                    numTasks++;
                    Duke.printAddedConfirmation(td, numTasks);
                    break;
                case ADD:
                    if (numTasks < 100) {
                        Duke.printWithDecorations("added: " + input);
                        tasks[numTasks] = new Task(input);
                        numTasks++;
                    } else {
                        Duke.printWithDecorations("Not enough slots!");
                    }
                    break;
                case MARK:
                    // We can take this exact substring because its guaranteed that input is of form "mark %d"
                    int markIndex = Integer.valueOf(input.substring(5)) - 1; // account for 1 indexing
                    if (isValidMark(tasks, markIndex)) // Note that this check also prints out error messages if any
                        Duke.printWithDecorations(tasks[markIndex].markDone());
                    break;
                case UNMARK:
                    int unmarkIndex = Integer.valueOf(input.substring(7)) - 1; // account for 1 indexing
                    if (isValidMark(tasks, unmarkIndex)) // Note that this check also prints out error messages if any
                        Duke.printWithDecorations(tasks[unmarkIndex].unmarkDone());
                    break;
            }
            input = sc.nextLine();
        }
        sc.close();
        Duke.bye();
    }
}