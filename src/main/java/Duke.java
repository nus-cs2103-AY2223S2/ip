import java.util.Scanner;

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
        StringBuilder sb = new StringBuilder();
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

    private Command getCommand(String input) {
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
            return Command.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Duke.welcome();
        Task[] tasks = new Task[100];
        int numTasks = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Duke.displayTasks(tasks, numTasks);
            } else if (input.matches("^mark\\s\\d+$")) {
                // We can take this exact substring because its guaranteed that input is of form "mark %d"
                int index = Integer.valueOf(input.substring(5)) - 1; // account for 1 indexing
                if (isValidMark(tasks, index)) // Note that this check also prints out error messages if any
                    Duke.printWithDecorations(tasks[index].markDone());
            } else if (input.matches("^unmark\\s\\d+$")) {
                int index = Integer.valueOf(input.substring(7)) - 1; // account for 1 indexing
                if (isValidMark(tasks, index)) // Note that this check also prints out error messages if any
                    Duke.printWithDecorations(tasks[index].unmarkDone());
            } else if (numTasks < 100) {
                Duke.printWithDecorations("added: " + input);
                tasks[numTasks] = new Task(input);
                numTasks++;
            } else {
                Duke.printWithDecorations("Not enough slots!");
            }
            input = sc.nextLine();
        }
        sc.close();
        Duke.bye();
    }
}
