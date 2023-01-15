import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SmartDuke {
    /**
     * The list of tasks added by the user and currently recorded by SmartDuke.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Enumerates the different types of user commands available on SmartDuke.
     */
    enum Commands {
        ADD_TODO("todo.*"),
        ADD_DEADLINE("deadline.*/by.*"),
        ADD_EVENT("event.*/from.*/to.*"),
        DELETE_TASK("delete[\\s]*[0-9]+[\\s]*"),
        MARK_TASK("mark[\\s]*[0-9]+[\\s]*"),
        UNMARK_TASK("unmark[\\s]*[0-9]+[\\s]*"),
        LIST_TASKS("list"),
        END_CHAT("bye");

        /**
         * The regex pattern of this Command.
         */
        private Pattern pattern;

        /**
         * Checks if the given string matches this Command.
         * @param userCommand The given string provided by the user.
         * @return True if the given string matches this Command.
         */
        public boolean match(String userCommand) {
            return this.pattern.matcher(userCommand).matches();
        }

        private Commands(String regex) {
            this.pattern = Pattern.compile(regex);
        }
    }

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
     * @throws DukeException If there is no such task in the list with the given task number.
     */
    private static void deleteTask(int taskNo) throws DukeException {
        try {
            Task removedTask = SmartDuke.taskList.remove(taskNo - 1);
            System.out.println("--------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now there are " + SmartDuke.taskList.size() + " tasks in your list.");
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
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
     * @throws DukeException If there is no such task in the list with the given task number.
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
     * @throws DukeException If there is no such task in the list with the given task number.
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
                String userCommand = userInput.nextLine();
                if (Commands.ADD_TODO.match(userCommand)) {
                    /* add todo task */
                    String taskDesc = userCommand.substring(4).trim();
                    Task todoTask = new ToDo(taskDesc);
                    SmartDuke.addTask(todoTask);
                } else if (Commands.ADD_DEADLINE.match(userCommand)) {
                    /* add deadline task */
                    String[] parsedCommand = userCommand.substring(8).split("/by", -1);
                    String taskDesc = parsedCommand[0].trim();
                    String by = parsedCommand[1].trim();
                    Task deadlineTask = new Deadline(taskDesc, by);
                    SmartDuke.addTask(deadlineTask);
                } else if (Commands.ADD_EVENT.match(userCommand)) {
                    /* add event task */
                    String[] parsedCommand = userCommand.substring(5).split("/from|/to", -1);
                    String taskDesc = parsedCommand[0].trim();
                    String from = parsedCommand[1].trim();
                    String to = parsedCommand[2].trim();
                    Task eventTask = new Event(taskDesc, from, to);
                    SmartDuke.addTask(eventTask);
                } else if (Commands.DELETE_TASK.match(userCommand)) {
                    /* delete task */
                    int taskNo = Integer.parseInt(userCommand.substring(6).trim());
                    SmartDuke.deleteTask(taskNo);
                } else if (Commands.MARK_TASK.match(userCommand)) {
                    /* mark task */
                    int taskNo = Integer.parseInt(userCommand.substring(4).trim());
                    SmartDuke.markTask(taskNo);
                } else if (Commands.UNMARK_TASK.match(userCommand)) {
                    /* unmark task */
                    int taskNo = Integer.parseInt(userCommand.substring(6).trim());
                    SmartDuke.unmarkTask(taskNo);
                } else if (Commands.LIST_TASKS.match(userCommand)) {
                    SmartDuke.listTasks();
                } else if (Commands.END_CHAT.match(userCommand)) {
                    /* End the session */
                    System.out.println("--------------------------");
                    System.out.println("ok bye");
                    System.out.println("--------------------------");
                    break;
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