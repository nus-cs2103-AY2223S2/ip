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
                    //TODO do something here
                case EVENT:
                    //TODO do something here
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

                case TODO:
                    //TODO do something here
                case ADD:
                    if (numTasks < 100) {
                        Duke.printWithDecorations("added: " + input);
                        tasks[numTasks] = new Task(input);
                        numTasks++;
                    } else {
                        Duke.printWithDecorations("Not enough slots!");
                    }
                    break;
            }
            input = sc.nextLine();
        }
        sc.close();
        Duke.bye();
    }
}
