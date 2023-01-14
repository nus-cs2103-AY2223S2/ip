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
     * @param task the given task
     */
    private static void addTask(Task task) {
        try {
            SmartDuke.taskList[SmartDuke.numTasks] = task;
            SmartDuke.numTasks += 1;
            System.out.println("--------------------------");
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now there are " + SmartDuke.numTasks + " tasks in your list.");
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            Task task = SmartDuke.taskList[i];
            System.out.println(i+1 + ". "+ task);
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
            System.out.println("ok i've marked this task as done:");
            System.out.println(task);
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
            System.out.println("ok i've marked this task as not done yet");
            System.out.println(task);
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

        while (true) {
            String command = userInput.nextLine();
            if (command.equals("bye")) {
                /* End the session */
                System.out.println("--------------------------");
                System.out.println("ok bye");
                System.out.println("--------------------------");
                break;
            } else if (command.equals("list")) {
                SmartDuke.listTasks();
            } else if (command.matches("todo\\s[^\\s].*")) {
                /* add todo task */
                String taskDesc = command.substring(5).trim();
                Task todoTask = new ToDo(taskDesc);
                SmartDuke.addTask(todoTask);
            } else if (command.matches("deadline\\s[^\\s].*\\s/by\\s[^\\s].*")) {
                /* add deadline task */
                String[] parsedCommand = command.substring(9).split("/by");
                String taskDesc = parsedCommand[0].trim();
                String by = parsedCommand[1].trim();
                Task deadlineTask = new Deadline(taskDesc, by);
                SmartDuke.addTask(deadlineTask);
            } else if (command.matches("event\\s[^\\s].*\\s/from\\s[^\\s].*\\s/to\\s[^\\s].*")) {
                /* add event task */
                String[] parsedcommand = command.substring(6).split("/[a-z]+\\s");
                String taskDesc = parsedcommand[0].trim();
                String from = parsedcommand[1].trim();
                String to = parsedcommand[2].trim();
                Task eventTask = new Event(taskDesc, from, to);
                SmartDuke.addTask(eventTask);
            } else if (command.matches("mark [0-9]+")) {
                /* mark task */
                int taskNo = Integer.parseInt(command.substring(5));
                SmartDuke.markTask(taskNo);
            } else if (command.matches("unmark [0-9]+")) {
                /* unmark task */
                int taskNo = Integer.parseInt(command.substring(7));
                SmartDuke.unmarkTask(taskNo);
            } else {
                System.err.println("huh? i dont understand you");
            }
        }
    }

    public static void main(String[] args) {
        SmartDuke.startSession();
    }
}
