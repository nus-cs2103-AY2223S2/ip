import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskStore = new ArrayList<>(100);
    private static final String END_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String byIndicator = "/by";
    private static final String fromIndicator = "/from";
    private static final String toIndicator = "/to";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        String separator = "____________________________________________________________\n";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting + separator);
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String command = sc.nextLine().trim();
                if (command.equals(Duke.END_COMMAND)) {
                    sc.close();
                    System.out.println(goodbye);
                    break;
                } else if (command.equals(Duke.LIST_COMMAND)) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < Duke.taskStore.size(); i++) {
                        System.out.println((i + 1) + ". " + Duke.taskStore.get(i));
                    }
                } else {
                    processCommand(command);
                } 
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(separator);
            }
            
        }
    }

    private static void processCommand(String line) throws DukeException {
        String[] splitCommand = line.split(" ");
        String command = splitCommand[0];
        if (command.equals(Duke.MARK_COMMAND)) {
            if (splitCommand.length == 1) {
                throw new DukeException("A task number needs to be provided.");
            } else if (splitCommand.length > 2) {
                throw new DukeException("I don't recognise that task number.");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("I don't recognise that task number.");
            }
            if (taskIndex >= taskStore.size() || taskIndex < 0) {
                throw new DukeException("That task number doesn't exist!");
            }
            Duke.taskStore.get(taskIndex).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(Duke.taskStore.get(taskIndex));
        } else if (command.equals(Duke.UNMARK_COMMAND)) {
            if (splitCommand.length == 1) {
                throw new DukeException("A task number needs to be provided.");
            } else if (splitCommand.length > 2) {
                throw new DukeException("I don't recognise that task number.");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("I don't recognise that task number.");
            }
            if (taskIndex >= taskStore.size() || taskIndex < 0) {
                throw new DukeException("That task number doesn't exist!");
            }
            Duke.taskStore.get(taskIndex).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(Duke.taskStore.get(taskIndex));
        } else if (command.equals(Duke.DELETE_COMMAND)) {
            if (splitCommand.length == 1) {
                throw new DukeException("A task number needs to be provided.");
            } else if (splitCommand.length > 2) {
                throw new DukeException("I don't recognise that task number.");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("I don't recognise that task number.");
            }
            if (taskIndex >= taskStore.size() || taskIndex < 0) {
                throw new DukeException("That task number doesn't exist!");
            }
            Task task = Duke.taskStore.get(taskIndex);
            Duke.taskStore.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.println("Now you have " + Duke.taskStore.size() + " tasks in the list.");
        } else {
            Task task = null;
            if (command.equals(Duke.TODO_COMMAND)) {
                try {
                    String description = line.split(Duke.TODO_COMMAND)[1].trim();
                    if (description.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    task = new Todo(description);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            } else if (command.equals(Duke.DEADLINE_COMMAND)) {
                try {
                    String details = line.split(Duke.DEADLINE_COMMAND)[1].trim();
                    String name = details.split(Duke.byIndicator)[0].trim();
                    String deadline = details.split(Duke.byIndicator)[1].trim();
                    if (name.isEmpty() || deadline.isEmpty()) {
                        throw new DukeException("The description and /by of a deadline cannot be empty.");
                    }
                    task = new Deadline(name, deadline);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description and /by of a deadline cannot be empty.");
                }
            } else if (command.equals(Duke.EVENT_COMMAND)) {
                try {
                    String details = line.split(Duke.EVENT_COMMAND)[1].trim();
                    String name = details.split(Duke.fromIndicator)[0].trim();
                    String from = details.split(Duke.fromIndicator)[1].split(Duke.toIndicator)[0].trim();
                    String to = details.split(Duke.fromIndicator)[1].split(Duke.toIndicator)[1].trim();
                    if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new DukeException("The description, /from and /to of a deadline cannot be empty.");
                    }
                    task = new Event(name, from, to);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description, /from and /to of a deadline cannot be empty.");
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            Duke.taskStore.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + Duke.taskStore.size() + " tasks in the list.");
        }
    }

}
