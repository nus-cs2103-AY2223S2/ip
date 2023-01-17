import java.util.Scanner;
import task.Task;

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
            String icon = tasks[i].getStatusIcon();
            String name = tasks[i].getTaskName();
            sb.append(String.format("%d.%s %s\n",i+1,icon,name));
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
