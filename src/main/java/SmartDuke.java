import java.util.Scanner;

public class SmartDuke {
    /**
     * The list of tasks added by the user and currently recorded by SmartDuke.
     */
    private static String[] taskList = new String[1];

    /**
     * The number of tasks currently recorded by SmartDuke.
     */
    private static int numTasks = 0;

    /**
     * Add the given task to taskList.
     * @param task the given task
     */
    private static void addTask(String task) {
        try {
            SmartDuke.taskList[SmartDuke.numTasks] = task;
            SmartDuke.numTasks += 1;
            System.out.println("--------------------------");
            System.out.println("added: " + task);
            System.out.println("--------------------------");
        } catch(IndexOutOfBoundsException e) {
            System.err.println("Sorry I can only store a maximum of 100 tasks...");
        }
    }

    /**
     * Displays all the tasks currently recorded by SmartDuke.
     */
    private static void listTasks() {
        System.out.println("--------------------------");
        for (int i = 0; i < numTasks; i++) {
            String task = SmartDuke.taskList[i];
            System.out.println(i + 1 + " " + task);
        }
        System.out.println("--------------------------");
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
                    SmartDuke.addTask(command);
            }
        }
    }

    public static void main(String[] args) {
        SmartDuke.startSession();
    }
}
