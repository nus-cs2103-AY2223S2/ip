package output;

import task.Task;

public class Printer {
    public static void printWithDecorations(String input) {
        System.out.println("\nOwO OwO OwO OwO OwO OwO");
        System.out.println(input);
        System.out.println("OwO OwO OwO OwO OwO OwO\n");
    }

    public static void printWelcome() {
        String welcomeString = 
                "Hello from\n"
                +" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        printWithDecorations(welcomeString);
    }

    public static void printBye() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDecorations(bye);
    }

    public static void printTasks(Task[] tasks, int numTasks) {
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
        printWithDecorations(sb.toString());
    }

    public static void printAddedConfirmation(Task task, int numTasks) {
        String grammar = numTasks <= 1 ? "task" : "tasks";
        String s = String.format("Got it. I've added this task:\n   %s\nNow you have %d %s in the list.", task.toString(), numTasks, grammar);
        printWithDecorations(s);
    }
    
}
