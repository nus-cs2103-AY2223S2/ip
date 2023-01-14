import java.util.ArrayList;
import java.util.Scanner;

public class SmartDuke {
    /**
     * The list of tasks added by the user and currently recorded by SmartDuke.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Add the given task to taskList.
     * @param task the given task
     */
    private static void addTask(Task task) {
        SmartDuke.taskList.add(task);
        System.out.println("--------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now there are " + SmartDuke.taskList.size() + " tasks in your list.");
        System.out.println("--------------------------");
    }

    /**
     * Delete the task with the given task number from the list.
     * @param taskNo the given task number
     */
    private static void deleteTask(int taskNo) {
        Task removedTask = SmartDuke.taskList.remove(taskNo - 1);
        System.out.println("--------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now there are " + SmartDuke.taskList.size() + " tasks in your list.");
        System.out.println("--------------------------");
    }

    /**
     * Displays all the tasks currently recorded by SmartDuke.
     */
    private static void listTasks() {
        System.out.println("--------------------------");
        if (SmartDuke.taskList.size() == 0) {
            System.out.println("There are currently no tasks in your list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < SmartDuke.taskList.size(); i++) {
            Task task = SmartDuke.taskList.get(i);
            System.out.println(i+1 + ". "+ task);
        }
        System.out.println("--------------------------");
    }

    /**
     * Marks the task with the given task number as done.
     * @param taskNo the given task number
     */
    private static void markTask(int taskNo) throws DukeException {
        try {
            Task task = SmartDuke.taskList.get(taskNo - 1);
            task.markDone();
            System.out.println("--------------------------");
            System.out.println("ok i've marked this task as done:");
            System.out.println(task);
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Marks the task with the given task number as not done.
     * @param taskNo the given task number
     */
    private static void unmarkTask(int taskNo) throws DukeException {
        try {
            Task task = SmartDuke.taskList.get(taskNo - 1);
            task.markNotDone();
            System.out.println("--------------------------");
            System.out.println("ok i've marked this task as not done yet");
            System.out.println(task);
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
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
            try {
                String command = userInput.nextLine();
                if (command.equals("bye")) {
                    /* End the session */
                    System.out.println("--------------------------");
                    System.out.println("ok bye");
                    System.out.println("--------------------------");
                    break;
                } else if (command.equals("list")) {
                    SmartDuke.listTasks();
                } else if (command.matches("todo.*")) {
                    /* add todo task */
                    String taskDesc = command.substring(4).trim();
                    Task todoTask = new ToDo(taskDesc);
                    SmartDuke.addTask(todoTask);
                } else if (command.matches("deadline.*/by.*")) {
                    /* add deadline task */
                    String[] parsedCommand = command.substring(8).split("/by", -1);
                    String taskDesc = parsedCommand[0].trim();
                    String by = parsedCommand[1].trim();
                    Task deadlineTask = new Deadline(taskDesc, by);
                    SmartDuke.addTask(deadlineTask);
                } else if (command.matches("event.*/from.*/to.*")) {
                    /* add event task */
                    String[] parsedCommand = command.substring(5).split("/from|/to", -1);
                    String taskDesc = parsedCommand[0].trim();
                    String from = parsedCommand[1].trim();
                    String to = parsedCommand[2].trim();
                    Task eventTask = new Event(taskDesc, from, to);
                    SmartDuke.addTask(eventTask);
                } else if (command.matches("mark[\\s]*[0-9]+[\\s]*")) {
                    /* mark task */
                    int taskNo = Integer.parseInt(command.substring(4).trim());
                    SmartDuke.markTask(taskNo);
                } else if (command.matches("unmark[\\s]*[0-9]+[\\s]*")) {
                    /* unmark task */
                    int taskNo = Integer.parseInt(command.substring(6).trim());
                    SmartDuke.unmarkTask(taskNo);
                } else if (command.matches("delete[\\s]*[0-9]+[\\s]*")) {
                    int taskNo = Integer.parseInt(command.substring(6).trim());
                    SmartDuke.deleteTask(taskNo);
                } else throw new DukeException("huh? i dont understand you...");
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SmartDuke.startSession();
    }
}
