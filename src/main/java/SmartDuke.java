import java.util.Scanner;

public class SmartDuke {
    /**
     * The list of tasks added by the user and currently recorded by SmartDuke.
     */
    private static Task[] taskList = new Task[100];

    /**
     * The number of tasks currently recorded by SmartDuke.
     */
    private static int numTasks = 0;

    /**
     * Add the given task to taskList.
     * @param taskDesc the given task description
     */
    private static void addTask(String taskDesc) {
        try {
            Task task = new Task(taskDesc);
            SmartDuke.taskList[SmartDuke.numTasks] = task;
            SmartDuke.numTasks += 1;
            System.out.println("--------------------------");
            System.out.println("added: " + task.getDescription());
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Sorry I can only store a maximum of 100 tasks...");
        }
    }

    /**
     * Displays all the tasks currently recorded by SmartDuke.
     */
    private static void listTasks() {
        System.out.println("--------------------------");
        System.out.println("Here are all your tasks:");
        for (int i = 0; i < numTasks; i++) {
            Task task = SmartDuke.taskList[i];
            String message = String.format("%d. %s [%s]", i+1,
                    task.getDescription(), task.getStatusIcon());
            System.out.println(message);
        }
        System.out.println("--------------------------");
    }

    /**
     * Marks the task with the given task number as done.
     * @param taskNo the given task number
     */
    private static void markTask(int taskNo) {
        try {
            Task task = SmartDuke.taskList[taskNo - 1];
            task.markDone();
            System.out.println("--------------------------");
            String message = String.format("ok i've marked this task as done:\n [%s] %s",
                    task.getStatusIcon(), task.getDescription());
            System.out.println(message);
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Marks the task with the given task number as not done.
     * @param taskNo the given task number
     */
    private static void unmarkTask(int taskNo) {
        try {
            Task task = SmartDuke.taskList[taskNo - 1];
            task.markNotDone();
            System.out.println("--------------------------");
            String message = String.format("ok i've marked this task as not done yet:\n [%s] %s",
                    task.getStatusIcon(), task.getDescription());
            System.out.println(message);
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Begins the chat session with the user.
     */
    private static void startSession() {
        /* Greet the user */
        String greeting = "Yo i'm SmartDuke.\n" +
                "     how can i help you?";
        System.out.println("--------------------------");
        System.out.println(greeting);
        System.out.println("--------------------------");

        Scanner userInput = new Scanner(System.in);

        conversationLoop: while (true) {
            String command = userInput.nextLine();
            switch (command) {
                case "bye":
                    /* End the session */
                    System.out.println("--------------------------");
                    System.out.println("ok bye");
                    System.out.println("--------------------------");
                    break conversationLoop;
                case "list":
                    SmartDuke.listTasks();
                    break;
                default:
                    if (command.matches("mark [0-9]+")) { /* the 'mark task' command */
                        int taskNo = Integer.parseInt(command.substring(5));
                        SmartDuke.markTask(taskNo);
                    } else if (command.matches("unmark [0-9]+")) { /* the 'unmark task' command */
                        int taskNo = Integer.parseInt(command.substring(7));
                        SmartDuke.unmarkTask(taskNo);
                    } else {
                        SmartDuke.addTask(command);
                    }
            }
        }
    }

    public static void main(String[] args) {
        SmartDuke.startSession();
    }
}
