import java.util.Scanner;

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

    public static void main(String[] args) {
        Duke.welcome();

        Task[] tasks = new Task[100];
        int numTasks = 0;

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {

            if (input.equals("list")) {
                Duke.displayTasks(tasks, numTasks);
            } else if (input.matches("\\bmark\\s\\d+")) { // regex: \bmark\s\d+, need \b to not detect unmark
                // do stuff
            } else if (input.matches("\\bunmark\\s\\d+")) {
                // do stuff
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
